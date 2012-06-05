package net.k3rnel.arena.tcg.main.states;

import net.k3rnel.arena.tcg.main.GameClient;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
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
    int stateID = 0; //Tip: It's not Oregon. It's never Oregon. 
    
    Image background = null;
    
    //The Shiny Menu Options
    Image newGameOption = null;
    Image continueOption = null;
    Image optionsOption = null;
    Image quitOption = null;

    // The Menu Option Size
    float newGameScale = 1;
    float continueScale = 1;
    float optionsScale = 1;
    float quitScale = 1;

    // Where we place the Menus
    private static int menuX = 410;
    private static int menuY = 230;

    float scaleStep = 0.0001f; // Sets the speed of the Zoom In Animation
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
        newGameOption = menuOptions.getSubImage(0, 0, 377, 71);

        // Continue Option
        continueOption = menuOptions.getSubImage(0, 71, 377, 68);

        // Options Option. Ya, rly. 
        optionsOption = menuOptions.getSubImage(0, 139, 377, 50);

        // Quit Option        
        quitOption = menuOptions.getSubImage(0, 186, 377, 58);
    }

    /**
     * This makes things appear on screen, after we've initialized the variables
     */
    public void render(GameContainer gc, StateBasedGame sbg, Graphics gx) throws SlickException {

        // Display the background
        background.draw(0, 0);

        // Display menu
        newGameOption.draw(menuX, menuY, newGameScale);

        continueOption.draw(menuX, menuY+80, continueScale);

        optionsOption.draw(menuX, menuY+160, optionsScale);
        
        quitOption.draw(menuX, menuY+225, quitScale);
    }

    /**
     * Changes the stuff that is happening on-screen. 
     * In this case, it checks where your mouse is located and makes the image 
     * you mouse-overed Zoom In or Out. I dunno if its patented by Apple, but Byte me. 
     */
    public void update(GameContainer gc, StateBasedGame sb, int delta) throws SlickException {
        Input input = gc.getInput();

        int mouseX = input.getMouseX();
        int mouseY = input.getMouseY();

        boolean insideNewGame = false;
        boolean insideContinue = false;
        boolean insideOptions = false;
        boolean insideQuit = false;

        if( ( mouseX >= menuX && mouseX <= menuX + newGameOption.getWidth()) &&
                ( mouseY >= menuY && mouseY <= menuY + newGameOption.getHeight()) ){
            insideNewGame = true;
            insideContinue = false;
            insideOptions = false;
            insideQuit = false;
        }else if( ( mouseX >= menuX && mouseX <= menuX+ continueOption.getWidth()) &&
                ( mouseY >= menuY+80 && mouseY <= menuY+80 + continueOption.getHeight()) 
               ){
            insideNewGame = false;
            insideContinue = true;
            insideOptions = false;
            insideQuit = false;
        } else if( ( mouseX >= menuX && mouseX <= menuX+ optionsOption.getWidth()) &&
                ( mouseY >= menuY+160 && mouseY <= menuY+160 + optionsOption.getHeight())){
            insideNewGame = false;
            insideContinue = false;
            insideOptions = true;
            insideQuit = false;
        } else if( ( mouseX >= menuX && mouseX <= menuX+ quitOption.getWidth()) &&
                ( mouseY >= menuY+220 && mouseY <= menuY+220 + quitOption.getHeight())){
            insideNewGame = false;
            insideContinue = false;
            insideOptions = false;
            insideQuit = true;
        }else{
            insideNewGame = false;
            insideContinue = false;
            insideOptions = false;
            insideQuit = false;
            
        }

        if(insideNewGame){
            if(newGameScale < 1.05f)
                newGameScale += scaleStep * delta;

            if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) ){
                sb.enterState(GameClient.CHARSELECTSTATE);
            }
        }else{
            if(newGameScale > 1.0f)
                newGameScale -= scaleStep * delta;

            if ( input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON) )
                gc.exit();
        } 

        if(insideContinue)
        {
            if(continueScale < 1.05f)
                continueScale +=  scaleStep * delta;
        }else{
            if(continueScale > 1.0f)
                continueScale -= scaleStep * delta;
        }
        
        if(insideOptions)
        {
            if(optionsScale < 1.05f)
                optionsScale +=  scaleStep * delta;
        }else{
            if(optionsScale > 1.0f)
                optionsScale -= scaleStep * delta;
        }
        if(insideQuit)
        {
            if(quitScale < 1.05f)
                quitScale +=  scaleStep * delta;
        }else{
            if(quitScale > 1.0f)
                quitScale -= scaleStep * delta;
        }
    }
}
