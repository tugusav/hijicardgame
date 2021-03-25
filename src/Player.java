
public class Player {
    private String namaPlayer;
    private boolean isPlaying = false;
    private PlayerCards playerCards; 

    public Player(String namaPlayer, PlayerCards playerCards){
        //Konstruktor dari class player
        this.namaPlayer = namaPlayer;
        this.playerCards = playerCards;
    }
    public String getNamePlayer(){
        return this.namaPlayer;
        //untuk nge-return nama player yang sedang bermain (giliran)
    }

    public void showPlayerCards(){
        this.playerCards.showListCards();
         //Ini belum kebayang banget sih tapi gw coba buat implementasi di PlayerCards
    }
       
    public  void getNumCards(){
        this.playerCards.getNumCards();
        //gatau ini wkwkw bener ga ye
    }
}