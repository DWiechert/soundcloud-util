package com.github.dwiechert.mp3;

import java.io.IOException;

import com.github.dwiechert.sc.util.models.Mp3Metadata;
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

			final ID3v2 id3v2Tag;
			if (mp3File.hasId3v2Tag()) {
				id3v2Tag = mp3File.getId3v2Tag();
			} else {
				id3v2Tag = new ID3v24Tag();
				mp3File.setId3v2Tag(id3v2Tag);
			}

			id3v2Tag.setTitle(metadata.getTitle());
			id3v2Tag.setArtist(metadata.getArtist());
			id3v2Tag.setComposer(metadata.getComposer());
			id3v2Tag.setPublisher(metadata.getPublisher());
			id3v2Tag.setOriginalArtist(metadata.getOriginalArtist());
			id3v2Tag.setAlbumArtist(metadata.getAlbumArtist());
			id3v2Tag.setAlbum(metadata.getAlbum());
			id3v2Tag.setGenreDescription(metadata.getGenre());
			id3v2Tag.setTrack(metadata.getTrackNumber());
			id3v2Tag.setUrl(metadata.getUrl());
			id3v2Tag.setCopyright(metadata.getCopyright());
			id3v2Tag.setComment(metadata.getComment());
			id3v2Tag.setEncoder(metadata.getEncoder());
			
			mp3File.save(endFilename);
		} catch (final UnsupportedTagException | InvalidDataException | IOException | NotSupportedException e) {
			System.err.println("Error tagging file [" + originalFilename + "] with mp3 metadata.");
		}
	}
}
