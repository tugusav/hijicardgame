import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CardDeck{
    private List<Card> deck = new ArrayList<>(108);
    
    public CardDeck(){
        generateCards();
    }

    public void generateCards(){
        generateNumbers();
        generateActionCards();
        generateSpecial();
        
    }

    public void generateNumbers(){
        Color red = new Color("RED");
        Color green = new Color("GREEN");
        Color blue = new Color("BLUE");
        Color yellow = new Color("YELLOW");
        List<Color> colors = new ArrayList<>();
        colors.add(red);
        colors.add(green);
        colors.add(blue);
        colors.add(yellow);
        for(var color: colors){
            deck.add(new Angka(0, color));
            for(int i = 1; i <= 9; i++){
                deck.add(new Angka(i, color));
                deck.add(new Angka(i, color));
            }
        }
    }
    public void generateActionCards(){
        Color red = new Color("RED");
        Color green = new Color("GREEN");
        Color blue = new Color("BLUE");
        Color yellow = new Color("YELLOW");
        List<Color> colors = new ArrayList<>();
        colors.add(red);
        colors.add(green);
        colors.add(blue);
        colors.add(yellow);

        for(var color: colors){
            for(int i = 1; i <= 2; i++){
                deck.add(new Action(color, "SKIP"));
                deck.add(new Action(color, "REVERSE"));
                deck.add(new Action(color, "DRAW 2"));
            }
        }
    }
    public void generateSpecial(){
        Color wild = new Color("WILD");
        for (int i = 0; i < 4; i++){
            deck.add(new Special(wild, "WILD COLOR"));
            deck.add(new Special(wild, "WILD DRAW FOUR"));
        }
    }
    
    public Card peekTopCard(){
        return deck.get(deck.size()-1);
    }
    
    public int getTotalDeck(){
        return deck.size();
    }

    public void shuffleCard(){
        Collections.shuffle(this.deck);
    }

    public List<Card> assignCardsToPlayer(){
        List<Card> handCards = new ArrayList<>();
        int deckSize = deck.size();
        PlayerCards handCardsList;
            if(deck.size() < 7){
                try{
                    throw new NotEnoughCardsException();
                }catch(NotEnoughCardsException e){
                    System.err.println(e.getErrorMessage());
                }
            }else {
                for(int i = 0; i < 7; i++){
                    Card theCard = deck.get(i);
                    deck.remove(i);
                    handCards.add(theCard);
                }
                  
            }
            return handCards;
        }
    
        
}

