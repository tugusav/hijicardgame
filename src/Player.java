
public class Player extends playerCards {
    private String namaPlayer;
    private boolean statusPlayer;
    private PlayerCardList playerCards; 


    public Player(String namaPlayer, PlayerCardList playerCards){
        
        this.namaPlayer = namaPlayer;
        this.playerCards = playerCards;
    }
    public String getNamePlayer(){
        return this.namaPlayer;
    }

    public void String showCards(){
       

    }

    public getNumCard(Card listPlayerCard){
        jumlahCardPlayer(listPlayerCard);
    }

}