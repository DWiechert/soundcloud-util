package com.github.dwiechert.sc.util.commands;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;

public class Test {
	public static void main(final String[] args) throws Exception {
		final Mp3File mp3file  = new Mp3File("S:\\Music\\MashPotatoes\\Fully Loaded\\Taio Shoes.mp3");
		System.out.println("Length of this mp3 is: " + mp3file.getLengthInSeconds() + " seconds");
		System.out.println("Bitrate: " + mp3file.getLengthInSeconds() + " kbps " + (mp3file.isVbr() ? "(VBR)" : "(CBR)"));
		System.out.println("Sample rate: " + mp3file.getSampleRate() + " Hz");
		System.out.println("Has ID3v1 tag?: " + (mp3file.hasId3v1Tag() ? "YES" : "NO"));
		System.out.println("Has ID3v2 tag?: " + (mp3file.hasId3v2Tag() ? "YES" : "NO"));
		System.out.println("Has custom tag?: " + (mp3file.hasCustomTag() ? "YES" : "NO"));
		
		final ID3v1 v1 = mp3file.getId3v1Tag();
		System.out.println(v1);
		
		final ID3v2 tag = mp3file.getId3v2Tag();
		System.out.println(tag);
	}
}
