package com.github.dwiechert.syncCheck;

import com.github.dwiechert.sc.util.models.SyncConfig;

public interface SyncChecker {
	public void check(SyncConfig config);
}
