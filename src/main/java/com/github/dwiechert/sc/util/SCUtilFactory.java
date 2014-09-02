package com.github.dwiechert.sc.util;

import com.github.dwiechert.download.Downloader;
import com.github.dwiechert.download.impls.SCDownloader;
import com.github.dwiechert.syncCheck.SyncChecker;
import com.github.dwiechert.syncCheck.impls.SCSyncChecker;

public final class SCUtilFactory {
	private static final SCDownloader SC_DOWNLOADER = new SCDownloader();
	private static final SCSyncChecker SC_SYNC_CHECKER = new SCSyncChecker();

	private SCUtilFactory() {

	}

	public static Downloader getDownloader(final String url) {
		if (url.contains("soundcloud")) {
			return SC_DOWNLOADER;
		} else {
			throw new IllegalArgumentException("Downloader for url [" + url + "] is currently not supported.");
		}
	}

	public static SyncChecker getSyncChecker(final String url) {
		if (url.contains("soundcloud")) {
			return SC_SYNC_CHECKER;
		} else {
			throw new IllegalArgumentException("SyncChecker for url [" + url + "] is currently not supported.");
		}
	}
}
