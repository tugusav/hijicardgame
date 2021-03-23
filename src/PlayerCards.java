
public class PlayerCardList extends Card {
    private List<Card> playerCards = new ArrayList<>();
    public int numCards = 0;
    public void drawCard(Card drawCard){

    }

    public void discardCard(Card currentCard) {

    }

    public void int jumlahCardPlayer(Card playerCards){
        for(int i =0; i<playerCards.length;i++){
            numCards += 1;
        }
        System.out.println("Jumlah kartu %s\n adalah %d\n", namaPlayer, numCards);
    }
}