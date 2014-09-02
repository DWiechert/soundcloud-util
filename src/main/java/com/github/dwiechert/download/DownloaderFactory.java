package com.github.dwiechert.download;

import com.github.dwiechert.download.impls.SCDownloader;

public final class DownloaderFactory {
	private static final SCDownloader SC_DOWNLOADER = new SCDownloader();
	
	private DownloaderFactory() {
		
	}

	public static Downloader getDownloader(final String url) {
		if (url.contains("soundcloud")) {
			return SC_DOWNLOADER;
		} else {
			throw new IllegalArgumentException("Downloader for url [" + url + "] is currently not supported.");
		}
	}
}
