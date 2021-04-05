import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;
public class test2{

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        CardDeck deck = new CardDeck();
        boolean isHiji = false; //  menentukan game masih berjalan atau tidak
        Stack<Card> discardPile = new Stack<>(); // untuk dapat melihat top of stack
        Card curCard; // kartu yang ada di discardPile paling atas
        List<Player> players = new ArrayList<>(); // list of players
        Player winner = null;
        Player curPlayer;
        Arah arah = Arah.SEARAH_JARUM_JAM;
        deck.shuffleCard();
        List<Card> playerCard = deck.assignCardsToPlayer();
        PlayerCards test = new PlayerCards(playerCard);
        test.showListCards();
        players = GameBuilder.generatePlayers(3, deck, input);
        System.out.println(players.size());
        // for(int i=0; i< players.size(); i++){
        //     System.out.println(players.get(i).getNamePlayer());
        //     players.get(i).getPlayerCards().showListCards();
        // } 
        GiliranPemain listPemain = new GiliranPemain(players, arah);
        for(int i=0; i< listPemain.getGiliran().size(); i++){
            System.out.println(listPemain.getGiliran().get(i).getNamePlayer());
            listPemain.getGiliran().get(i).getPlayerCards().showListCards();
        } 
        
        
    }
    
}
