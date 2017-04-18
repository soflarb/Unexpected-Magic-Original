package com.mygdx.game.model.song;

import java.io.IOException;
import java.util.Queue;

import com.mygdx.game.services.file.FileReader;

public class Song {
	public final String title;
	public final int bpm;
	private final int[] time;
	private final Voice[] voices;
	public Song(String path) throws IOException {
		Queue<String> sList = FileReader.readUXM(path);
		title = sList.poll().replaceAll("\"", "");
		String[] t = sList.poll().replaceAll("\"", "").split("/", 2);
		time = new int[]{Integer.parseInt(t[0]), Integer.parseInt(t[1])};
		bpm = Integer.parseInt(sList.poll());
		voices = new Voice[sList.size()];
		int i = 0;
		for(String s : sList) {
			voices[i++] = new Voice(s, null);
		}
	}
	public Voice[] getVoices() {
		return voices.clone();
	}
	public int[] getTime() {
		return time.clone();
	}
}
