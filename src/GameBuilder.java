/**
 * GameBuilder
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class GameBuilder {

    public GameBuilder(){
        // int numPlayers = 0;
        // Scanner input = new Scanner(System.in);
        // System.out.printf("Masukkan jumlah pemain : ");
        // numPlayers = input.nextInt();
        // generatePlayers();
    }

    public static List<Player> generatePlayers(int numPlayers, CardDeck deck){
        List<Player> players = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        for(int i = 0; i < numPlayers; i++){
            String name = " ";
            List<Card> handCardList = deck.assignCardsToPlayer();
            PlayerCards playerCards = new PlayerCards(handCardList);
            System.out.printf("Masukkan nama pemain ke - %d", i+1);
            name = input.nextLine();
            Player player1 = new Player(name, playerCards);
            players.add(player1);
        }
        input.close();
        return players;
    } 
    
}