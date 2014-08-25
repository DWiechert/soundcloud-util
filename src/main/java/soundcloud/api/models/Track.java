package soundcloud.api.models;

import java.io.Serializable;
import java.util.Date;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a SoundCloud track.
 *
 * Class based off of the Json documentation found at <a href="http://developers.soundcloud.com/docs/api/reference#tracks">the tracks api</a>.
 *
 * @author Dan Wiechert
 */
public class Track implements Serializable {
	private static final long serialVersionUID = -4028984271260510444L;

	@SerializedName("id")
	private int id;

	@SerializedName("created_at")
	private Date createdAt;

	@SerializedName("user_id")
	private String userId;

	@SerializedName("user")
	private User user;

	@SerializedName("title")
	private String title;

	@SerializedName("permalink")
	private String permalink;

	@SerializedName("permalink_url")
	private String permalinkUrl;

	@SerializedName("uri")
	private String uri;

	@SerializedName("sharing")
	private String sharing;

	@SerializedName("embeddable_by")
	private String embeddableBy;

	@SerializedName("purchase_url")
	private String purchaseUrl;

	@SerializedName("artwork_url")
	private String artworkUrl;

	@SerializedName("description")
	private String description;

	@SerializedName("duration")
	private long duration;

	@SerializedName("genre")
	private String genre;

	@SerializedName("shared_to_count")
	private int sharedToCount;

	@SerializedName("tag_list")
	private String tagList;

	@SerializedName("label_id")
	private String labelId;

	@SerializedName("label_name")
	private String labelName;

	@SerializedName("release")
	private String release;

	@SerializedName("release_day")
	private String releaseDay;

	@SerializedName("release_month")
	private String releaseMonth;

	@SerializedName("release_year")
	private String releaseYear;

	@SerializedName("streamable")
	private boolean streamable;

	@SerializedName("downloadable")
	private boolean downloadable;

	@SerializedName("state")
	private String state;

	@SerializedName("license")
	private String license;

	@SerializedName("download_url")
	private String downloadUrl;

	@SerializedName("stream_url")
	private String streamUrl;

	/**
	 * Generic constructor for a Track.
	 */
	public Track() {

	}

	/**
	 * @return the id
	 */
	public final int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public final void setId(final int id) {
		this.id = id;
	}

	/**
	 * @return the createdAt
	 */
	public final Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt
	 *            the createdAt to set
	 */
	public final void setCreatedAt(final Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the userId
	 */
	public final String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public final void setUserId(final String userId) {
		this.userId = userId;
	}

	/**
	 * @return the user
	 */
	public final User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public final void setUser(final User user) {
		this.user = user;
	}

	/**
	 * @return the title
	 */
	public final String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public final void setTitle(final String title) {
		this.title = title;
	}

	/**
	 * @return the permalink
	 */
	public final String getPermalink() {
		return permalink;
	}

	/**
	 * @param permalink
	 *            the permalink to set
	 */
	public final void setPermalink(final String permalink) {
		this.permalink = permalink;
	}

	/**
	 * @return the permalinkUrl
	 */
	public final String getPermalinkUrl() {
		return permalinkUrl;
	}

	/**
	 * @param permalinkUrl
	 *            the permalinkUrl to set
	 */
	public final void setPermalinkUrl(final String permalinkUrl) {
		this.permalinkUrl = permalinkUrl;
	}

	/**
	 * @return the uri
	 */
	public final String getUri() {
		return uri;
	}

	/**
	 * @param uri
	 *            the uri to set
	 */
	public final void setUri(final String uri) {
		this.uri = uri;
	}

	/**
	 * @return the sharing
	 */
	public final String getSharing() {
		return sharing;
	}

	/**
	 * @param sharing
	 *            the sharing to set
	 */
	public final void setSharing(final String sharing) {
		this.sharing = sharing;
	}

	/**
	 * @return the embeddableBy
	 */
	public final String getEmbeddableBy() {
		return embeddableBy;
	}

	/**
	 * @param embeddableBy
	 *            the embeddableBy to set
	 */
	public final void setEmbeddableBy(final String embeddableBy) {
		this.embeddableBy = embeddableBy;
	}

	/**
	 * @return the purchaseUrl
	 */
	public final String getPurchaseUrl() {
		return purchaseUrl;
	}

	/**
	 * @param purchaseUrl
	 *            the purchaseUrl to set
	 */
	public final void setPurchaseUrl(final String purchaseUrl) {
		this.purchaseUrl = purchaseUrl;
	}

	/**
	 * @return the artworkUrl
	 */
	public final String getArtworkUrl() {
		return artworkUrl;
	}

	/**
	 * @param artworkUrl
	 *            the artworkUrl to set
	 */
	public final void setArtworkUrl(final String artworkUrl) {
		this.artworkUrl = artworkUrl;
	}

	/**
	 * @return the description
	 */
	public final String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public final void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @return the duration
	 */
	public final long getDuration() {
		return duration;
	}

	/**
	 * @param duration
	 *            the duration to set
	 */
	public final void setDuration(final long duration) {
		this.duration = duration;
	}

	/**
	 * @return the genre
	 */
	public final String getGenre() {
		return genre;
	}

	/**
	 * @param genre
	 *            the genre to set
	 */
	public final void setGenre(final String genre) {
		this.genre = genre;
	}

	/**
	 * @return the sharedToCount
	 */
	public final int getSharedToCount() {
		return sharedToCount;
	}

	/**
	 * @param sharedToCount
	 *            the sharedToCount to set
	 */
	public final void setSharedToCount(final int sharedToCount) {
		this.sharedToCount = sharedToCount;
	}

	/**
	 * @return the tagList
	 */
	public final String getTagList() {
		return tagList;
	}

	/**
	 * @param tagList
	 *            the tagList to set
	 */
	public final void setTagList(final String tagList) {
		this.tagList = tagList;
	}

	/**
	 * @return the labelId
	 */
	public final String getLabelId() {
		return labelId;
	}

	/**
	 * @param labelId
	 *            the labelId to set
	 */
	public final void setLabelId(final String labelId) {
		this.labelId = labelId;
	}

	/**
	 * @return the labelName
	 */
	public final String getLabelName() {
		return labelName;
	}

	/**
	 * @param labelName
	 *            the labelName to set
	 */
	public final void setLabelName(final String labelName) {
		this.labelName = labelName;
	}

	/**
	 * @return the release
	 */
	public final String getRelease() {
		return release;
	}

	/**
	 * @param release
	 *            the release to set
	 */
	public final void setRelease(final String release) {
		this.release = release;
	}

	/**
	 * @return the releaseDay
	 */
	public final String getReleaseDay() {
		return releaseDay;
	}

	/**
	 * @param releaseDay
	 *            the releaseDay to set
	 */
	public final void setReleaseDay(final String releaseDay) {
		this.releaseDay = releaseDay;
	}

	/**
	 * @return the releaseMonth
	 */
	public final String getReleaseMonth() {
		return releaseMonth;
	}

	/**
	 * @param releaseMonth
	 *            the releaseMonth to set
	 */
	public final void setReleaseMonth(final String releaseMonth) {
		this.releaseMonth = releaseMonth;
	}

	/**
	 * @return the releaseYear
	 */
	public final String getReleaseYear() {
		return releaseYear;
	}

	/**
	 * @param releaseYear
	 *            the releaseYear to set
	 */
	public final void setReleaseYear(final String releaseYear) {
		this.releaseYear = releaseYear;
	}

	/**
	 * @return the streamable
	 */
	public boolean isStreamable() {
		return streamable;
	}

	/**
	 * @param streamable
	 *            the streamable to set
	 */
	public void setStreamable(boolean streamable) {
		this.streamable = streamable;
	}

	/**
	 * @return the downloadable
	 */
	public boolean isDownloadable() {
		return downloadable;
	}

	/**
	 * @param downloadable
	 *            the downloadable to set
	 */
	public void setDownloadable(boolean downloadable) {
		this.downloadable = downloadable;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the license
	 */
	public String getLicense() {
		return license;
	}

	/**
	 * @param license
	 *            the license to set
	 */
	public void setLicense(String license) {
		this.license = license;
	}

	/**
	 * @return the downloadUrl
	 */
	public String getDownloadUrl() {
		return downloadUrl;
	}

	/**
	 * @param downloadUrl
	 *            the downloadUrl to set
	 */
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	/**
	 * @return the streamUrl
	 */
	public String getStreamUrl() {
		return streamUrl;
	}

	/**
	 * @param streamUrl
	 *            the streamUrl to set
	 */
	public void setStreamUrl(String streamUrl) {
		this.streamUrl = streamUrl;
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
		result = prime * result + (artworkUrl == null ? 0 : artworkUrl.hashCode());
		result = prime * result + (createdAt == null ? 0 : createdAt.hashCode());
		result = prime * result + (description == null ? 0 : description.hashCode());
		result = prime * result + (downloadUrl == null ? 0 : downloadUrl.hashCode());
		result = prime * result + (downloadable ? 1231 : 1237);
		result = prime * result + (int) (duration ^ duration >>> 32);
		result = prime * result + (embeddableBy == null ? 0 : embeddableBy.hashCode());
		result = prime * result + (genre == null ? 0 : genre.hashCode());
		result = prime * result + id;
		result = prime * result + (labelId == null ? 0 : labelId.hashCode());
		result = prime * result + (labelName == null ? 0 : labelName.hashCode());
		result = prime * result + (license == null ? 0 : license.hashCode());
		result = prime * result + (permalink == null ? 0 : permalink.hashCode());
		result = prime * result + (permalinkUrl == null ? 0 : permalinkUrl.hashCode());
		result = prime * result + (purchaseUrl == null ? 0 : purchaseUrl.hashCode());
		result = prime * result + (release == null ? 0 : release.hashCode());
		result = prime * result + (releaseDay == null ? 0 : releaseDay.hashCode());
		result = prime * result + (releaseMonth == null ? 0 : releaseMonth.hashCode());
		result = prime * result + (releaseYear == null ? 0 : releaseYear.hashCode());
		result = prime * result + sharedToCount;
		result = prime * result + (sharing == null ? 0 : sharing.hashCode());
		result = prime * result + (state == null ? 0 : state.hashCode());
		result = prime * result + (streamUrl == null ? 0 : streamUrl.hashCode());
		result = prime * result + (streamable ? 1231 : 1237);
		result = prime * result + (tagList == null ? 0 : tagList.hashCode());
		result = prime * result + (title == null ? 0 : title.hashCode());
		result = prime * result + (uri == null ? 0 : uri.hashCode());
		result = prime * result + (user == null ? 0 : user.hashCode());
		result = prime * result + (userId == null ? 0 : userId.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Track)) {
			return false;
		}
		final Track other = (Track) obj;
		if (artworkUrl == null) {
			if (other.artworkUrl != null) {
				return false;
			}
		} else if (!artworkUrl.equals(other.artworkUrl)) {
			return false;
		}
		if (createdAt == null) {
			if (other.createdAt != null) {
				return false;
			}
		} else if (!createdAt.equals(other.createdAt)) {
			return false;
		}
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (downloadUrl == null) {
			if (other.downloadUrl != null) {
				return false;
			}
		} else if (!downloadUrl.equals(other.downloadUrl)) {
			return false;
		}
		if (downloadable != other.downloadable) {
			return false;
		}
		if (duration != other.duration) {
			return false;
		}
		if (embeddableBy == null) {
			if (other.embeddableBy != null) {
				return false;
			}
		} else if (!embeddableBy.equals(other.embeddableBy)) {
			return false;
		}
		if (genre == null) {
			if (other.genre != null) {
				return false;
			}
		} else if (!genre.equals(other.genre)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (labelId == null) {
			if (other.labelId != null) {
				return false;
			}
		} else if (!labelId.equals(other.labelId)) {
			return false;
		}
		if (labelName == null) {
			if (other.labelName != null) {
				return false;
			}
		} else if (!labelName.equals(other.labelName)) {
			return false;
		}
		if (license == null) {
			if (other.license != null) {
				return false;
			}
		} else if (!license.equals(other.license)) {
			return false;
		}
		if (permalink == null) {
			if (other.permalink != null) {
				return false;
			}
		} else if (!permalink.equals(other.permalink)) {
			return false;
		}
		if (permalinkUrl == null) {
			if (other.permalinkUrl != null) {
				return false;
			}
		} else if (!permalinkUrl.equals(other.permalinkUrl)) {
			return false;
		}
		if (purchaseUrl == null) {
			if (other.purchaseUrl != null) {
				return false;
			}
		} else if (!purchaseUrl.equals(other.purchaseUrl)) {
			return false;
		}
		if (release == null) {
			if (other.release != null) {
				return false;
			}
		} else if (!release.equals(other.release)) {
			return false;
		}
		if (releaseDay == null) {
			if (other.releaseDay != null) {
				return false;
			}
		} else if (!releaseDay.equals(other.releaseDay)) {
			return false;
		}
		if (releaseMonth == null) {
			if (other.releaseMonth != null) {
				return false;
			}
		} else if (!releaseMonth.equals(other.releaseMonth)) {
			return false;
		}
		if (releaseYear == null) {
			if (other.releaseYear != null) {
				return false;
			}
		} else if (!releaseYear.equals(other.releaseYear)) {
			return false;
		}
		if (sharedToCount != other.sharedToCount) {
			return false;
		}
		if (sharing == null) {
			if (other.sharing != null) {
				return false;
			}
		} else if (!sharing.equals(other.sharing)) {
			return false;
		}
		if (state == null) {
			if (other.state != null) {
				return false;
			}
		} else if (!state.equals(other.state)) {
			return false;
		}
		if (streamUrl == null) {
			if (other.streamUrl != null) {
				return false;
			}
		} else if (!streamUrl.equals(other.streamUrl)) {
			return false;
		}
		if (streamable != other.streamable) {
			return false;
		}
		if (tagList == null) {
			if (other.tagList != null) {
				return false;
			}
		} else if (!tagList.equals(other.tagList)) {
			return false;
		}
		if (title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!title.equals(other.title)) {
			return false;
		}
		if (uri == null) {
			if (other.uri != null) {
				return false;
			}
		} else if (!uri.equals(other.uri)) {
			return false;
		}
		if (user == null) {
			if (other.user != null) {
				return false;
			}
		} else if (!user.equals(other.user)) {
			return false;
		}
		if (userId == null) {
			if (other.userId != null) {
				return false;
			}
		} else if (!userId.equals(other.userId)) {
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
		return "Track [id=" + id + ", createdAt=" + createdAt + ", userId=" + userId + ", user=" + user + ", title=" + title + ", permalink="
				+ permalink + ", permalinkUrl=" + permalinkUrl + ", uri=" + uri + ", sharing=" + sharing + ", embeddableBy=" + embeddableBy
				+ ", purchaseUrl=" + purchaseUrl + ", artworkUrl=" + artworkUrl + ", description=" + description + ", duration=" + duration
				+ ", genre=" + genre + ", sharedToCount=" + sharedToCount + ", tagList=" + tagList + ", labelId=" + labelId + ", labelName="
				+ labelName + ", release=" + release + ", releaseDay=" + releaseDay + ", releaseMonth=" + releaseMonth + ", releaseYear="
				+ releaseYear + ", streamable=" + streamable + ", downloadable=" + downloadable + ", state=" + state + ", license=" + license
				+ ", downloadUrl=" + downloadUrl + ", streamUrl=" + streamUrl + "]";
	}
}
