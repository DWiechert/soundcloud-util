#soundcloud-util

*Command line utility that allows downloading and syncing of music files from SoundCloud.*

## Table of Contents
* **[Overview](#overview)**
* **[download](#download)**
* **[syncConfig](#syncConfig)**
* **[syncCheck](#syncCheck)**
* **[sync](#sync)**

## Overview
This is a command line utility to download and sync songs from SoundCloud to your local disk. This utility **requires Java 8 to run**.

### download
Allows the downloading of indiviaul song(s) or all songs by individual artist(s).
Accepted arguments:
* `-s` - Song url - can be used multiple times.
* `-a` - Artist url - can be used multiple times.
* `-o` - Output folder - Folder to download songs to. Defaults to current directory.
Example Execution:
```
java -jar soundcloud-util-<version>-jar-with-dependencies.jar download -s <songUrl> -a <artistUrl> -o <outputFolder>
```

### syncConfig

### syncCheck

### sync
