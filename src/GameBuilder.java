/**
 * GameBuilder
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class GameBuilder {

    public GameBuilder(){
        int numPlayers = 0;
        Scanner input = new Scanner(System.in);
        System.out.printf("Masukkan jumlah pemain : ");
        numPlayers = input.nextLine();
        generatePlayers();
    }

    public List<Player> generatePlayers(numPlayers, CardDeck deck){
        List<Player> players = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        for(int i = 0; i < numPlayers; i++){
            String name = " ";
            List<Card> handCardList = deck.assignCardsToPlayer();
            PlayerCards playerCards = new PlayerCards(handCardList);
            System.out.printf("Masukkan nama pemain ke - %i", i+1);
            name = input.nextLine();
            Player player1 = new Player(name, playerCards);
            players.add(player1);
        }
        return players;
    } 
}