package net.k3rnel.arena.entity;

import java.util.ArrayList;

import org.newdawn.slick.tiled.TiledMapPlus;

public class Scene {
    
    private TiledMapPlus activeMap;
    private float camera_x;
    private float camera_y;
    private boolean[][] blocked;
    private ArrayList<Integer> above;
    private ArrayList<Integer> below;
    private static int SIZE = 16;
    
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
        //Twice the size for twice the flavor. Now using Subtiles!
        this.blocked = new boolean[activeMap.getWidth()*2][activeMap.getHeight()*2];
//        for (int xAxis=0;xAxis<activeMap.getWidth()*2; xAxis++)
//        {
//            for (int yAxis=0;yAxis<activeMap.getHeight()*2; yAxis++)
//            {
//              blocked[xAxis][yAxis] = -1;  
//            }
//        }

        for (int xAxis=0;xAxis<activeMap.getWidth(); xAxis++)
        {
            for (int yAxis=0;yAxis<activeMap.getHeight(); yAxis++)
            {
                for(int i = 0; i<activeMap.getLayerCount();i++){
                    int tileID = activeMap.getTileId(xAxis, yAxis, i);
                    String value = activeMap.getTileProperty(tileID, "wall", "-1");
                    if ("a".equals(value)){
                        blocked[xAxis*2][yAxis*2] = true;
                        blocked[(xAxis*2) + 1][yAxis*2] = true;
                        blocked[xAxis*2][(yAxis*2) + 1] = true;
                        blocked[(xAxis*2) + 1][yAxis*2 + 1] = true;
                    }
                    else if ("n".equals(value)){
                        blocked[xAxis*2][yAxis*2] = true;
                        blocked[(xAxis*2)+1][(yAxis*2)] = true;
                    }else if ("s".equals(value)){
                        blocked[xAxis*2][(yAxis*2)+1] = true;
                        blocked[xAxis*2+1][(yAxis*2) + 1] = true;
                    }else if ("e".equals(value)){
                        blocked[(xAxis*2)][(yAxis*2) + 1] = true;
                        blocked[(xAxis*2) + 1][(yAxis*2) + 1] = true;
                    }else if ("w".equals(value)){
                        blocked[(xAxis*2)][(yAxis*2)] = true;
                        blocked[(xAxis*2)][(yAxis*2) + 1] = true;
                    }else if ("nw".equals(value)){
                        blocked[(xAxis*2)][(yAxis*2)] = true;
                    }else if ("ne".equals(value)){
                        blocked[(xAxis*2)+1][(yAxis*2)] = true;
                    }else if ("sw".equals(value)){
                        blocked[(xAxis*2)+1][(yAxis*2)] = true;
                    }else if ("se".equals(value)){
                        blocked[(xAxis*2)+1][(yAxis*2)+1] = true;
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
    public boolean isBlocked(int x, int y) {
        boolean block = false;
        System.out.println(x+"/"+y);
        try{
            block = blocked[x][y];
        }catch(Exception e){
            block = false;
        }
        if(x<=-1||y<=-1||x>=128||y>=128)
            block=true;
        System.out.println(block);
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
     * @return the SIZE
     */
    public static int getSIZE() {
        return SIZE;
    }

}
