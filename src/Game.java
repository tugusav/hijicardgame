import java.util.Random;
import java.util.Scanner;

public class Game {
    public Game(){

    }
    public static void clearScreen() {  

        System.out.print("\033[H\033[2J");  
    
        System.out.flush();  
     
     }

    public void showCommmands2(){
        System.out.println("Masukkan angka menu yang ingin kamu lakukan!");
        System.out.println("0. Help (start)");
        System.out.println("1. List Cards (cards)");
        System.out.println("2. Discard (discard)");
        System.out.println("3. Draw (draw)");
        System.out.println("4. Declare HIJI (HIJI)");
        System.out.println("5. List Players (players)");
        System.out.println("6. View Player in Turn (turn)");
        System.out.println("7. Help (help)");
        System.out.println("8. End (end)");
        System.out.println("8. EXIT (exit)");
    }

    public void showPlayers(GiliranPemain players){
        for(int i = 0; i < players.getGiliran().size(); i++){
            System.out.printf("Pemain %d : %s", i+1, players.getGiliran().get(i).getNamePlayer()); System.out.println(" ");
            System.out.println("Jumlah Kartu: " + players.getGiliran().get(i).getTotalPlayerCards());
            if(players.getGiliran().get(i).isPlaying()){
                System.out.println("Sedang giliran");
                System.out.println(" ");
            } else {
                System.out.println("Tidak sedang giliran");
                System.out.println(" ");
            }
        }
    }

    public boolean isCardValid(Card currentCard, Card playedCard, Color currentColor){
        if (playedCard instanceof Angka){
            if (currentCard instanceof Angka){
                Angka c = (Angka) currentCard;
                Angka p = (Angka) playedCard;
                return (c.getColor().equals(p.getColor())) && (c.getNumber() == p.getNumber());
            } else {
                return false;
            }
        } else if (playedCard instanceof Action){
            if(currentCard instanceof Action){
                Action c = (Action) currentCard;
                Action p = (Action) playedCard;
                return (c.getAction().equals(p.getAction())) && (c.getColor().equals(p.getColor()));
            } else {
                return false;
            }
        } else if (playedCard instanceof Special){
            if (currentCard instanceof Special){
                Special c = (Special) currentCard;
                Special p = (Special) playedCard;
                return c.getSpecial().equals(p.getSpecial());
            } else {
                return false;
            }
        } else{
            return false;
        }
        
    }

    public boolean isCardValidNonMultipleDiscard(Card currentCard, Card playedCard, Color currentColor){
        //Color cardColor = new Color(currentCard.getColor());
        if (playedCard instanceof Special){
            return true;
        }else if (currentCard instanceof Special){
            if(currentColor.getColor().equals(playedCard.getColor())){
                return true;
            } else {
                return false;
            }
        } else if (currentCard instanceof Angka){
            if(playedCard instanceof Angka){
                Angka p = (Angka) playedCard;
                Angka c = (Angka) currentCard;
                return  p.getNumber() == c.getNumber() || p.getColor().equals(c.getColor());
            }else {
                return playedCard.getColor().equals(currentCard.getColor()) || playedCard instanceof Special;
            }   
        } else if (currentCard instanceof Action){
            if(playedCard instanceof Action){
                Action c = (Action) currentCard;
                Action p = (Action) playedCard;
                return playedCard.getColor().equals(currentCard.getColor()) || c.getAction().equals(p.getAction()); 
            } 
            return playedCard.getColor().equals(currentCard.getColor());
        }else{
            return false;
        }
    }
    
    public boolean isDrawCardValidDiscard(Card currentCard, Card playedCard, Color currentColor){
        Color cardColor = new Color(currentCard.getColor());
        if (playedCard instanceof Special){
            return true;
        }else if (currentCard instanceof Special){
            if(currentColor.getColor().equals(playedCard.getColor())){
                return true;
            } else {
                return false;
            }
        } else if (currentCard instanceof Angka){
            if(playedCard instanceof Angka){
                Angka p = (Angka) playedCard;
                Angka c = (Angka) currentCard;
                return  p.getNumber() == c.getNumber() || p.getColor().equals(c.getColor());
            }else {
                return playedCard.getColor().equals(currentCard.getColor()) || playedCard instanceof Special;
            }
        } else if (currentCard instanceof Action){
            if(playedCard instanceof Action){
                Action c = (Action) currentCard;
                Action p = (Action) playedCard;
                return c.getAction().equals(p.getAction());
            }
            return playedCard.getCardType().equals(currentCard.getCardType());
        }else{
            return false;
        }
    }

    public void drawCards(int numberOfCards, Player player){
        for(int i=0; i < numberOfCards; i++){
            Card theCard = generateRandomCard();
            player.getPlayerCards().addCard(theCard);
        }
    }

    public void drawTwo(Player player){
        drawCards(2, player);
    }

    public void drawFour(Player player){
        drawCards(4, player);
    }
    

    public boolean isLeft0Card(Player currentPlayer){
        if (currentPlayer.getTotalPlayerCards() == 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean isLeft1Card(Player currentPlayer){
        if (currentPlayer.getTotalPlayerCards() == 1){
            return true;
        }else{
            return false;
        }
    }


    public Card generateFirstCard(){
        String[] warna = {"RED", "GREEN", "BLUE", "YELLOW"};
        Card randomCard;

        Random rand = new Random();
        int randnum1 = rand.nextInt((3-0) + 1) + 0;
        int randnum2 = rand.nextInt((9-0) + 1) + 0;
        
        randomCard = new Angka(randnum2, new Color(warna[randnum1]));
        return randomCard;
    }
    public Card generateRandomCard(){
        String[] tipe = {"NUMBERS", "ACTION", "SPECIAL"};
        String[] warna = {"RED", "GREEN", "BLUE", "YELLOW"};
        String[] tipeAct = {"SKIP", "REVERSE", "DRAW 2"};
        String[] tipeSpec = {"WILDCOLOR", "DRAW 4"};
        String[] warnaSpes = {"WILD"};
        Integer[] ang = {0,1,2,3,4,5,6,7,8,9};
        Card randomCard;
    
        Random rand = new Random();

        int randnum1 = rand.nextInt((2-0) + 1) + 0;
        int randnum2 = rand.nextInt((3-0) + 1) + 0;
        int randnum3 = rand.nextInt((9-0) + 1) + 0;
        int randnum4 = rand.nextInt((2-0) + 1) + 0;
        int randnum5 = rand.nextInt((1-0) + 1) + 0;

        if (randnum1 == 2){
            randomCard = new Special(new Color(warnaSpes[0]), tipeSpec[randnum5]);
        }
        else if (randnum1 == 1){
            randomCard = new Action(new Color(warna[randnum2]), tipeAct[randnum4]);
        }else {
            randomCard = new Angka(ang[randnum3],new Color (warna[randnum2]));
        }
        return randomCard;


    }

    public void viewPlayerInTurn(GiliranPemain players, Arah arah) {
        for(int i = 0; i < players.getGiliran().size(); i++){
            if (players.getGiliran().get(i).isPlaying()) {
                System.out.println("Player dalam giliran: " + players.getGiliran().get(i).getNamePlayer());
                if (players.getArah() == Arah.SEARAH_JARUM_JAM) {
                    System.out.println("Player selanjutnya: " + players.getGiliran().get((i+1) % players.getGiliran().size()).getNamePlayer());
                } else {
                    System.out.println("Player selanjutnya: " + players.getGiliran().get((i-1 + players.getGiliran().size()) % players.getGiliran().size()).getNamePlayer());
                }
            } else {
            }
        }
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
                System.out.println("Masukkan kamu salah! masukkan angka 0-6!\n");
            }

        }
    }
}
