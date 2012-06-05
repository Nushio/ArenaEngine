/**
 * This file is part of ArenaTCG
 * 
 *  ArenaTCG is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  
 *  ArenaTCG is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with ArenaTCG.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.k3rnel.arena.tcg.main;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import net.k3rnel.arena.tcg.main.states.MainMenuScreen;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * The Game Client. 
 * Handles different Game States, like Menus, Options, Walking on Sunshine and Battling
 * @author Nushio
 *
 */
public class GameClient extends StateBasedGame {

    public static final int MAINMENUSTATE           = 0;
    public static final int OPTIONSSTATE            = 1;
    public static final int CHARSELECTSTATE         = 2;
    public static final int WALKONMAPSTATE          = 3;

    public GameClient(String title) {
        super(title);

        this.addState(new MainMenuScreen(MAINMENUSTATE));
        this.enterState(MAINMENUSTATE);
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.getState(MAINMENUSTATE).init(gc, this);

    }

    /**
     * If you don't know what this does, then roflmao. 
     * @param args
     */
    public static void main(String [] args) {
        // Start in Fullscreen? Default no. 
        //        final boolean fullscreen = false;
        //        try {
        //            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //            double screenRatio = 0.0;
        //            if(screenSize!=null && screenSize.getWidth()>0 && screenSize.getHeight()>0)
        //                screenRatio = screenSize.getWidth() / screenSize.getHeight();
        //            int width, height;
        //            if(screenRatio < 0.6){
        //                //Start Widescreen
        //               width = 1024;
        //               height = 600;
        //            } else{
        //                //Start Fullscreen
        //                width = 768;
        //                height = 1024;
        //            }
        //            
        //            m_gamecontainer = new AppGameContainer(new GameClient("Arena TCG"),
        //                  width,height, fullscreen);
        //            m_gamecontainer.setTargetFrameRate(50);
        //            m_gamecontainer.start();
        //        } catch (Exception e) {
        //            JOptionPane.showMessageDialog(new JDialog(), "Whoops!\nThere was a terrible error and we can't launch the game.\nOur sincerest apologies.");            
        //            e.printStackTrace();
        //        }
        try {
            AppGameContainer app = new AppGameContainer(new GameClient("Arena TCG"));

            app.setDisplayMode(800, 600, false);
            app.start();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JDialog(), "Whoops!\nThere was a terrible error and we can't launch the game.\nOur sincerest apologies.");            
            e.printStackTrace();
        }
    }


}
