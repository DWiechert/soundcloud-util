package com.github.dwiechert.sc.util.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to be stored as json for the folder configuration for syncing.
 *
 * @author Dan Wiechert
 */
public class FolderConfig {
	private String artistUrl;
	private String artist;
	private String localFolder;
	private String downloadFolder;
	private boolean syncOn = true;
	private Mp3Metadata mp3Metadata;
	private List<SongConfig> songs;

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
	 * @return the artist
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * @param artist
	 *            the artist to set
	 */
	public void setArtist(final String artist) {
		this.artist = artist;
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

	/**
	 * @return the mp3Metadata
	 */
	public Mp3Metadata getMp3Metadata() {
		return mp3Metadata;
	}

	/**
	 * @param mp3Metadata
	 *            the mp3Metadata to set
	 */
	public void setMp3Metadata(final Mp3Metadata mp3Metadata) {
		this.mp3Metadata = mp3Metadata;
	}

	/**
	 * @return the songs
	 */
	public List<SongConfig> getSongs() {
		if (songs == null) {
			songs = new ArrayList<>();
		}
		return songs;
	}

	/**
	 * @param songs
	 *            the songs to set
	 */
	public void setSongs(final List<SongConfig> songs) {
		this.songs = songs;
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
		result = prime * result + (artist == null ? 0 : artist.hashCode());
		result = prime * result + (artistUrl == null ? 0 : artistUrl.hashCode());
		result = prime * result + (downloadFolder == null ? 0 : downloadFolder.hashCode());
		result = prime * result + (localFolder == null ? 0 : localFolder.hashCode());
		result = prime * result + (mp3Metadata == null ? 0 : mp3Metadata.hashCode());
		result = prime * result + (songs == null ? 0 : songs.hashCode());
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
		if (!(obj instanceof FolderConfig)) {
			return false;
		}
		final FolderConfig other = (FolderConfig) obj;
		if (artist == null) {
			if (other.artist != null) {
				return false;
			}
		} else if (!artist.equals(other.artist)) {
			return false;
		}
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
		if (mp3Metadata == null) {
			if (other.mp3Metadata != null) {
				return false;
			}
		} else if (!mp3Metadata.equals(other.mp3Metadata)) {
			return false;
		}
		if (songs == null) {
			if (other.songs != null) {
				return false;
			}
		} else if (!songs.equals(other.songs)) {
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
		return "FolderConfig [artistUrl=" + artistUrl + ", artist=" + artist + ", localFolder=" + localFolder + ", downloadFolder=" + downloadFolder
				+ ", syncOn=" + syncOn + ", mp3Metadata=" + mp3Metadata + ", songs=" + songs + "]";
	}
}
