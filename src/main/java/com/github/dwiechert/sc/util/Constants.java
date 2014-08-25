package com.github.dwiechert.sc.util;

public final class Constants {
	/** The SoundClound Client ID. */
	public static final String CLIENT_ID = "76f8a199dfa11251adb82d17d2501777";

	/** The SoundClound Client Secret. */
	public static final String CLIENT_SECRET = "df5da35bce0e21efa7501f781d363308";

	/** The SoundClound login username. */
	public static final String SOUNDCLOUD_USERNAME = "mashpotatoemusic";

	/** The SoundClound login password. */
	public static final String SOUNDCLOUD_PASSWORD = "1535irving";

	/** The URL for getting tracks from a SoundCloud song. Must format it with a song id and client id. */
	public static final String TRACK_URL = "https://api.soundcloud.com/tracks/%s?client_id=%s";

	/** The URL for getting tracks from a SoundCloud artist. Must format it with an artist id and client id. */
	public static final String ARTIST_TRACK_URL = "https://api.soundcloud.com/users/%d/tracks?client_id=%s";
}
