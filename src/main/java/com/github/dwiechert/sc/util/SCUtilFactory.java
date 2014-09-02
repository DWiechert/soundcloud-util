package com.github.dwiechert.sc.util;

import com.github.dwiechert.download.Downloader;
import com.github.dwiechert.download.impls.SCDownloader;

public final class SCUtilFactory {
	private static final SCDownloader SC_DOWNLOADER = new SCDownloader();
	
	private SCUtilFactory() {
		
	}

	public static Downloader getDownloader(final String url) {
		if (url.contains("soundcloud")) {
			return SC_DOWNLOADER;
		} else {
			throw new IllegalArgumentException("Downloader for url [" + url + "] is currently not supported.");
		}
	}
}
