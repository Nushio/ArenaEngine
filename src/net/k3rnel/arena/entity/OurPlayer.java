package net.k3rnel.arena.entity;

import java.util.HashMap;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;

/**
 * Represents Our Player. 
 * The hero of life, the universe and everything.
 * 
 * @author Nushio
 *
 */
public class OurPlayer {

    public static enum Direction {UP, DOWN, LEFT, RIGHT}
    public enum Type {PLAYER, NPC, CHEST}
    
    private Type type;
    private SpriteSheet spriteSheet;
    private static int offset_x = -16;
    private static int offset_y = -32;
    private float tile_x;
    private float tile_y;
    private Animation sprite;
    private HashMap<Direction,Animation> animations = new HashMap<Direction,Animation>();
    private Direction direction;
    private boolean isMoving;
    
    
    
    public OurPlayer(SpriteSheet spriteSheet) {
       setSpriteSheet(spriteSheet);
       setDirection(Direction.DOWN);
       System.out.println(spriteSheet.getVerticalCount());
       if(spriteSheet.getVerticalCount()==4){
           Animation up = new Animation(false);
           for (int frame=0;frame<9;frame++) {
               up.addFrame(spriteSheet.getSprite(frame,0), 64);
           }
           animations.put(OurPlayer.Direction.UP, up);
           Animation left = new Animation(false);
           for (int frame=0;frame<9;frame++) {
               left.addFrame(spriteSheet.getSprite(frame,1), 64);
           }
           animations.put(OurPlayer.Direction.LEFT, left);
           Animation down = new Animation(false);
           for (int frame=0;frame<9;frame++) {
               down.addFrame(spriteSheet.getSprite(frame,2), 64);
           }
           animations.put(OurPlayer.Direction.DOWN, down);
           Animation right = new Animation(false);
           for (int frame=0;frame<9;frame++) {
               right.addFrame(spriteSheet.getSprite(frame,3), 64);
           }
           animations.put(OurPlayer.Direction.RIGHT, right);
           
           sprite = down;
       }
       
    }
    
    /**
     * @return the spriteSheet
     */
    public SpriteSheet getSpriteSheet() {
        return spriteSheet;
    }
    /**
     * @param spriteSheet the spriteSheet to set
     */
    public void setSpriteSheet(SpriteSheet spriteSheet) {
        this.spriteSheet = spriteSheet;
    }
    /**
     * @return the sprite
     */
    public Animation getSprite() {
        try{
            if(this.direction!=null){
                if(this.direction == Direction.UP){
                    sprite = animations.get(Direction.UP);
                }else if(this.direction == Direction.DOWN){
                    sprite = animations.get(Direction.DOWN);
                }else if(this.direction == Direction.LEFT){
                    sprite = animations.get(Direction.LEFT);
                }else if(this.direction == Direction.RIGHT){
                    sprite = animations.get(Direction.RIGHT);
                }else{
                    sprite = animations.get(Direction.DOWN);
                }
                
            }else{
                sprite = animations.get(Direction.DOWN);
            }
        }catch(Exception e){
            System.out.println("Sprite failed to load!");
        }
        return sprite;
    }
    /**
     * @param sprite the sprite to set
     */
    public void setSprite(Animation sprite) {
        this.sprite = sprite;
    }
    /**
     * @return the direction
     */
    public Direction getDirection() {
        return direction;
    }
    /**
     * @param direction the direction to set
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    /**
     * @return the isMoving
     */
    public boolean isMoving() {
        return isMoving;
    }
    /**
     * @param isMoving the isMoving to set
     */
    public void setMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }
    /**
     * @return the type
     */
    public Type getType() {
        return type;
    }
    /**
     * @param type the type to set
     */
    public void setType(Type type) {
        this.type = type;
    }
    /**
     * @return the animations
     */
    public HashMap<Direction, Animation> getAnimations() {
        return animations;
    }
    /**
     * @param animations the animations to set
     */
    public void setAnimations(HashMap<Direction, Animation> animations) {
        this.animations = animations;
    }
    /**
     * @return the tile_x
     */
    public float getTile_x() {
        return tile_x;
    }
    /**
     * @param tile_x the tile_x to set
     */
    public void setTile_x(float tile_x) {
        this.tile_x = tile_x;
    }
    /**
     * @return the tile_y
     */
    public float getTile_y() {
        return tile_y;
    }
    /**
     * @param tile_y the tile_y to set
     */
    public void setTile_y(float tile_y) {
        this.tile_y = tile_y;
    }

    /**
     * @return the offset_x
     */
    public int getOffset_x() {
        return offset_x;
    }


    /**
     * @return the offset_y
     */
    public int getOffset_y() {
        return offset_y;
    }

}
