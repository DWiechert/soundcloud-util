package com.github.dwiechert.sc.util.models;

/**
 * Class to be stored as json for the folder configuration for syncing.
 *
 * @author Dan Wiechert
 */
public class FolderConfig {
	private String artistUrl;
	private String localFolder;
	private String downloadFolder;
	private boolean syncOn;

	/**
	 * @return the artistUrl
	 */
	public String getArtistUrl() {
		return artistUrl;
	}

	/**
	 * @param artistUrl
	 *            the artistUrl to set
	 */
	public void setArtistUrl(final String artistUrl) {
		this.artistUrl = artistUrl;
	}

	/**
	 * @return the localFolder
	 */
	public String getLocalFolder() {
		return localFolder;
	}

	/**
	 * @param localFolder
	 *            the localFolder to set
	 */
	public void setLocalFolder(final String localFolder) {
		this.localFolder = localFolder;
	}

	/**
	 * @return the downloadFolder
	 */
	public String getDownloadFolder() {
		return downloadFolder;
	}

	/**
	 * @param downloadFolder
	 *            the downloadFolder to set
	 */
	public void setDownloadFolder(final String downloadFolder) {
		this.downloadFolder = downloadFolder;
	}

	/**
	 * @return the syncOn
	 */
	public boolean isSyncOn() {
		return syncOn;
	}

	/**
	 * @param syncOn
	 *            the syncOn to set
	 */
	public void setSyncOn(final boolean syncOn) {
		this.syncOn = syncOn;
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
		result = prime * result
				+ (artistUrl == null ? 0 : artistUrl.hashCode());
		result = prime * result
				+ (downloadFolder == null ? 0 : downloadFolder.hashCode());
		result = prime * result
				+ (localFolder == null ? 0 : localFolder.hashCode());
		result = prime * result + (syncOn ? 1231 : 1237);
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
		if (getClass() != obj.getClass()) {
			return false;
		}
		final FolderConfig other = (FolderConfig) obj;
		if (artistUrl == null) {
			if (other.artistUrl != null) {
				return false;
			}
		} else if (!artistUrl.equals(other.artistUrl)) {
			return false;
		}
		if (downloadFolder == null) {
			if (other.downloadFolder != null) {
				return false;
			}
		} else if (!downloadFolder.equals(other.downloadFolder)) {
			return false;
		}
		if (localFolder == null) {
			if (other.localFolder != null) {
				return false;
			}
		} else if (!localFolder.equals(other.localFolder)) {
			return false;
		}
		if (syncOn != other.syncOn) {
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
		return "FolderConfig [artistUrl=" + artistUrl + ", localFolder="
				+ localFolder + ", downloadFolder=" + downloadFolder
				+ ", syncOn=" + syncOn + "]";
	}
}
