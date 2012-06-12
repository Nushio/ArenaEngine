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
 * The Main Menu Screen. 
 * It's a BasicGameState as used by Slick2d. 
 * There's many like this, but this one's mine. Bwahahaha. 
 * 
 * @author Nushio
 *
 */
public class MainMenuScreen extends BasicGameState {

    // A Unique ID so that the GameClient knows which state we're in. 
    int stateID = 0; //Tip: It's not Machupichu. It's never Machupichu. 

    Image background = null;

    // The Shiny Menu Options
    Image newGameOption = null;
    Image continueOption = null;
    Image optionsOption = null;
    Image quitOption = null;

    // The Click Wizard!
    Animation wizard = null;
    Animation fireball = null;
    Image staff = null;
    Image speech = null;
    
    // Where we place the Menus
    private static int menuX = 410;
    private static int menuY = 230;
    
    boolean clicked = false;
    int frame = 0;
    int option = 0;
    /**
     * Initializes the StateID
     * @param stateID
     */
    public MainMenuScreen (int stateID ){
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
        background = new Image("res/images/background.png");

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
        
        wizard = new Animation();
        wizard.setAutoUpdate(true);
        SpriteSheet wizardSheet = new SpriteSheet("res/images/wizard.png", 86,76);
        for (int frame=0;frame<4;frame++) {
            wizard.addFrame(wizardSheet.getSprite(frame,0), 86);
        }
        
        speech = new Image("res/images/speech.png");

    }
    /**
     * This makes things appear on screen, after we've initialized the variables
     */
    public void render(GameContainer gc, StateBasedGame sbg, Graphics gx) throws SlickException {

        // Display the background
        background.draw(0, 0);

        // Display menu
        newGameOption.draw(menuX, menuY);

        continueOption.draw(menuX, menuY+65);

        optionsOption.draw(menuX, menuY+125);

        quitOption.draw(menuX, menuY+185);

        staff.draw(menuX-80,menuY+25+option);
        if(clicked){
            fireball.draw(menuX+5+frame,menuY+22+option);
            frame= frame+40;
            if(frame > 260){
                clicked = false;
                frame = 0;
                System.out.println(option);
                if(option == 0){
                    sbg.enterState(GameClient.PLAYSTATE);
                }else if(option == 120){
                    sbg.enterState(GameClient.OPTIONSSTATE);
                }else if(option == 160){
                   gc.exit();
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

        if(input.isButtonPressed(0, 0)){
            System.out.println("You pressed 0");
        }else if(input.isButtonPressed(1, 0)){
            System.out.println("You pressed 1");
        }else if(input.isButtonPressed(2, 0)){
            System.out.println("You pressed 2");
        }else if(input.isButtonPressed(3, 0)){
            System.out.println("You pressed 3");
        }else if(input.isButtonPressed(4, 0)){
            System.out.println("You pressed 4");
        }else if(input.isButtonPressed(5, 0)){
            System.out.println("You pressed 5");
        }else if(input.isButtonPressed(6, 0)){
            System.out.println("You pressed 6");
        }else if(input.isButtonPressed(7, 0)){
            System.out.println("You pressed 7");
        }else if(input.isButtonPressed(8, 0)){
            System.out.println("You pressed 8");
        }else if(input.isButtonPressed(9, 0)){
            System.out.println("You pressed 9");
        }
        if ( input.isKeyPressed(Input.KEY_SPACE) ){
            clicked = true;
        }
        if(input.isControllerUp(0)){
            if(option==0)
                option = 180;
            else
                option = option - 60;
            
        }
        if(input.isControllerDown(0)){
            option=option+60;
            if(option >= 240)
                option = 0;
            
        }
        if(input.isButton1Pressed(0)){
            clicked = true;
        }
        if(input.isButton2Pressed(0)){
            clicked = true;
        }  
        if(input.isButton3Pressed(0)){
            clicked = true;
        }
        if (input.isKeyPressed(Input.KEY_DOWN)){
            option=option+60;
            if(option >= 240)
                option = 0;
        }
        if (input.isKeyPressed(Input.KEY_UP)){
            if(option==0)
                option = 180;
            else
                option = option - 60;
            
        }
    }
}
