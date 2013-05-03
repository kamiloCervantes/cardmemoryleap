/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package memorygame;
  
/**
 *
 * @author kamilo
 */
public class CardBox {
    
    //posicion x del origen
    private int x;
    
    //posicion y del origen
    private int y;
    
    //ancho de la caja
    private int width;
    
    //alto de la caja
    private int height;
    
    private static final int CARD_WIDTH = 94;
    private static final int CARD_HEIGHT = 132;

    
    public CardBox(int x, int y){
        this.x = x;
        this.y = y;
        this.width = CARD_WIDTH;
        this.height = CARD_HEIGHT;
    }
    
    //este metodo nos dice si las coordenadas ingresadas estan en el rango de la carta
    public boolean onRange(int posX, int posY){
        boolean b = false;
        if(posX >= this.x && posY >= this.y && posX < this.x + this.width && posY < this.y + this.height){
            b = true;
        }
        return b;
    }
    
}
