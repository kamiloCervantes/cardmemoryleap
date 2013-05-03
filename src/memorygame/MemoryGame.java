/*
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package memorygame; 
 

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 *
 * @author kamilo
 */
public class MemoryGame {
    public static void main(String[] args) {
         try { 
             //inicializa el juego con el mouse en true
            AppGameContainer container = new AppGameContainer(new Game("Memory", false)); 
            //el juego corre a 800x600 de resolucion
            container.setDisplayMode(800,600,false); 
            //lanza el juego
            container.start(); 
         } 
         catch (SlickException e) { 
            e.printStackTrace(); 
         }
    }
}
