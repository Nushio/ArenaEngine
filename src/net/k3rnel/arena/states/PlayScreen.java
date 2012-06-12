package net.k3rnel.arena.states;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMapPlus;

/**
 * The Play Screen. 
 * It's a BasicGameState as used by Slick2d. 
 * 
 * Shows the Map and lets your character walk around on-screen
 * 
 * @author Nushio
 *
 */
public class PlayScreen extends BasicGameState {

    // A Unique ID so that the GameClient knows which state we're in. 
    int stateID = 2; //Tip: It's not Machupichu. It's never Machupichu. 

    private TiledMapPlus activeMap;
    private Animation player, up, down, left, right;
    private float x = 350, y = 272;
    private float camerax = -680;
    private float cameray = -680;
    private float tmppx = 0, tmppy = 0;
    private float nextx = 0, nexty = 0;
    private int[][] blocked;
    private ArrayList<Integer> ontop;
    private ArrayList<Integer> below;
    private int SIZE = 32;
    
    /**
     * Initializes the StateID
     * @param stateID
     */
    public PlayScreen (int stateID ){
        this.stateID = stateID;
    }

    /**
     * Returns the StateID. I'm Serious. 
     * @return stateID
     */
    @Override
    public int getID() {
        return stateID;
    }
    int i = 0;
    /**
     * Initializes the Main Menu
     * This is where we set up the background, as well as the menu options. 
     */
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        activeMap = new TiledMapPlus("res/maps/beach.tmx");
        SpriteSheet playerSheet = new SpriteSheet("res/sprites/male_walkcycle.png",64,64,0);

        up = new Animation();
        down = new Animation();
        left = new Animation();
        right = new Animation();
        for (int frame=0;frame<9;frame++) {
            up.addFrame(playerSheet.getSprite(frame,0), 64);
        }
        for (int frame=0;frame<9;frame++) {
            left.addFrame(playerSheet.getSprite(frame,1), 64);
        }
        for (int frame=0;frame<9;frame++) {
            down.addFrame(playerSheet.getSprite(frame,2), 64);
        }
        for (int frame=0;frame<9;frame++) {
            right.addFrame(playerSheet.getSprite(frame,3), 64);
        }
        player = down;

        blocked = new int[activeMap.getWidth()][activeMap.getHeight()];
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
        ontop = new ArrayList<Integer>();
        for(int i = 0; i < activeMap.getLayerCount();i++){
            String value = activeMap.getLayerProperty(i, "target","below");
            if(value.equals("above")){
                ontop.add(i);
            }else{
                below.add(i);
            }
        }
    }

    /**
     * This makes things appear on screen, after we've initialized the variables
     */
    public void render(GameContainer gc, StateBasedGame sbg, Graphics gx) throws SlickException {
        for(int i = 0; i< below.size(); i++){
            activeMap.render((int)camerax,(int)cameray, below.get(i));
        }
            player.draw((int)x, (int)y);
        for(int i = 0; i< ontop.size(); i++){
            activeMap.render((int)camerax,(int)cameray, ontop.get(i));
        }
        player.setAutoUpdate(false);
        gx.drawString("POS: "+((((int)x+player.getWidth()/2)+(int)camerax*-1) / SIZE)+"/"+((((int)y+player.getHeight()/2)+(int)cameray*-1) / SIZE),550,550);
        gx.drawString("TMP: "+tmppx+"/"+tmppy,550,565);
        gx.drawString("NEXT: "+nextx+"/"+nexty,550,580);
//        gx.drawString("PLAYER: "+(player.getWidth())+"/"+(player.getHeight()+1),550,580);
    }

    public void update(GameContainer gc, StateBasedGame sb, int delta) throws SlickException {
        Input input = gc.getInput();
//        delta = 420;
        if (input.isKeyDown(Input.KEY_UP)||input.isKeyDown(Input.KEY_W))
        {
            player = up;
            player.update(delta);
            int blocked = isBlocked(((((int)x+player.getWidth()/2)+(int)camerax*-1)), (((((int)y - 10 * 0.1f)+player.getHeight()/2)+(int)cameray*-1)));
            if (blocked != 0 ){
                // The lower the delta the slowest the sprite will animate.
                float tmpx = (float)((((((int)x+ 10 * 0.1f)+player.getWidth()/2)+(int)camerax*-1))/32);
                float tmpy = (float)((((((int)y+ 10 * 0.1f)+player.getHeight())+(int)cameray*-1))/32);
                tmppx = tmpx;
                tmppy = tmpy;
                if(blocked == 1){
                    y -= 1 * 0.1f;
                    cameray +=delta * 0.1f;
                }else{
                    y -= 10 * 0.1f;
                    cameray +=delta * 0.1f; 
                }
            }
        }else if (input.isKeyDown(Input.KEY_DOWN)||input.isKeyDown(Input.KEY_S)){
            player = down;
            player.update(delta);
            int blocked =isBlocked(((((int)x+player.getWidth()/2)+(int)camerax*-1)), (((((int)y+ 10 * 0.1f)+player.getHeight())+(int)cameray*-1))); 
            if (blocked != 0){
                float tmpx = (float)((((((int)x+ 10 * 0.1f)+player.getWidth()/2)+(int)camerax*-1))/32);
                float tmpy = (float)((((((int)y+ 10 * 0.1f)+player.getHeight())+(int)cameray*-1))/32);
                tmppx = tmpx - (int)tmpx;
                tmppy = tmpy - (int)tmpy;
                if((blocked == 2) && (tmpy-(int)tmpy)<0.2){
                    y += 10 * 0.1f;
                    cameray -= delta * 0.1f;
                }else if((blocked == 8) && (tmpx-(int)tmpx)<0.47 && (tmpy-(int)tmpy)<0.7){
                    y += 10 * 0.1f;
                    cameray -= delta * 0.1f;
                }else if((blocked == 8) && (tmpx-(int)tmpx)>.5 && (tmpy-(int)tmpy)<0.3){
                    y += 10 * 0.1f;
                    cameray -= delta * 0.1f;
                }else if((blocked == 7) && (tmpx-(int)tmpx)>0.47 && (tmpy-(int)tmpy)>0.7){
                    y += 10 * 0.1f;
                    cameray -= delta * 0.1f;
                }else if((blocked == 7) && (tmpx-(int)tmpx)<.5 && (tmpy-(int)tmpy)>0.3){
                    y += 10 * 0.1f;
                    cameray -= delta * 0.1f;
                }else if(blocked!=2 && blocked!=8 && blocked != 0){
                    y += 10 * 0.1f;
                    cameray -= delta * 0.1f;
                }
            }
        }
        else if (input.isKeyDown(Input.KEY_LEFT)||input.isKeyDown(Input.KEY_A))
        {
            player = left;
            player.update(delta);
            int blocked =isBlocked((((((int)x- 10 * 0.1f)+player.getWidth()/3)+(int)camerax*-1)), (((((int)y-10 * 0.1f)+60)+(int)cameray*-1)));
            if (blocked != 0){
                float tmpx = (float)((((((int)x+ 10 * 0.1f)+player.getWidth()/2)+(int)camerax*-1))/32);
                float tmpy = (float)((((((int)y+ 10 * 0.1f)+player.getHeight())+(int)cameray*-1))/32);
                tmppx = tmpx - (int)tmpx;
                tmppy = tmpy - (int)tmpy;
                if((blocked == 4) && (tmpy-(int)tmpy)<0.2){
                    x -= 10 * 0.1f;
                    camerax += delta * 0.1f;
                }else if((blocked == 7) && (tmpx-(int)tmpx)<0.4  && ((tmpy-(int)tmpy)>0.7||(tmpy-(int)tmpy)<0.4)){ 
                    x += 10 * 0.1f;
                    camerax -= delta * 0.1f;
                }else if((blocked == 7) && (tmpx-(int)tmpx)>0.2 && (tmpy-(int)tmpy)>0.4){
                    x += 10 * 0.1f;
                    camerax -= delta * 0.1f;
                }else if((blocked == 7) && (tmpx-(int)tmpx)<.5 && (tmpy-(int)tmpy)>0.9){
                    x += 10 * 0.1f;
                    camerax -= delta * 0.1f;
                }else if((blocked == 7) && (tmpx-(int)tmpx)>.2 && (tmpy-(int)tmpy)>0.06 ){
                    x += 10 * 0.1f;
                    camerax -= delta * 0.1f;
                }else if(blocked!=4 && blocked!=7 && blocked != 0){
                    x -= 10 * 0.1f;
                    camerax += delta * 0.1f;
                }else{
                    System.out.println(tmppx+"/"+tmppy);
                }
                
            }
        }
        else if (input.isKeyDown(Input.KEY_RIGHT)||input.isKeyDown(Input.KEY_D))
        {
            player = right;
            player.update(delta);
            int blocked =isBlocked((((((int)x+ 10 * 0.1f)+40)+(int)camerax*-1)),(((((int)y+10*0.1f)+60)+(int)cameray*-1) ));
            if (blocked != 0){
                float tmpx = (float)((((((int)x+ 10 * 0.1f)+player.getWidth()/2)+(int)camerax*-1))/32);
                float tmpy = (float)((((((int)y+ 10 * 0.1f)+player.getHeight())+(int)cameray*-1))/32);
                tmppx = tmpx - (int)tmpx;
                tmppy = tmpy - (int)tmpy;
                if((blocked == 4) && (tmpy-(int)tmpy)<0.2){
                    x += 10 * 0.1f;
                    camerax -= delta * 0.1f;
                }else if((blocked == 8) && (tmpx-(int)tmpx)>0.4  && (tmpy-(int)tmpy)<0.7){ 
                    x += 10 * 0.1f;
                    camerax -= delta * 0.1f;
                }else if((blocked == 8) && (tmpx-(int)tmpx)<0.2 && (tmpy-(int)tmpy)<0.4){
                    x += 10 * 0.1f;
                    camerax -= delta * 0.1f;
                }else if((blocked == 8) && (tmpx-(int)tmpx)>.5 && (tmpy-(int)tmpy)<0.9){
                    x += 10 * 0.1f;
                    camerax -= delta * 0.1f;
                }else if((blocked == 8) && (tmpx-(int)tmpx)<.2 && (tmpy-(int)tmpy)<0.06 ){
                    x += 10 * 0.1f;
                    camerax -= delta * 0.1f;
                }else if(blocked!=4 && blocked!=8 && blocked != 0){
                   x += 10 * 0.1f;
                   camerax -= delta * 0.1f;
                }else{
                    System.out.println(tmppx+"/"+tmppy);
                }
            }
        }
        
        
        
        
        
        
        
        else if(input.isKeyDown(Input.KEY_0)){
            SpriteSheet playerSheet = new SpriteSheet("res/sprites/male_walkcycle.png",64,64,0);

            up = new Animation();
            down = new Animation();
            left = new Animation();
            right = new Animation();
            for (int frame=0;frame<9;frame++) {
                up.addFrame(playerSheet.getSprite(frame,0), 64);
            }
            for (int frame=0;frame<9;frame++) {
                left.addFrame(playerSheet.getSprite(frame,1), 64);
            }
            for (int frame=0;frame<9;frame++) {
                down.addFrame(playerSheet.getSprite(frame,2), 64);
            }
            for (int frame=0;frame<9;frame++) {
                right.addFrame(playerSheet.getSprite(frame,3), 64);
            }
            player.update(delta);
        } else if(input.isKeyDown(Input.KEY_1)){
            SpriteSheet playerSheet = new SpriteSheet("res/sprites/female_walkcycle.png",64,64,0);
            up = new Animation();
            down = new Animation();
            left = new Animation();
            right = new Animation();
            for (int frame=0;frame<9;frame++) {
                up.addFrame(playerSheet.getSprite(frame,0), 64);
            }
            for (int frame=0;frame<9;frame++) {
                left.addFrame(playerSheet.getSprite(frame,1), 64);
            }
            for (int frame=0;frame<9;frame++) {
                down.addFrame(playerSheet.getSprite(frame,2), 64);
            }
            for (int frame=0;frame<9;frame++) {
                right.addFrame(playerSheet.getSprite(frame,3), 64);
            }
            player.update(delta);
        }
    }
    
    private int isBlocked(float x, float y)
    {
//        int xBlock = (((int)x+64/2)+((int)camerax)*-1) / SIZE;
//        int yBlock = (((int)y+player.getHeight()/2)+(int)cameray*-1) / SIZE;
        int xBlock = (int)x / SIZE;
        int yBlock = (int)y / SIZE;
        nextx = xBlock;
        nexty = yBlock;
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
}
