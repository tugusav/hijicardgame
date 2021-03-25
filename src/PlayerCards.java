import java.util.ArrayList;
import java.util.List;

public class PlayerCards{
    private List<Card> playerCards = new ArrayList<>();
    public int numCards = 0;

    public void drawCard(List<Card> drawedCards){
        for(int i = 0; i<drawedCards.size();i++){
        //Nambahin berupa list of cards (kalo nambah kartunya banyak)
        drawCard(drawedCards.get(i));
        this.numCards++;
        }
    }
    public void drawCard(Card drawCard){
        playerCards.add(drawCard);
        //Ini bakal add one element di akhir list gitu, reference dari geeks for geeks
        //Ini belum selese nunggu kelas card selese
    }

    public void discardCard(Card currentCard) {
        //ini belum kebayang jujur AWKAOWAKWOAKWO

    }

    public void printNumCards(Player player){
        System.out.format("Jumlah kartu %s\n adalah %d\n", player.getNamePlayer() , this.getNumCards());
        //Ini bakal ngeluarin output berupa jumlah kartu yang dipegan oleh seorang
    }
    public int getNumCards(){
        return this.numCards;
    }

    public void showListCards(){
        for(int i = 1; i <= playerCards.size(); i++){
            System.out.println(i + ". " + playerCards.get(i).getType());
        }
    }
}