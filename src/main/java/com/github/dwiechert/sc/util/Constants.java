package com.github.dwiechert.sc.util;

public final class Constants {
	/** The SoundClound Client ID. */
	public static final String CLIENT_ID = "76f8a199dfa11251adb82d17d2501777";

	/** The SoundClound Client Secret. */
	public static final String CLIENT_SECRET = "df5da35bce0e21efa7501f781d363308";

	/** The URL for getting tracks from a SoundCloud song. Must format it with a song id and client id. */
	public static final String TRACK_URL = "https://api.soundcloud.com/tracks/%d?client_id=%s";

	/** The URL for getting tracks from a SoundCloud artist. Must format it with an artist id and client id. */
	public static final String ARTIST_TRACK_URL = "https://api.soundcloud.com/users/%d/tracks.json?client_id=%s&limit=200";

	/** The URL for getting the user from a SoundCloud id. Must format it with a user id and client id. */
	public static final String ARTIST_URL = "https://api.soundcloud.com/users/%d?client_id=%s";
}
