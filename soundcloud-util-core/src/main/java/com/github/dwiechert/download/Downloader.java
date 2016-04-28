package com.github.dwiechert.download;

import com.github.dwiechert.sc.util.models.Mp3Metadata;

public interface Downloader {
	public void downloadPlaylist(String playlistUrl, String destinationFolder, Mp3Metadata metadata);
	
	public void downloadArtist(String artistUrl, String destinationFolder, Mp3Metadata metadata);

	public String downloadSong(String songUrl, String destinationFolder, Mp3Metadata metadata);
}
