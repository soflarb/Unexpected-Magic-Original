package com.mygdx.game.gameEngine.managers;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.gameEngine.sound.Synth;
import com.mygdx.game.gameEngine.systems.MovementSystem;
import com.mygdx.game.gameEngine.systems.SoundSystem;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.song.INote;
import com.mygdx.game.model.song.ISong;
import com.mygdx.game.model.song.IVoice;
import com.mygdx.game.model.song.Voice;

/**
 * Class for managing entities.
 * @author soflarb
 * Revised by car0b1nius
 */
public class EntityManager {
	private Engine engine;
	private SpriteBatch batch;
	private ISong song;
	private List<Player> players;


	public EntityManager(Engine engine, SpriteBatch batch, ISong song, List<Player> players){
		this.engine = engine;
		this.batch = batch;
		this.song = song;
		this.players = players;

		//Create all the systems
		MovementSystem movementSystem = new MovementSystem();
		SoundSystem soundSystem = new SoundSystem(new Synth());
		
		//Add all the systems to the engine
		this.engine.addSystem(movementSystem);
		this.engine.addSystem(soundSystem);
	}

	public void update(int tick){
		manageNoteEntities(tick);
	}
	int prevTick = -1;
	Queue<Entity> noteEntityQueue = new LinkedList<>();
	private void manageNoteEntities(int tick) {
		//check each voice if create new note should be created and create it
		for(int i = prevTick+1; i <= tick; i++) {
			for (Player player : players) {

				IVoice voice = player.getVoice();

					if (i >= voice.length()) {
						noteEntityQueue.add(null);
						continue;
					}
					INote note = voice.noteAtTick(i);
					if (note == null || note.isRest()) {
						noteEntityQueue.add(null);
						continue;
					}

					Entity newNoteEntity = EntityFactory.createNoteEntity(note, voice, players.indexOf(player));
					engine.addEntity(newNoteEntity);
					noteEntityQueue.add(newNoteEntity);
				}
				//while there are more than 20 notes/voice, ...
				while (noteEntityQueue.size() > song.getVoices().length * 150) { // TODO tick length
					//remove the oldest one from both queue and engine.
					Entity e = noteEntityQueue.poll();
					if (e == null) continue;
					engine.removeEntity(e);
				}
			}
		prevTick = tick;
	}


}
