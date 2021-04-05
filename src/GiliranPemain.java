//import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
//import java.util.stream.Stream;


public class GiliranPemain {
    private Arah arah = Arah.SEARAH_JARUM_JAM;
    private int giliran = 0; //var untuk pemain yang dapet giliran saat ini
    //private final Player[] players;
    private List<Player> listGiliran = new ArrayList<>();
    //
    public GiliranPemain(List<Player> listPlayer, Arah arah){
        this.arah = arah;
        this.listGiliran = listPlayer;
    }
    
    public void setListGiliran(List<Player> giliranplayers) {
        this.listGiliran = giliranplayers;
    }

    public List<Player> getGiliran(){
        return this.listGiliran;
    }

    // public ArrayListGenerics<Player> getListPlayer(){
    //     return this.giliranplayers;
    // }
    // public ArrayListGenerics<Player> getListPlayer() 

    public void reverseArah() {
    if (arah == Arah.SEARAH_JARUM_JAM){
        arah = Arah.BERLAWANAN_JARUM_JAM;
    }
    else{
        arah = Arah.SEARAH_JARUM_JAM;
    }
        
    }
    public Arah getArah() {
        return this.arah;
    }
    
    public Player next() {
        giliran = getNextIndex();
        return getGiliranPlayer();
    }

    public Player skip() {
        giliran = getNextIndex();
        giliran = getNextIndex();
        return getGiliranPlayer();
    }

    // private int getNextIndex() {
    //     var addition = arah == Arah.SEARAH_JARUM_JAM ? 1 : -1;
    //     return (listGiliran.size() + giliran + addition) % listGiliran.size();
    // }

    private int getNextIndex() {
        int addition;
        if (arah != Arah.SEARAH_JARUM_JAM ){
            addition = -1;
        }
        else{
            addition = 1;
        }
        int getidx = (listGiliran.size() + giliran + addition) % listGiliran.size();
        return getidx;
    }



    public Player getGiliranPlayer() {
        return listGiliran.get(giliran);

    }

    public void setGiliran(int giliran){
        this.giliran = giliran;
    }

    

    //public Stream<Player> stream() {
    //    return Arrays.stream(giliranplayers);
    //}
}