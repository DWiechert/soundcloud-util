package com.github.dwiechert.mp3;

import java.io.IOException;

import com.github.dwiechert.sc.util.models.Mp3Metadata;
import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.ID3v24Tag;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.NotSupportedException;
import com.mpatric.mp3agic.UnsupportedTagException;

public class Mp3Tagger {
	public static void tagMp3(final String originalFilename, final String endFilename, final Mp3Metadata metadata) {
		// All tagging was found on the GitHub README - https://github.com/mpatric/mp3agic
		try {
			final Mp3File mp3File = new Mp3File(originalFilename);

			final ID3v1 tag;
			if (mp3File.hasId3v1Tag()) {
				tag = mp3File.getId3v1Tag();
			} else {
				tag = new ID3v24Tag();
				mp3File.setId3v1Tag(tag);
			}

			tag.setTitle(metadata.getTitle());
			tag.setArtist(metadata.getArtist());
			tag.setComposer(metadata.getComposer());
			tag.setPublisher(metadata.getPublisher());
			tag.setOriginalArtist(metadata.getOriginalArtist());
			tag.setAlbumArtist(metadata.getAlbumArtist());
			tag.setAlbum(metadata.getAlbum());
			tag.setGenreDescription(metadata.getGenre());
			tag.setTrack(metadata.getTrackNumber());
			tag.setUrl(metadata.getUrl());
			tag.setCopyright(metadata.getCopyright());
			tag.setComment(metadata.getComment());
			tag.setEncoder(metadata.getEncoder());
			
			mp3File.save(endFilename);
		} catch (final UnsupportedTagException | InvalidDataException | IOException | NotSupportedException e) {
			System.err.println("Error tagging file [" + originalFilename + "] with mp3 metadata.");
		}
	}
}
