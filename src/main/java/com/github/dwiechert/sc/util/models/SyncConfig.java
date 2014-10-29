package com.github.dwiechert.sc.util.models;

import java.util.ArrayList;
import java.util.List;

public class SyncConfig {
	private List<FolderConfig> configs;

	/**
	 * @return the configs
	 */
	public List<FolderConfig> getConfigs() {
		if (configs == null) {
			configs = new ArrayList<>();
		}
		return configs;
	}

	/**
	 * @param configs
	 *            the configs to set
	 */
	public void setConfigs(final List<FolderConfig> configs) {
		this.configs = configs;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (configs == null ? 0 : configs.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof SyncConfig)) {
			return false;
		}
		final SyncConfig other = (SyncConfig) obj;
		if (configs == null) {
			if (other.configs != null) {
				return false;
			}
		} else if (!configs.equals(other.configs)) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SyncConfig [configs=" + configs + "]";
	}
}
