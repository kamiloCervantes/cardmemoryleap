/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package memorygame;
 
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
 
/**
 *
 * @author kamilo
 */
public class Card {
    
    //identificador de la carta
    private int id;
    
    //identificador de la pareja
    private int pareja_id;
    
    //imagen que esconde la carta
    private Image cardimage;
    
    //imagen de la parte de atras de la carta
    private Image cardbackimage;
    
    //estado de la carta (volteada o no)
    private boolean flipped;
    
    private CardBox box;
    
    //coordenada x
    private int posX;
    
    //coordenada y
    private int posY;
    
    public Card(int id, int pareja_id, Image cardimage, Image cardbackimage, boolean flipped, int posX, int posY){
        this.id = id;
        this.pareja_id = pareja_id;
        this.cardimage = cardimage;
        this.cardbackimage = cardbackimage;
        this.flipped = flipped;
        this.posX = posX;
        this.posY = posY;
        this.box = new CardBox(posX, posY);
    }
    
    //dibuja una carta en la vista
    public void drawCard() throws SlickException{
        if(flipped){
            this.cardimage.draw(this.getPosX(), this.getPosY());
        }
        else{
            this.cardbackimage.draw(this.getPosX(), this.getPosY());
        }
    }
    
    //voltea una carta ya sea para abrirla o cerrarla
    public void flipCard() throws SlickException{
        flipped = !flipped;
        this.drawCard();
    }
    
    public void setFlipped(boolean param){
        this.flipped = param;
    }
    
    public boolean isFlipped(){
        return this.flipped;
    }
    
    public int getId(){
        return this.id;
    }
    
    public CardBox getBox(){
        return this.box;
    }

    public void setCardbackimage(Image i) {
        this.cardbackimage = i;
    }
    
    public int getPareja_id(){
        return this.pareja_id;
    }

    /**
     * @return the posX
     */
    public int getPosX() {
        return posX;
    }

    /**
     * @return the posY
     */
    public int getPosY() {
        return posY;
    }
}
