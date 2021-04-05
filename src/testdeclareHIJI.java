import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class testdeclareHIJI {
    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        CardDeck deck = new CardDeck();
        List<Player> players = new ArrayList<>(); // list of players
        Arah arah = Arah.SEARAH_JARUM_JAM;
        GiliranPemain listPemain = new GiliranPemain(players, arah);
        int numPlayers = input.nextInt();
        players = GameBuilder.generatePlayers(numPlayers, deck, input);
        listPemain.setListGiliran(players);
        Random randomizer = new Random();
        int indexGiliran = randomizer.nextInt(((numPlayers - 1)) + 1);
        listPemain.setGiliran(indexGiliran);
        Player curPlayer;
        curPlayer = listPemain.getGiliranPlayer();
        Declare declare = new Declare(curPlayer);
        declare.threading();
    }
}
