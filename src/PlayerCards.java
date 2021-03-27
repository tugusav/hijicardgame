import java.util.ArrayList;
import java.util.List;

public class PlayerCards{
    private List<Card> playerCards = new ArrayList<>();
    public int numCards = 0;

    public PlayerCards(List<Card> playerCards){
        this.playerCards = playerCards;
        this.numCards = playerCards.size();
    }
    public int getNumCards(){
        return this.numCards;
    }


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
    }

    public void discardCard(Card currentCard) {
        playerCards.remove(currentCard);
        this.numCards--;
        //ini bakal remove ato ngeluarin kartu yang akan digunakan di permainan HIJI
    }

    public void discardCard2(Card cardNdemolish){
        while(playerCards.contains(cardNdemolish)){
            playerCards.remove(cardNdemolish);
            this.numCards--;
        }
        //Kepikirannya jadi pake while dia buat ngecek gitu kalo ada kartu yang tipenya sama atau ngga, kalau ada dia return true
        //, terus lanjut ngeremove object card nya
    }

    public void printNumCards(Player player){
        System.out.format("Jumlah kartu %s\n adalah %d\n", player.getNamePlayer() , this.getNumCards());
        //Ini bakal ngeluarin output berupa jumlah kartu yang dipegan oleh seorang
    }
 

    public void showListCards(){
        for(int i = 0; i < playerCards.size(); i++){
            System.out.println(i+1 + ". " + playerCards.get(i).getType());
        }
    }
}