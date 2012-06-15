package net.k3rnel.arena.entity;

import java.util.ArrayList;

import org.newdawn.slick.tiled.TiledMapPlus;

public class Scene {
    
    private TiledMapPlus activeMap;
    private float camera_x;
    private float camera_y;
    private int[][] blocked;
    private ArrayList<Integer> above;
    private ArrayList<Integer> below;
    private static int SIZE = 32;
    
    public Scene(TiledMapPlus tiledMap) {
        setActiveMap(tiledMap);
    }
    
    /**
     * @return the activeMap
     */
    public TiledMapPlus getActiveMap() {
        return activeMap;
    }
    /**
     * @param activeMap the activeMap to set
     */
    public void setActiveMap(TiledMapPlus tiledMap) {
        this.activeMap = tiledMap;
        this.blocked = new int[activeMap.getWidth()][activeMap.getHeight()];
        for (int xAxis=0;xAxis<activeMap.getWidth(); xAxis++)
        {
            for (int yAxis=0;yAxis<activeMap.getHeight(); yAxis++)
            {
              blocked[xAxis][yAxis] = -1;  
            }
        }

        for (int xAxis=0;xAxis<activeMap.getWidth(); xAxis++)
        {
            for (int yAxis=0;yAxis<activeMap.getHeight(); yAxis++)
            {
                for(int i = 0; i<activeMap.getLayerCount();i++){
                    int tileID = activeMap.getTileId(xAxis, yAxis, i);
                    String value = activeMap.getTileProperty(tileID, "wall", "fake");
                    if ("a".equals(value)){
                        blocked[xAxis][yAxis] = 0;
                    }else if ("n".equals(value)){
                        blocked[xAxis][yAxis] = 1;
                    }else if ("s".equals(value)){
                        blocked[xAxis][yAxis] = 2;
                    }else if ("e".equals(value)){
                        blocked[xAxis][yAxis] = 3;
                    }else if ("w".equals(value)){
                        blocked[xAxis][yAxis] = 4;
                    }else if ("nw".equals(value)){
                        blocked[xAxis][yAxis] = 5;
                    }else if ("ne".equals(value)){
                        blocked[xAxis][yAxis] = 6;
                    }else if ("sw".equals(value)){
                        blocked[xAxis][yAxis] = 7;
                    }else if ("se".equals(value)){
                        blocked[xAxis][yAxis] = 8;
                    }
                }
            }
        }
        below = new ArrayList<Integer>();
        above = new ArrayList<Integer>();
        for(int i = 0; i < activeMap.getLayerCount();i++){
            String value = activeMap.getLayerProperty(i, "target","below");
            if(value.equals("above")){
                above.add(i);
            }else{
                below.add(i);
            }
        }
    }
   
    /**
     * Checks collisions on x, y
     * @param x
     * @param y
     * @return
     */
    private int isBlocked(float x, float y) {
        int xBlock = (int)x / SIZE;
        int yBlock = (int)y / SIZE;
        int block = -1;
        try{
            block = blocked[xBlock][yBlock];
        }catch(Exception e){
            block = -1;
        }
        if(xBlock<=0||yBlock<=0||xBlock>=64||yBlock>=64)
            block=0;
        return block;
    }
    
    
    /**
     * @return the camera_x
     */
    public float getCamera_x() {
        return camera_x;
    }
    /**
     * @param camera_x the camera_x to set
     */
    public void setCamera_x(float camera_x) {
        this.camera_x = camera_x;
    }
    /**
     * @return the camera_y
     */
    public float getCamera_y() {
        return camera_y;
    }
    /**
     * @param camera_y the camera_y to set
     */
    public void setCamera_y(float camera_y) {
        this.camera_y = camera_y;
    }
    /**
     * @return the blocked
     */
    public int[][] getBlocked() {
        return blocked;
    }
    /**
     * @param blocked the blocked to set
     */
    public void setBlocked(int[][] blocked) {
        this.blocked = blocked;
    }
    /**
     * @return the above
     */
    public ArrayList<Integer> getAbove() {
        return above;
    }
    /**
     * @param above the above to set
     */
    public void setAbove(ArrayList<Integer> above) {
        this.above = above;
    }
    /**
     * @return the below
     */
    public ArrayList<Integer> getBelow() {
        return below;
    }
    /**
     * @param below the below to set
     */
    public void setBelow(ArrayList<Integer> below) {
        this.below = below;
    }
    /**
     * @return the sIZE
     */
    public static int getSIZE() {
        return SIZE;
    }
    /**
     * @param sIZE the sIZE to set
     */
    public static void setSIZE(int sIZE) {
        SIZE = sIZE;
    }

}
