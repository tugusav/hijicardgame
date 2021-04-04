//import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
//import java.util.stream.Stream;


public class GiliranPemain {
    private Arah arah = Arah.SEARAH_JARUM_JAM;
    private int giliran = 0; //var untuk pemain yang dapet giliran saat ini
    //private final Player[] players;
    private List<Player> giliranplayers = new ArrayList<>();
    //
    public GiliranPemain(List<Player> giliranplayers) {
        this.giliranplayers = giliranplayers;
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
        return (giliranplayers.size() + giliran + increment) % giliranplayers.size();
    }

    public Player getGiliranPlayer() {
        return giliranplayers.get(giliran);

    }

    //public Stream<Player> stream() {
    //    return Arrays.stream(giliranplayers);
    //}
}