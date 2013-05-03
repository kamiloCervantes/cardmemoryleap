/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package memorygame;
  
/**
 *
 * @author kamilo
 */

import com.leapmotion.leap.*;
import com.leapmotion.leap.Gesture.State;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;



class LeapListener extends Listener {
  //instancia del juego
  Game memorygame;
  
  //contenedor del juego provisto por Slick2D
  AppGameContainer app;
  
  //fila y columna seleccionada
  int rows, cols;
    
  //se inicializa el juego con uso de mouse = false  
  public LeapListener(Game game) throws SlickException{
      this.memorygame = game;
      game.mouse = false;
      app = new AppGameContainer(this.memorygame);
      rows = 0;
      cols = 0;
  }
    
  public void onInit(Controller controller) {
    System.out.println("Initialized");
  }

  //se deben habilitar los gestos swipe y circle
  public void onConnect(Controller controller) {
    System.out.println("Connected");
    controller.enableGesture(Gesture.Type.TYPE_SWIPE);
    controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
  }

  public void onDisconnect(Controller controller) {
    System.out.println("Disconnected");
  }
  
  //aqui se capturan los gestos y se envían los valores de filas y columnas al juego y tambien si se desea voltear la carta
  public void onFrame(Controller controller) {
//      Get the most recent frame and report some basic information
        Frame frame = controller.frame();
        GestureList gestures = frame.gestures();
        for(int i = 0; i < gestures.count(); i++){
            Gesture gesture = gestures.get(i);
            
            switch(gesture.type()){
                case TYPE_SWIPE:
                    SwipeGesture swipe = new SwipeGesture(gesture);
                    System.out.println("Swipe id: " + swipe.id()
                            + ", " + swipe.state()
                            + ", position: " + swipe.position()
                            + ", direction: " + swipe.direction()
                            + ", speed: " + swipe.speed());
                    if(swipe.state().equals(State.STATE_STOP)){
                        if(swipe.direction().getX() < -0.5){
                            rows++;
                        }
                        else{
                            if(swipe.direction().getX() > 0.5){
                                rows--;
                            } 
                        }
                        if(swipe.direction().getY() < -0.5){
                            cols++;
                        }
                        else{
                            if(swipe.direction().getY() > 0.5){
                                cols--;
                            }
                        }
                    }
                    if(rows < 0){
                        rows = 0;
                    }
                    if(rows > 5){
                        rows = 5;
                    }
                    if(cols < 0){
                        cols = 0;
                    }
                    if(cols > 2){
                        cols = 2;
                    }
                    System.out.println("Fila: "+rows+" Columna: "+cols);
                    this.memorygame.rows = rows;
                    this.memorygame.cols = cols;
                    break;
                case TYPE_CIRCLE:
                    CircleGesture circle = new CircleGesture(gesture);
                    // Calculate clock direction using the angle between circle normal and pointable
                    String clockwiseness;
                    if (circle.pointable().direction().angleTo(circle.normal()) <= Math.PI/4) {
                        // Clockwise if angle is less than 90 degrees
                        clockwiseness = "clockwise";
                    } else {
                        clockwiseness = "counterclockwise";
                    }

                    // Calculate angle swept since last frame
                    double sweptAngle = 0;
                    if (circle.state() != State.STATE_START) {
                        CircleGesture previousUpdate = new CircleGesture(controller.frame(1).gesture(circle.id()));
                        sweptAngle = (circle.progress() - previousUpdate.progress()) * 2 * Math.PI;
                    }

                    System.out.println("Circle id: " + circle.id()
                            + ", " + circle.state()
                            + ", progress: " + circle.progress()
                            + ", radius: " + circle.radius()
                            + ", angle: " + Math.toDegrees(sweptAngle)
                            + ", " + clockwiseness);
                    
                    if(circle.radius() > 40 && circle.progress() > 1 && circle.state().equals(State.STATE_STOP)){
                        this.memorygame.flip = true;
                    }
                    break;
            }
        }
    }
}
