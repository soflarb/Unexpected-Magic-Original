package com.mygdx.game.gameEngine.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.mygdx.game.gameEngine.components.NoteComponent;
import com.mygdx.game.gameEngine.components.PositionComponent;
import com.mygdx.game.gameEngine.managers.SoundManager;

/**
 * Created by rasmus on 2017-05-05.
 */
public class SoundSystem extends IteratingSystem{

    private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
    private ComponentMapper<NoteComponent> nm = ComponentMapper.getFor(NoteComponent.class);
    private SoundManager soundManager;

    public SoundSystem(SoundManager s) {
        super(Family.all(PositionComponent.class, NoteComponent.class).get());
        this.soundManager = s;
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PositionComponent pos = pm.get(entity);
        NoteComponent not = nm.get(entity);
        if(pos.y < 100 && pos.y > 95){ //TODO THIS IS NOT ACCURATE
            soundManager.play(not.note);
        }
    }
}
