package soundcloud.api.models;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

/**
 * Represents a SoundCloud user.
 * 
 * Class based off of the Json documentation found at <a href="http://developers.soundcloud.com/docs/api/reference#users">the users api</a>.
 * 
 * @author Dan Wiechert
 */
public class User implements Serializable {
	private static final long serialVersionUID = -7972695590016831836L;

	@SerializedName("id")
	private int id;

	@SerializedName("permalink")
	private String permalink;

	@SerializedName("username")
	private String username;

	@SerializedName("uri")
	private String uri;

	@SerializedName("permalink_url")
	private String permalinkUrl;

	@SerializedName("avatar_url")
	private String avatarUrl;

	/**
	 * Generic constructor for a User.
	 */
	public User() {

	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(final int id) {
		this.id = id;
	}

	/**
	 * @return the permalink
	 */
	public String getPermalink() {
		return permalink;
	}

	/**
	 * @param permalink
	 *            the permalink to set
	 */
	public void setPermalink(final String permalink) {
		this.permalink = permalink;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(final String username) {
		this.username = username;
	}

	/**
	 * @return the uri
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * @param uri
	 *            the uri to set
	 */
	public void setUri(final String uri) {
		this.uri = uri;
	}

	/**
	 * @return the permalinkUrl
	 */
	public String getPermalinkUrl() {
		return permalinkUrl;
	}

	/**
	 * @param permalinkUrl
	 *            the permalinkUrl to set
	 */
	public void setPermalinkUrl(final String permalinkUrl) {
		this.permalinkUrl = permalinkUrl;
	}

	/**
	 * @return the avatarUrl
	 */
	public String getAvatarUrl() {
		return avatarUrl;
	}

	/**
	 * @param avatarUrl
	 *            the avatarUrl to set
	 */
	public void setAvatarUrl(final String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((avatarUrl == null) ? 0 : avatarUrl.hashCode());
		result = prime * result + id;
		result = prime * result + ((permalink == null) ? 0 : permalink.hashCode());
		result = prime * result + ((permalinkUrl == null) ? 0 : permalinkUrl.hashCode());
		result = prime * result + ((uri == null) ? 0 : uri.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

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
		final User other = (User) obj;
		if (avatarUrl == null) {
			if (other.avatarUrl != null) {
				return false;
			}
		} else if (!avatarUrl.equals(other.avatarUrl)) {
			return false;
		}
		if (id != other.id) {
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
		if (uri == null) {
			if (other.uri != null) {
				return false;
			}
		} else if (!uri.equals(other.uri)) {
			return false;
		}
		if (username == null) {
			if (other.username != null) {
				return false;
			}
		} else if (!username.equals(other.username)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", " + (permalink != null ? "permalink=" + permalink + ", " : "")
				+ (username != null ? "username=" + username + ", " : "") + (uri != null ? "uri=" + uri + ", " : "")
				+ (permalinkUrl != null ? "permalinkUrl=" + permalinkUrl + ", " : "") + (avatarUrl != null ? "avatarUrl=" + avatarUrl : "") + "]";
	}
}
