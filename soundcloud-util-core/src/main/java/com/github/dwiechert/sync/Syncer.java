package com.github.dwiechert.sync;

import com.github.dwiechert.sc.util.models.FolderConfig;

public interface Syncer {
	public void sync(FolderConfig folderConfig);
}
