package net.k3rnel.arena.entity;

import java.util.HashMap;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;

/**
 * Represents a Game Object. 
 * Perhaps a Chest or a Barrel
 * 
 * @author Nushio
 *
 */
public class GameObject {

    public static enum Direction {UP, DOWN, LEFT, RIGHT}
    public enum Type {NPC, CHEST}
    
    private Type type;
    private SpriteSheet spriteSheet;
    private float pos_x;
    private float pos_y;
    private int tile_x;
    private int tile_y;
    private Animation sprite;
    private HashMap<Direction,Animation> animations = new HashMap<Direction,Animation>();
    private Direction direction;
    private boolean isMoving;
    
    
    
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
     * @return the pos_x
     */
    public float getPos_x() {
        return pos_x;
    }
    /**
     * @param pos_x the pos_x to set
     */
    public void setPos_x(float pos_x) {
        this.pos_x = pos_x;
    }
    /**
     * @return the pos_y
     */
    public float getPos_y() {
        return pos_y;
    }
    /**
     * @param pos_y the pos_y to set
     */
    public void setPos_y(float pos_y) {
        this.pos_y = pos_y;
    }
    /**
     * @return the tile_x
     */
    public int getTile_x() {
        return tile_x;
    }
    /**
     * @param tile_x the tile_x to set
     */
    public void setTile_x(int tile_x) {
        this.tile_x = tile_x;
    }
    /**
     * @return the tile_y
     */
    public int getTile_y() {
        return tile_y;
    }
    /**
     * @param tile_y the tile_y to set
     */
    public void setTile_y(int tile_y) {
        this.tile_y = tile_y;
    }
}
