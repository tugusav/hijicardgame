
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
    public PlayerCards getPlayerCards(){
        return this.playerCards;
    }

    public void showPlayerCards(){
        this.playerCards.showListCards();
         //Ini belum kebayang banget sih tapi gw coba buat implementasi di PlayerCards
    }
       
    public int getTotalPlayerCards(){
        return this.playerCards.getNumCards();
        //gatau ini wkwkw bener ga ye
    }
    public boolean isPlaying(){
        return isPlaying;
    }
    public void setPlayerCards(PlayerCards playerCards){
        this.playerCards = playerCards;
    }

    public void setIsPlaying(){
        this.isPlaying = true;
    }

    public void setIsNotPlaying(){
        this.isPlaying = false;
    }
}