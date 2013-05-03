/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package memorygame;
  
import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author kamilo
 */
public class CardsLogic {
    private ArrayList<Card> cards;
    
    private static final int CARD_WIDTH = 94;
    private static final int CARD_HEIGHT = 132;
    private static final int CARD_MARGIN = 33;
    
    
    public CardsLogic() throws SlickException{
        cards = new ArrayList<Card>();
        this.cards.add(new Card(1, 17, new Image("img/king_of_spades.png"), new Image("img/cardbackimage.png"), false, CARD_MARGIN*1+CARD_WIDTH*0, CARD_MARGIN*1+CARD_HEIGHT*0));
        this.cards.add(new Card(2, 10, new Image("img/ace_of_hearts.png"), new Image("img/cardbackimage.png"), false, CARD_MARGIN*1+CARD_WIDTH*0, CARD_MARGIN*2+CARD_HEIGHT*1));
        this.cards.add(new Card(3, 14, new Image("img/7_of_hearts.png"), new Image("img/cardbackimage.png"), false, CARD_MARGIN*1+CARD_WIDTH*0, CARD_MARGIN*3+CARD_HEIGHT*2));
        this.cards.add(new Card(4, 12, new Image("img/ace_of_clubs.png"), new Image("img/cardbackimage.png"), false, CARD_MARGIN*2+CARD_WIDTH*1, CARD_MARGIN*1+CARD_HEIGHT*0));
        this.cards.add(new Card(5, 16, new Image("img/black_joker.png"), new Image("img/cardbackimage.png"), false, CARD_MARGIN*2+CARD_WIDTH*1, CARD_MARGIN*2+CARD_HEIGHT*1));
        this.cards.add(new Card(6, 18, new Image("img/queen_of_diamonds.png"), new Image("img/cardbackimage.png"), false, CARD_MARGIN*2+CARD_WIDTH*1, CARD_MARGIN*3+CARD_HEIGHT*2));
        this.cards.add(new Card(7, 11, new Image("img/8_of_clubs.png"), new Image("img/cardbackimage.png"), false, CARD_MARGIN*3+CARD_WIDTH*2, CARD_MARGIN*1+CARD_HEIGHT*0));
        this.cards.add(new Card(8, 15, new Image("img/2_of_spades.png"), new Image("img/cardbackimage.png"), false, CARD_MARGIN*3+CARD_WIDTH*2, CARD_MARGIN*2+CARD_HEIGHT*1));
        this.cards.add(new Card(9, 13, new Image("img/ace_of_spades.png"), new Image("img/cardbackimage.png"), false, CARD_MARGIN*3+CARD_WIDTH*2, CARD_MARGIN*3+CARD_HEIGHT*2));
        this.cards.add(new Card(10, 2, new Image("img/ace_of_hearts.png"), new Image("img/cardbackimage.png"), false, CARD_MARGIN*4+CARD_WIDTH*3, CARD_MARGIN*1+CARD_HEIGHT*0));
        this.cards.add(new Card(11, 7, new Image("img/8_of_clubs.png"), new Image("img/cardbackimage.png"), false, CARD_MARGIN*4+CARD_WIDTH*3, CARD_MARGIN*2+CARD_HEIGHT*1));
        this.cards.add(new Card(12, 4, new Image("img/ace_of_clubs.png"), new Image("img/cardbackimage.png"), false, CARD_MARGIN*4+CARD_WIDTH*3, CARD_MARGIN*3+CARD_HEIGHT*2));
        this.cards.add(new Card(13, 9, new Image("img/ace_of_spades.png"), new Image("img/cardbackimage.png"), false, CARD_MARGIN*5+CARD_WIDTH*4, CARD_MARGIN*1+CARD_HEIGHT*0));
        this.cards.add(new Card(14, 3, new Image("img/7_of_hearts.png"), new Image("img/cardbackimage.png"), false, CARD_MARGIN*5+CARD_WIDTH*4, CARD_MARGIN*2+CARD_HEIGHT*1));
        this.cards.add(new Card(15, 8, new Image("img/2_of_spades.png"), new Image("img/cardbackimage.png"), false, CARD_MARGIN*5+CARD_WIDTH*4, CARD_MARGIN*3+CARD_HEIGHT*2));
        this.cards.add(new Card(16, 5, new Image("img/black_joker.png"), new Image("img/cardbackimage.png"), false, CARD_MARGIN*6+CARD_WIDTH*5, CARD_MARGIN*1+CARD_HEIGHT*0));
        this.cards.add(new Card(17, 1, new Image("img/king_of_spades.png"), new Image("img/cardbackimage.png"), false, CARD_MARGIN*6+CARD_WIDTH*5, CARD_MARGIN*2+CARD_HEIGHT*1));
        this.cards.add(new Card(18, 6, new Image("img/queen_of_diamonds.png"), new Image("img/cardbackimage.png"), false, CARD_MARGIN*6+CARD_WIDTH*5, CARD_MARGIN*3+CARD_HEIGHT*2));
    }
    
    public ArrayList<Card> getCards(){
        return this.cards;
    }
    
    //dibujar todas las cartas
    public void drawCards() throws SlickException{
        for(Card card : this.cards){
            card.drawCard();
        }      
    }
    
    //abrir la carta que tiene determinada id
    public void openCard(int card_id) throws SlickException{
        System.out.println("Abriendo carta "+ card_id);
         for(Card card : this.cards){
             if(card.getId() == card_id){
                 card.setFlipped(true);
                 card.drawCard();
             }
        } 
    }
    
    //cerrar la carta que tiene determinada id
    public void closeCard(int card_id) throws SlickException{
        System.out.println("Cerrando carta "+ card_id);
         for(Card card : this.cards){
             if(card.getId() == card_id){
                 card.setFlipped(false);
                 card.drawCard();
             }
        } 
    }
    
    //hacer hover sobre la carta que tiene determinada id
    public void hoverCard(int card_id) throws SlickException{
        for(Card card : this.cards){
             if(card.getId() == card_id && !card.isFlipped()){
                 card.setCardbackimage(new Image("img/hovercardbackimage.png"));
                 card.drawCard();
             }
        } 
    }
    
    //abrir todas las cartas
    public void openAllCards() throws SlickException{
        for(Card card : this.cards){
            card.setFlipped(true);
            card.drawCard();
        }      
    }
    
    //cerrar todas las cartas excepto las parejas encontradas
    public void closeAllCards() throws SlickException{
        for(Card card : this.cards){
            card.setCardbackimage(new Image("img/cardbackimage.png"));
            card.drawCard();
        } 
    }
    
    //evaluar las parejas
    public void evaluate(ArrayList<Card> flippedcards) throws SlickException{
        if(flippedcards.size() == 2){
            if(!((flippedcards.get(0).getPareja_id() == flippedcards.get(1).getId()) && (flippedcards.get(1).getPareja_id() == flippedcards.get(0).getId()))){
                this.closeCard(flippedcards.get(0).getId());
                this.closeCard(flippedcards.get(1).getId());
            }
            else{
                
            }
             flippedcards.clear();
        }
    }
    
    //obtener identificador de carta de acuerdo a posicion en la grilla de cartas (fila, columna)
    public int getId(int row, int col) throws SlickException{
        if(row < 0){
            row = 0;
        }
        if(row > 5){
            row = 5;
        }
        if(col < 0){
            col = 0;
        }
        if(col > 2){
            col = 2;
        }
        int posX = CARD_MARGIN*(row+1)+CARD_WIDTH*row;
        int posY = CARD_MARGIN*(col+1)+CARD_HEIGHT*col;
        int id = 0;
        for(Card card : this.cards){
             if(card.getPosX() == posX && card.getPosY() == posY){
                 id = card.getId();
             }
        } 
        return id;
    }
    
    //obtener carta de acuerdo a su id
    public Card getCard(int card_id){
        Card c = null;
         for(Card card : this.cards){
             if(card.getId() == card_id){
                 c = card;
             }
        } 
         return c;
    }
    
}
