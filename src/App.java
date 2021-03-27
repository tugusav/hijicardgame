import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        CardDeck deck = new CardDeck();
        Color red = new Color("GREEN");
        Card card1 = new Angka(3, red);
        Card card2 = new Angka(4, red);
        List<Card> playerCards = new ArrayList<>();
        playerCards.add(card1);
        playerCards.add(card2);
        PlayerCards holyCard = new PlayerCards(playerCards);
        Player player1 = new Player("Agung", holyCard);

        System.out.println(player1.getNamePlayer());
        System.out.println(player1.getTotalPlayerCards());
        System.out.println(player1.isPlaying());
        player1.getPlayerCards().drawCard(card2);
        player1.getPlayerCards().showListCards();
        
        Card topCard = deck.peekTopCard();
        System.out.println(topCard.getType() + " " + deck.getTotalDeck());
        deck.shuffleCard();
        topCard = deck.peekTopCard();
        System.out.println(topCard.getType() + " " + deck.getTotalDeck());
        List<Card> shuffledCard = deck.assignCardsToPlayer();
        player1.getPlayerCards().addPlayerCards(shuffledCard);
        player1.getPlayerCards().showListCards();
        System.out.println(deck.getTotalDeck());




    }
}
