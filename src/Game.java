import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Game {
    public boolean isHiji = false; //  menentukan game masih berjalan atau tidak
    public List<Card> drawPile = new ArrayList<>(); // untuk mengambil kartu
    public Stack<Card> discardPile = new Stack<>(); // untuk dapat melihat top of stack
    public List<Player> players = new ArrayList<>(); // list of players
    public Player winner = null;
    public Player curPlayer;
    public Arah arah = Arah.SEARAH_JARUM_JAM;
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

    public void drawCards(int numberOfCards){
        if (numberOfCards == 2){
            generateRandomCard();
            generateRandomCard();
        }else if (numberOfCards == 4){
            generateRandomCard();
            generateRandomCard();
            generateRandomCard();
            generateRandomCard();
        }else{
            generateRandomCard();
        }
    }

    public void drawTwo(){
        drawCards(2);
    }

    public void drawFour(){
        drawCards(4);
    }
    
    public boolean isGameOver(){
        return winner != null;
    }

    public void setWinner(Player player){
        winner = player;
    }

    public boolean isLeft1Card(Player currentPlayer){
        if (currentPlayer.getTotalPlayerCards() == 1){
            return true;
        }else{
            return false;
        }
    }

    public void declareHiji(){
        if (isLeft1Card(curPlayer)) {
            System.out.println("HIJI");
        } else {
            drawTwo();
        }

    }

    public void generateRandomCard(){
        String[] tipe = {"NUMBERS", "ACTION", "SPECIAL"};
        String[] warna = {"RED", "GREEN", "BLUE", "YELLOW"};
        String[] tipeAct = {"SKIP", "REVERSE", "DRAW 2"};
        String[] tipeSpec = {"WILDCOLOR", "DRAW 4"};
        String[] warnaSpes = {"WILD"};
        Integer[] ang = {0,1,2,3,4,5,6,7,8,9};
    
        Random rand = new Random();

        int randnum1 = rand.nextInt((2-0) + 1) + 0;
        int randnum2 = rand.nextInt((3-0) + 1) + 0;
        int randnum3 = rand.nextInt((9-0) + 1) + 0;
        int randnum4 = rand.nextInt((2-0) + 1) + 0;
        int randnum5 = rand.nextInt((1-0) + 1) + 0;

        if (randnum1 == 2){
            Special spesial = new Special(new Color(warnaSpes[0]), tipeSpec[randnum5]);
        }
        else if (randnum1 == 1){
            Action action = new Action(new Color(warna[randnum2]), tipeAct[randnum4]);
        }else {
            Angka angka = new Angka(ang[randnum3],new Color (warna[randnum2]));
        }


    }

    public void viewPlayerInTurn() {
        for(int i = 1; i <= players.size(); i++){
            if (players.get(i).isPlaying()) {
                System.out.println("Player dalam giliran: " + players.get(i).getNamePlayer());
                if (arah == Arah.SEARAH_JARUM_JAM) {
                    System.out.println("Player selanjutnya: " + players.get((i+1) % players.size()).getNamePlayer());
                } else {
                    System.out.println("Player selanjutnya: " + players.get((i-1 + players.size()) % players.size()).getNamePlayer());
                }
            } else {

            }
        }
    }

    public void showListPlayer(int banyakPlayer, Player player){
        for(int i = 0; i < banyakPlayer; i++){
            System.out.printf("Pemain %d", i, ": %s", player.getNamePlayer());
            System.out.printf("Jumlah Kartu: %d", player.getPlayerCards());
            //Gw bingung anuin giliran pemainnya :( duh maap gais gw lagi bego banget :(
        }

    }

    public void start(){
        
    }
    
    public static void help(){
        System.out.println("\nGimana sih cara main HIJI\n" +
                "1. HIJI dimainkan oleh 2-6 pemain.\n" +
                "2. Di awal permainan, semua pemain akan mendapatkan 7 buah kartu, dan satu kartu angka dipilih secara acak untuk dijadikan kartu awal.\n" +
                "3. Pemain yang akan memulai giliran pertama akan diacak.\n" +
                "4. Pada setiap giliran, pemain boleh mengeluarkan satu atau lebih kartu yang dapat dimainkan pada giliran tersebut.\n" +
                "5. Apabila pemain tidak mengeluarkan kartu, pemain wajib mengambil satu kartu dari infinite deck\n" +
                "6. Apabila kartu yang baru diambil tersebut bisa dikeluarkan, pemain boleh mengeluarkan kartu tersebut (tidak wajib).\n" +
                "7. Beberapa jenis kartu memiliki power tertentu yang dapat memengaruhi jalannya permainan\n" +
                "8. Apabila pemain memiliki sisa satu kartu, maka pemain harus melakukan “Declare HIJI” dalam waktu 3 detik. Apabila tidak, pemain wajib mengambil dua kartu dari deck.\n" +
                "9. Pemain dinyatakan menang apabila kartu yang dipegangnya sudah habis, dan permainan selesai.");

        System.out.println("\n\nJenis Kartu HIJI\n" +
                "1. Angka 0-9 warna Merah/Hijau/Kuning/Biru\n" +
                "2. Draw 2 (+2) warna Merah/Hijau/Kuning/Biru\n" +
                "3. Draw 4 (+4)\n" +
                "4. Wildcard\n" +
                "5. Reverse warna Merah/Hijau/Kuning/Biru\n" +
                "6. Skip warna Merah/Hijau/Kuning/Biru\n");

        Scanner sc = new Scanner(System.in);
        int opsi; boolean option = true;

        while(option) {
            System.out.println("Pilih nomor kartu untuk info lebih lanjut : (0 untuk keluar)");
            opsi = sc.nextInt();
            if (opsi == 1) {
                System.out.println("\nDapat dikeluarkan jika : Kartu yang dimainkan sebelumnya berwarna sama, atau memiliki angka yang sama.\n" +
                        "Power : - \n");
            } else if (opsi == 2) {
                System.out.println("Dapat dikeluarkan jika : Kartu yang dimainkan sebelumnya berwarna sama, atau juga kartu Draw 2.\n" +
                        "Power : Pemain selanjutnya harus mengambil 2 kartu. Apabila pemain tersebut " +
                        "mengeluarkan Draw 2, maka pemain selanjutnya mengambil 4, dan seterusnya. Pemain yang " +
                        "mengambil kartu tambahan kehilangan gilirannya dan dilewati. \n");
            } else if (opsi == 3) {
                System.out.println("Dapat dikeluarkan jika : Kapanpun\n" +
                        "Power : Pemain selanjutnya harus mengambil 4 kartu, dan pemain " +
                        "yang mengeluarkan kartu Draw 4 dapat memilih warna yang dapat dimainkan selanjutnya. \n");
            } else if (opsi == 4) {
                System.out.println("Dapat dikeluarkan jika : Kapanpun\n" +
                        "Power : Pemain dapat memilih warna yang dapat dikeluarkan oleh pemain selanjutnya. \n");
            } else if (opsi == 5) {
                System.out.println("Dapat dikeluarkan jika : Kartu yang dimainkan sebelumnya berwarna sama, atau juga kartu Reverse.\n" +
                        "Power : Urutan pemain dibalik.\n");
            } else if (opsi == 6) {
                System.out.println("Dapat dikeluarkan jika : Kartu yang dimainkan sebelumnya berwarna sama, atau juga kartu Skip\n" +
                        "Power : Pemain selanjutnya dilewati (kehilangan giliran).\n");
            } else if (opsi == 0) {
                option = false;
            } else {
                System.out.println("Teu bener sia teh!\n");
            }
        }
    }
}
