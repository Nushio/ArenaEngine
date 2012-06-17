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
package net.k3rnel.arena;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import net.k3rnel.arena.states.GamePlay;
import net.k3rnel.arena.states.MainMenu;
import net.k3rnel.arena.states.Options;

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

    public static final int MAINMENU           = 0;
    public static final int OPTIONS            = 1;
    public static final int GAMEPLAY           = 2;

    public GameClient(String title) {
        super(title);

        this.addState(new MainMenu(MAINMENU));
        this.addState(new Options(OPTIONS));
        this.addState(new GamePlay(GAMEPLAY));
        this.enterState(MAINMENU);
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.getState(MAINMENU).init(gc, this);

    }

    /**
     * If you don't know what this does, then roflmao. 
     * @param args
     */
    public static void main(String [] args) {
        try {
            AppGameContainer app = new AppGameContainer(new GameClient("Unsealed: Whispers of Wisdom"));

            app.setDisplayMode(800, 600, false);
            app.start();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(new JDialog(), "Whoops!\nThere was a terrible error and we can't launch the game.\nOur sincerest apologies.");            
            e.printStackTrace();
        }
    }


}
