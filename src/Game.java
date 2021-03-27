import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Game {
    public boolean isHiji = false; //  menentukan game masih berjalan atau tidak
    public List<Card> drawPile = new ArrayList<>(); // untuk mengambil kartu
    public Stack<Card> discardPile = new Stack<>(); // untuk dapat melihat top of stack
    public List<Player> players = new ArrayList<>(); // list of players
    public Player winner = null;

    public Game(List<Card> drawPile, List<Player> players){
        this.drawPile = drawPile;
        this.players = players;
    }

    public void getPlayers(){
        for(int i = 1; i <= players.size(); i++){
            System.out.println("Pemain " + i + ": " + players.get(i).getNamePlayer());
            System.out.println("Jumlah Kartu: " + players.get(i).getTotalPlayerCards());
            if(players.get(i).isPlaying()){
                System.out.println("Sedang giliran");
            } else {
                System.out.println("Tidak sedang giliran");
            }
        }
    }

    public void discard(List<Card> discarded){
        discardPile.addAll(discarded); // menambahkan seluruh kartu ke discard
    }

    public Card peekTopCard(){
        // untuk melihat kartu bagian atas
        return discardPile.peek();
    }

    public List<Card> drawCards(Player player, int numberOfCards){
        List<Card> drawnCards = new ArrayList<>();
        int size = drawPile.size();
        
        for(int i = 0; i < numberOfCards; i++){
            drawnCards.add(drawPile.get(size-i)); 
            // ngambil dari paling belakang (ini implementasi bukan blackhole), mungkin bisa tambahin method/bikin class drawPile
        }

        return drawnCards;
    }

    public void drawTwo(Player player){
        drawCards(player, 2);
    }

    public void drawFour(Player player){
        drawCards(player, 4);
    }
    
    public boolean isGameOver(){
        return winner != null;
    }

    public void setWinner(Player player){
        winner = player;
    }

    //public boolean 


    
    
}
