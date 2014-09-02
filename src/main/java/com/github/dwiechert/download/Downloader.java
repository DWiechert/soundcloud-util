package com.github.dwiechert.download;

public interface Downloader {
	public void downloadArtist(String artistUrl, String destinationFolder);
	
	public void downloadSong(String songUrl, String destinationFolder);
}
