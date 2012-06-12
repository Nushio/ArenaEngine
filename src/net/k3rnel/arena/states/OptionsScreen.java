package net.k3rnel.arena.states;

import net.k3rnel.arena.GameClient;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * The Options Screen. 
 * It's a BasicGameState as used by Slick2d. 
 * 
 * @author Nushio
 *
 */
public class OptionsScreen extends BasicGameState {

    // A Unique ID so that the GameClient knows which state we're in. 
    int stateID = 1; //Tip: It's not Oregon. It's never Oregon. 

    Image background = null;

    // The Shiny Menu Options
    Image newGameOption = null;
    Image continueOption = null;
    Image optionsOption = null;
    Image quitOption = null;

    // The Click Wizard!
    Animation fireball = null;
    Image staff = null;
    
    // Where we place the Menus
    private static int menuX = 120;
    private static int menuY = 100;
    
    boolean clicked = false;
    int frame = 0;
    int option = 0;
    /**
     * Initializes the StateID
     * @param stateID
     */
    public OptionsScreen (int stateID ){
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

    /**
     * Initializes the Main Menu
     * This is where we set up the background, as well as the menu options. 
     */
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        // Load the background. 
        background = new Image("res/images/optionsmenu.png");

        // Load the menu image. We shred this into tiny pieces. 
        Image menuOptions = new Image("res/images/menuoptions.png");

        // New Game Option
        newGameOption = menuOptions.getSubImage(0, 0, 263, 65);

        // Continue Option
        continueOption = menuOptions.getSubImage(0, 65, 263, 65);

        // Options Option. Ya, rly. 
        optionsOption = menuOptions.getSubImage(0, 131, 263, 65);

        // Quit Option        
        quitOption = menuOptions.getSubImage(0, 196, 263, 65);

        staff = new Image("res/images/staff-cursor.png");
        
        fireball = new Animation();
        fireball.setAutoUpdate(true);
        SpriteSheet fireballSheet = new SpriteSheet("res/images/fireball.png",32,32);
        for (int frame=1;frame<4;frame++) {
            fireball.addFrame(fireballSheet.getSprite(frame,0), 32);
        }

    }
    /**
     * This makes things appear on screen, after we've initialized the variables
     */
    public void render(GameContainer gc, StateBasedGame sbg, Graphics gx) throws SlickException {

        // Display the background
        background.draw(0, 0);

        // Display menu
//        newGameOption.draw(menuX, menuY);
//
//        continueOption.draw(menuX, menuY+65);
//
//        optionsOption.draw(menuX, menuY+125);
//
//        quitOption.draw(menuX, menuY+185);

        staff.draw(menuX-80,menuY+25+option);
        if(clicked){
            fireball.draw(menuX+5+frame,menuY+22+option);
            frame= frame+30;
            if(frame > 180){
                clicked = false;
                frame = 0;
                System.out.println(option);
                if(option == 378){
                    sbg.enterState(GameClient.MAINMENUSTATE);
                }
                    
            }
//            wizard.draw(100, 400);
//            wizard.setSpeed((float) 0.5);
//            speech.draw(120,320);
//            gx.setAntiAlias(true);
//            gx.setFont(new TrueTypeFont(new java.awt.Font("res/fonts/MedievalSharp.ttf",0,24), true));
//            gx.setColor(new Color(0,0,0));
//            gx.drawString("YOU SHALL NOT CLICK!",150,345);
        }
    }

    public void update(GameContainer gc, StateBasedGame sb, int delta) throws SlickException {
        Input input = gc.getInput();

        if ( input.isKeyPressed(Input.KEY_SPACE) ){
            clicked = true;
        }
        if (input.isKeyPressed(Input.KEY_DOWN)){
            if(option == 0){
                option =78;
            }else if(option == 78){
            option=378;
            }else{
                option = 0;
            }
        }
        if (input.isKeyPressed(Input.KEY_UP)){
            if(option==0)
                option = 378;
            else if(option == 78){
                option = 0;
            }else{
                option = 78;
            }
            
        }
    }
}
