import java.util.Arrays;
import java.util.stream.Stream;


public class GiliranPemain {
    private Arah arah = Arah.SEARAH_JARUM_JAM;
    private int giliran = 0; //var untuk pemain yang dapet giliran saat ini
    private final Player[] players;
    //
    public GiliranPemain(Player[] players) {
        this.players = players;
    }

    public void reverseArah() {
        arah = Arah.BERLAWANAN_JARUM_JAM;
    }
    
    public Player next() {
        giliran = getNextIndex();
        return getGiliranPlayer();
    }

    private int getNextIndex() {
        var increment = arah == Arah.SEARAH_JARUM_JAM ? 1 : -1;
        return (players.length + giliran + increment) % players.length;
    }

    public Player getGiliranPlayer() {
        return players[giliran];
    }
    
    public Stream<Player> stream() {
        return Arrays.stream(players);
    }
}