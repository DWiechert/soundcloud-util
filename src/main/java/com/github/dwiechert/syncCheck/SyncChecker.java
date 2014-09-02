package com.github.dwiechert.syncCheck;

import com.github.dwiechert.sc.util.models.FolderConfig;

public interface SyncChecker {
	public void check(FolderConfig folderConfig);
}
