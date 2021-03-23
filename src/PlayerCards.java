
public class PlayerCards extends Player {
    private List<Card> playerCards = new ArrayList<>();
    public int numCards = 0;
    public void drawCard(Card drawNCard){
        playerCards.addAll(drawNCard);
        //Nambahin berupa list of cards (kalo nambah kartunya banyak)
    }
    public void drawCard(Type drawOneCard){
        playerCards.add(drawOneCard)
        //Ini bakal add one element di akhir list gitu, reference dari geeks for geeks
        //Ini belum selese nunggu kelas card selese
    }

    public void discardCard(Card currentCard) {
        //ini belum kebayang jujur AWKAOWAKWOAKWO
    }

    public void getNumCards(Card playerCards){
        for(int i =0; i<playerCards.length;i++){
            numCards += 1;
        //Ini dia iterate nambah terus jumlah kartunya tiap ngedtect ada kartu
        }
        System.out.println("Jumlah kartu %s\n adalah %d\n", namaPlayer, numCards);
        //Ini bakal ngeluarin output berupa jumlah kartu yang dipegan oleh seorang
    }

    public void showListCards(){
        return this.playerCards;
    }
}