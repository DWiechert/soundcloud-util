package com.github.dwiechert.sc.util.models;

public class SongConfig {
	private String songUrl;
	private long trackId;
	private boolean syncOn = true;
	private String localSong;
	private Mp3Metadata mp3Metadata;

	/**
	 * @return the songUrl
	 */
	public String getSongUrl() {
		return songUrl;
	}

	/**
	 * @param songUrl
	 *            the songUrl to set
	 */
	public void setSongUrl(final String songUrl) {
		this.songUrl = songUrl;
	}

	/**
	 * @return the trackId
	 */
	public long getTrackId() {
		return trackId;
	}

	/**
	 * @param trackId
	 *            the trackId to set
	 */
	public void setTrackId(final long trackId) {
		this.trackId = trackId;
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
	 * @return the localSong
	 */
	public String getLocalSong() {
		return localSong;
	}

	/**
	 * @param localSong
	 *            the localSong to set
	 */
	public void setLocalSong(final String localSong) {
		this.localSong = localSong;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (localSong == null ? 0 : localSong.hashCode());
		result = prime * result + (mp3Metadata == null ? 0 : mp3Metadata.hashCode());
		result = prime * result + (songUrl == null ? 0 : songUrl.hashCode());
		result = prime * result + (syncOn ? 1231 : 1237);
		result = prime * result + (int) (trackId ^ trackId >>> 32);
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
		if (!(obj instanceof SongConfig)) {
			return false;
		}
		final SongConfig other = (SongConfig) obj;
		if (localSong == null) {
			if (other.localSong != null) {
				return false;
			}
		} else if (!localSong.equals(other.localSong)) {
			return false;
		}
		if (mp3Metadata == null) {
			if (other.mp3Metadata != null) {
				return false;
			}
		} else if (!mp3Metadata.equals(other.mp3Metadata)) {
			return false;
		}
		if (songUrl == null) {
			if (other.songUrl != null) {
				return false;
			}
		} else if (!songUrl.equals(other.songUrl)) {
			return false;
		}
		if (syncOn != other.syncOn) {
			return false;
		}
		if (trackId != other.trackId) {
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
		return "SongConfig [songUrl=" + songUrl + ", trackId=" + trackId + ", syncOn=" + syncOn + ", localSong=" + localSong + ", mp3Metadata="
				+ mp3Metadata + "]";
	}
}
