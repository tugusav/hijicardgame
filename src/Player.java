
public class Player {
    private String namaPlayer;
    private boolean statusPlayer;
    private PlayerCardList playerCards; 


    public Player(String namaPlayer, PlayerCards playerCards){
        //Konstruktor dari class player
        this.namaPlayer = namaPlayer;
        this.playerCards = playerCards;
    }
    public String getNamePlayer(){
        return this.namaPlayer;
        //untuk nge-return nama player yang sedang bermain (giliran)
    }

    public void showListCards(){
        PlayerCards.showListCards();
         //Ini belum kebayang banget sih tapi gw coba buat implementasi di PlayerCards
    }
       
    public  void getNumCards(){
        PlayerCards.getNumCards();
        //gatau ini wkwkw bener ga ye
    }
}