package com.github.dwiechert.sc.util.models;

/**
 * Class to store the mp3 information.
 *
 * @author Dan Wiechert
 */
public class Mp3Metadata {
	private String title;
	private String artist;
	private String composer;
	private String publisher;
	private String originalArtist;
	private String albumArtist;
	private String album;
	private String genre;
	private String trackNumber;
	private String url;
	private String copyright;
	private String comment;
	private String encoder;

	/**
	 * Default constructor.
	 */
	public Mp3Metadata() {
		// Do nothing
	}

	/**
	 * Copy constructor.
	 * 
	 * @param other
	 *            The other {@link Mp3Metadata} to copy.
	 */
	public Mp3Metadata(final Mp3Metadata other) {
		this.title = other.title;
		this.artist = other.artist;
		this.composer = other.composer;
		this.publisher = other.publisher;
		this.originalArtist = other.originalArtist;
		this.albumArtist = other.albumArtist;
		this.album = other.album;
		this.genre = other.genre;
		this.trackNumber = other.trackNumber;
		this.url = other.url;
		this.copyright = other.copyright;
		this.comment = other.comment;
		this.encoder = other.encoder;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(final String title) {
		this.title = title;
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
	 * @return the composer
	 */
	public String getComposer() {
		return composer;
	}

	/**
	 * @param composer
	 *            the composer to set
	 */
	public void setComposer(final String composer) {
		this.composer = composer;
	}

	/**
	 * @return the publisher
	 */
	public String getPublisher() {
		return publisher;
	}

	/**
	 * @param publisher
	 *            the publisher to set
	 */
	public void setPublisher(final String publisher) {
		this.publisher = publisher;
	}

	/**
	 * @return the originalArtist
	 */
	public String getOriginalArtist() {
		return originalArtist;
	}

	/**
	 * @param originalArtist
	 *            the originalArtist to set
	 */
	public void setOriginalArtist(final String originalArtist) {
		this.originalArtist = originalArtist;
	}

	/**
	 * @return the albumArtist
	 */
	public String getAlbumArtist() {
		return albumArtist;
	}

	/**
	 * @param albumArtist
	 *            the albumArtist to set
	 */
	public void setAlbumArtist(final String albumArtist) {
		this.albumArtist = albumArtist;
	}

	/**
	 * @return the album
	 */
	public String getAlbum() {
		return album;
	}

	/**
	 * @param album
	 *            the album to set
	 */
	public void setAlbum(final String album) {
		this.album = album;
	}

	/**
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * @param genre
	 *            the genre to set
	 */
	public void setGenre(final String genre) {
		this.genre = genre;
	}

	/**
	 * @return the trackNumber
	 */
	public String getTrackNumber() {
		return trackNumber;
	}

	/**
	 * @param trackNumber
	 *            the trackNumber to set
	 */
	public void setTrackNumber(final String trackNumber) {
		this.trackNumber = trackNumber;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(final String url) {
		this.url = url;
	}

	/**
	 * @return the copyright
	 */
	public String getCopyright() {
		return copyright;
	}

	/**
	 * @param copyright
	 *            the copyright to set
	 */
	public void setCopyright(final String copyright) {
		this.copyright = copyright;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(final String comment) {
		this.comment = comment;
	}

	/**
	 * @return the encoder
	 */
	public String getEncoder() {
		return encoder;
	}

	/**
	 * @param encoder
	 *            the encoder to set
	 */
	public void setEncoder(final String encoder) {
		this.encoder = encoder;
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
		result = prime * result + (album == null ? 0 : album.hashCode());
		result = prime * result + (albumArtist == null ? 0 : albumArtist.hashCode());
		result = prime * result + (artist == null ? 0 : artist.hashCode());
		result = prime * result + (comment == null ? 0 : comment.hashCode());
		result = prime * result + (composer == null ? 0 : composer.hashCode());
		result = prime * result + (copyright == null ? 0 : copyright.hashCode());
		result = prime * result + (encoder == null ? 0 : encoder.hashCode());
		result = prime * result + (genre == null ? 0 : genre.hashCode());
		result = prime * result + (originalArtist == null ? 0 : originalArtist.hashCode());
		result = prime * result + (publisher == null ? 0 : publisher.hashCode());
		result = prime * result + (title == null ? 0 : title.hashCode());
		result = prime * result + (trackNumber == null ? 0 : trackNumber.hashCode());
		result = prime * result + (url == null ? 0 : url.hashCode());
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
		if (!(obj instanceof Mp3Metadata)) {
			return false;
		}
		final Mp3Metadata other = (Mp3Metadata) obj;
		if (album == null) {
			if (other.album != null) {
				return false;
			}
		} else if (!album.equals(other.album)) {
			return false;
		}
		if (albumArtist == null) {
			if (other.albumArtist != null) {
				return false;
			}
		} else if (!albumArtist.equals(other.albumArtist)) {
			return false;
		}
		if (artist == null) {
			if (other.artist != null) {
				return false;
			}
		} else if (!artist.equals(other.artist)) {
			return false;
		}
		if (comment == null) {
			if (other.comment != null) {
				return false;
			}
		} else if (!comment.equals(other.comment)) {
			return false;
		}
		if (composer == null) {
			if (other.composer != null) {
				return false;
			}
		} else if (!composer.equals(other.composer)) {
			return false;
		}
		if (copyright == null) {
			if (other.copyright != null) {
				return false;
			}
		} else if (!copyright.equals(other.copyright)) {
			return false;
		}
		if (encoder == null) {
			if (other.encoder != null) {
				return false;
			}
		} else if (!encoder.equals(other.encoder)) {
			return false;
		}
		if (genre == null) {
			if (other.genre != null) {
				return false;
			}
		} else if (!genre.equals(other.genre)) {
			return false;
		}
		if (originalArtist == null) {
			if (other.originalArtist != null) {
				return false;
			}
		} else if (!originalArtist.equals(other.originalArtist)) {
			return false;
		}
		if (publisher == null) {
			if (other.publisher != null) {
				return false;
			}
		} else if (!publisher.equals(other.publisher)) {
			return false;
		}
		if (title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!title.equals(other.title)) {
			return false;
		}
		if (trackNumber == null) {
			if (other.trackNumber != null) {
				return false;
			}
		} else if (!trackNumber.equals(other.trackNumber)) {
			return false;
		}
		if (url == null) {
			if (other.url != null) {
				return false;
			}
		} else if (!url.equals(other.url)) {
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
		return "MP3Metadata [trackNumber=" + trackNumber + ", artist=" + artist + ", title=" + title + ", album=" + album + ", genre=" + genre
				+ ", comment=" + comment + ", composer=" + composer + ", publisher=" + publisher + ", originalArtist=" + originalArtist
				+ ", albumArtist=" + albumArtist + ", copyright=" + copyright + ", url=" + url + ", encoder=" + encoder + "]";
	}
}
