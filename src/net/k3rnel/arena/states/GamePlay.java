package net.k3rnel.arena.states;

import net.k3rnel.arena.entity.OurPlayer;
import net.k3rnel.arena.entity.OurPlayer.Direction;
import net.k3rnel.arena.entity.Scene;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMapPlus;

/**
 * The GamePlay Screen. 
 * It's a BasicGameState as used by Slick2d. 
 * 
 * Shows the Map and lets your character walk around on-screen
 * 
 * @author Nushio
 *
 */
public class GamePlay extends BasicGameState{

    // A Unique ID so that the GameClient knows which state we're in. 
    int stateID = 2; //Tip: It's not Guadalajara. It's never Guadalajara.
    private OurPlayer player;
    private Scene scene;
    int tmp1, tmp2, tmp3, tmp4;
    
    /**
     * Initializes the StateID
     * @param stateID
     */
    public GamePlay (int stateID ){
        this.stateID = stateID;
    }

    /**
     * Initializes the GamePlay
     * This is where we set up the active map, player, etc.  
     */
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        scene = new Scene(new TiledMapPlus("res/maps/beach.tmx"));
        player = new OurPlayer(new SpriteSheet("res/sprites/male_walkcycle.png",64,64,0));
        player.setPos_x(370);
        player.setPos_y(250);
    }


    /**
     * This makes things appear on screen, after we've initialized the variables
     */
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics gfx)
            throws SlickException {
        TiledMapPlus activeMap = scene.getActiveMap();
        for(int i = 0; i< scene.getBelow().size(); i++){
            activeMap.render((int)scene.getCamera_x(),(int)scene.getCamera_y(), scene.getBelow().get(i));
        }
        if(player.getDirection().equals(Direction.RIGHT))
            tmp1 = ((((int)player.getPos_x()+42)+(int)scene.getCamera_x()*-1) / Scene.getSIZE());
        if(player.getDirection().equals(Direction.LEFT))
            tmp1 = ((((int)player.getPos_x()+20)+(int)scene.getCamera_x()*-1) / Scene.getSIZE());
        if(player.getDirection().equals(Direction.DOWN))
            tmp2 = ((((int)player.getPos_y()+30)+(int)scene.getCamera_y()*-1) / Scene.getSIZE());
        if(player.getDirection().equals(Direction.UP))
        tmp2 = ((((int)player.getPos_y()+32)+(int)scene.getCamera_y()*-1) / Scene.getSIZE());
        player.getSprite().draw((int)player.getPos_x(), (int)player.getPos_y());
        
        for(int i = 0; i< scene.getAbove().size(); i++){
            activeMap.render((int)scene.getCamera_x(),(int)scene.getCamera_y(), scene.getAbove().get(i));
        }
        gfx.drawString("POS: "+tmp1+"/"+tmp2, 550, 550);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();
        if (input.isKeyDown(Input.KEY_UP)||input.isKeyDown(Input.KEY_W)){
            player.setDirection(Direction.UP);
            player.getSprite().update(delta);
            player.setTile_y(player.getTile_y()-1);
            scene.setCamera_y(scene.getCamera_y()+32*0.1f);
        } else if(input.isKeyDown(Input.KEY_DOWN)||input.isKeyDown(Input.KEY_S)){
            player.setDirection(Direction.DOWN);
            player.getSprite().update(delta);
            player.setTile_y(player.getTile_y()+1);
            scene.setCamera_y(scene.getCamera_y()-32*0.1f);
        } else if(input.isKeyDown(Input.KEY_LEFT)||input.isKeyDown(Input.KEY_A)){
            player.setDirection(Direction.LEFT);
            player.getSprite().update(delta);
            player.setTile_x(player.getTile_x()-1);
            scene.setCamera_x(scene.getCamera_x()+32*0.1f);
        } else if(input.isKeyDown(Input.KEY_RIGHT)||input.isKeyDown(Input.KEY_D)){
            player.setDirection(Direction.RIGHT);
            player.getSprite().update(delta);
            player.setTile_x(player.getTile_x()+1);
            scene.setCamera_x(scene.getCamera_x()-32*0.1f);
        }
    }

    /**
     * Returns the StateID. I'm Serious. 
     * @return stateID
     */
    @Override
    public int getID() {
        return stateID;
    }
}
