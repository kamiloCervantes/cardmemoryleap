/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package memorygame;
  
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.*;
import org.newdawn.slick.font.effects.ColorEffect;

/**
 *
 * @author kamilo
 */
public class Game extends BasicGame implements KeyListener {
    
    //fondo de la ventana
    private Image background;
    
    //cartas y lógica del juego
    public CardsLogic cards;
    
    //variable para almacenar de forma temporal las parejas volteadas para su posterior evaluación
    private ArrayList<Card> flipped; 
    
    //variable controladora para crear un delay al evaluar las parejas voleadas
    private int elapsedtime;
    
    //duracion del delay
    private static final int DELAY = 30;
    
    //variable para cargar la fuente
    UnicodeFont font;
    
    //variable que indica si se usa mouse o no
    boolean mouse;
    
    //variables que indican la fila y columna seleccionada
    int rows, cols;
    
    //variables que indican si se desea voltear la fila y columna seleccionada
    boolean flip;
    
    public Game(String title, boolean leap){
        super(title);
        mouse = !leap;
    }
    
    @Override
    public void init(GameContainer gc) throws SlickException {
        font = new UnicodeFont("fonts/coolvetica.ttf", 44, false, false);
        font.addAsciiGlyphs();
        font.getEffects().add(new ColorEffect());
        font.loadGlyphs();
        background = new Image("img/background.png");
        cards = new CardsLogic();
        flipped = new ArrayList<Card>();
        elapsedtime = 0;
        rows = 0;
        cols = 0;
        flip = false;
    }
    
    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        background.draw(0,0);
        cards.drawCards();
        font.drawString(280, 520, "Card Memory", Color.darkGray);
    }
    
    @Override
    public void update(GameContainer gc, int delta) throws SlickException {   
        elapsedtime++;
        if(mouse){
            for(Card card : cards.getCards()){
                if(card.getBox().onRange(gc.getInput().getMouseX(), gc.getInput().getMouseY())){
                    if(gc.getInput().isMousePressed(0) && this.flipped.size()< 2){
                        elapsedtime = 0; elapsedtime++;
                        cards.openCard(card.getId());
                        this.flipped.add(card);
                        }
                    else{
                        cards.closeAllCards();
                        cards.hoverCard(card.getId());     
                    }
                }
            }
            if(elapsedtime >= DELAY){
                cards.evaluate(this.flipped);
            }
        }
        else{
            if(cards.getId(rows, cols) > 0){
                cards.closeAllCards();
                cards.hoverCard(cards.getId(rows, cols));
                if(flip && this.flipped.size()< 2){
                    elapsedtime = 0; elapsedtime++;
                    cards.openCard(cards.getId(rows, cols));
                    this.flipped.add(cards.getCard(cards.getId(rows, cols)));
                    flip = false;
                }
                if(elapsedtime >= DELAY){
                    cards.evaluate(this.flipped);
                }
             }
         }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
