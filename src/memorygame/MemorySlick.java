/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package memorygame; 
 
import com.leapmotion.leap.Controller;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 *
 * @author kamilo
 */
public class MemorySlick {
    
    public static void main(String[] args) throws SlickException {
         //se inicializa el juego a traves del Listener
         LeapListener listener = new LeapListener(new Game("Memory", true));
         //se inicializa el controlador del LEAP
         Controller controller = new Controller();
         //se asocia el Listener al controlador
         controller.addListener(listener);
         //el juego corre con 800x600 de resolucion
         listener.app.setDisplayMode(800, 600, false);
         //se lanza el juego
         listener.app.start();
         //al terminar se elimina el listener
         controller.removeListener(listener);
    }
    
   
    
}
