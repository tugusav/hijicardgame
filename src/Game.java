import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class Game {
    // public boolean isHiji = false; //  menentukan game masih berjalan atau tidak
    // public List<Card> drawPile = new ArrayList<>(); // untuk mengambil kartu
    // public Stack<Card> discardPile = new Stack<>(); // untuk dapat melihat top of stack
    // public List<Player> players = new ArrayList<>(); // list of players
    // public Player winner = null;
    // public Player curPlayer;
    // public Arah arah = Arah.SEARAH_JARUM_JAM;
    // public Game(List<Card> drawPile, List<Player> players){
    //     this.drawPile = drawPile;
    //     this.players = players;
    //}

    public Game(){

    }

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        CardDeck deck = new CardDeck();
        boolean isHiji = false; //  menentukan game masih berjalan atau tidak
        Stack<Card> discardPile = new Stack<>(); // untuk dapat melihat top of stack
        Card curCard; // kartu yang ada di discardPile paling atas
        List<Player> players = new ArrayList<>(); // list of players
        Player winner = null;
        Player curPlayer;
        Arah arah = Arah.SEARAH_JARUM_JAM;
        GiliranPemain listPemain = new GiliranPemain(players, arah);
        Random randomizer = new Random();
        Game game = new Game();
        Card discardedCard;
        System.out.println("-------WELCOME TO HIJI GAMES-------");
        System.out.println("1. HELP");
        System.out.println("2. START");
        System.out.println("3. EXIT");

        String commando = input.next();

        while (!commando.toLowerCase().equals("exit")){
            if (commando.toLowerCase().equals("help"))
            {
                help();
            }
            else if (commando.equals("START"))
            {   
                deck.shuffleCard();
                // Memasukkan jumlah pemain
                System.out.println("Masukkan jumlah pemain :");
                int numPlayers = input.nextInt();
                while (numPlayers>6 || numPlayers<2) {
                    System.out.println("Jumlah pemain tidak valid, jumlah pemain harus diantara 2-6");
                    numPlayers = input.nextInt();
                }
                // membuat pemain & membagikan kartu
                players = GameBuilder.generatePlayers(numPlayers, deck, input);
                listPemain.setListGiliran(players);
                System.out.println("Dealer membagi kartu...");
                Thread.sleep(3000);
                // memilih orang pertama untuk bermain
                int indexGiliran = randomizer.nextInt(((numPlayers-1) - 0) + 1) + 0;
                // rand.nextInt((max - min) + 1) + min -> untuk random dengan range min-max
                // memasang giliran pemain di listPemain
                listPemain.setGiliran(indexGiliran);
                // memasang current player pertama
                curPlayer = listPemain.getGiliranPlayer();
                curPlayer.setIsPlaying(); // sekarang pemain sedang bermain

                // generate kartu yang akan dimainkan pertama
                System.out.println("Pemain pertama yang akan bermain adalah " + curPlayer.getNamePlayer());
                curCard = game.generateFirstCard();
                System.out.println("Anda sedang bermain sebagai " + curPlayer.getNamePlayer());
                System.out.println("Generating random card...");
                System.out.println("First card to play is: " + curCard.getType() +"!");
                System.out.println("What will you do?");

                System.out.println("Masukkan angka menu yang ingin kamu lakukan!");
                System.out.println("1. List Cards");
                System.out.println("2. Discard");
                System.out.println("3. Draw");
                System.out.println("4. Declare HIJI");
                System.out.println("5. List Players");
                System.out.println("6. View Player in Turn");
                System.out.println("7. Help");
                System.out.println("8. EXIT");

                int command = input.nextInt();

                switch(command){
                    case 1:
                        curPlayer.getPlayerCards().showListCards();
                    case 2:
                        String option;
                        do  {
                            System.out.println("Masukkan kartu yang ingin kamu mainkan: ");
                            curPlayer.getPlayerCards().showListCards(); //ngeprint list kartu
                            int pilihan = input.nextInt();
                            while (pilihan > curPlayer.getTotalPlayerCards() || pilihan < 1){
                                System.out.println("Range pilihan tidak valid! Masukkan pilihan lagi: ");
                                pilihan = input.nextInt();
                            }
                            // ArrayListGenerics<Card> cardsToDiscard = new ArrayListGenerics<>();
                            discardedCard = curPlayer.getPlayerCards().getCard(pilihan-1);
                            if(!game.isCardValid(curCard, discardedCard)){
                                System.out.println("Kartu yang kamu pilih tidak valid!");
                            } else {
                                curCard = discardedCard; // ngeliat paling atas di discarded pile
                                curPlayer.getPlayerCards().discardCard(discardedCard);
                                System.out.println("Kartu berhasil di discard!");
                            }
                            System.out.println("Apakah kamu ingin mengeluarkan kartu lagi? (y/n): ");
                            input.nextLine();
                            option = input.nextLine(); // y/n
                            }while(!option.equals("n"));
                        
                    case 3:
                        Card drawedCard;
                        drawedCard = game.generateRandomCard();
                        curPlayer.getPlayerCards().addCard(drawedCard);
                    case 4:
                        // Game.declareHiji();
                    case 5:
                        // Game.getPlayers();
                    case 6:
                        // Game.curPlayer;
                    case 7:
                        // Game.help();
                    case 8:
                        break;


                }
            }
            System.out.println("Terimakasih sudah bermain HIJI bersama OOPah Koriya!");
            break;
        }
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

    public boolean isCardValid(Card currentCard, Card playedCard){
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
        Color cardColor = new Color(currentCard.getColor());
        if (playedCard instanceof Special){
            return true;
        }else if (currentCard instanceof Special){
            if(cardColor == currentColor){
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
            return playedCard.getColor().equals(currentCard.getColor()) || currentCard.getCardType().equals(playedCard.getCardType());
        }else{
            return false;
        }
    }

    public void applyPower(int numCardsDiscarded, Card powerCard, Player currentPlayer, GiliranPemain giliran, Color currentColor, Scanner sc){
        String[] warna = {"RED", "GREEN", "BLUE", "YELLOW"};
        if (powerCard.getCardType().equals("DRAW 2")){
            currentPlayer = giliran.next();
            for(int i = 0; i < numCardsDiscarded; i++){
                drawTwo(currentPlayer);
            }
        } else if(powerCard.getCardType().equals("SKIP")){
            for(int i = 0; i < numCardsDiscarded; i++){
                currentPlayer = giliran.skip();
            }    
        } else if (powerCard.getCardType().equals("REVERSE")){
            for(int i = 0; i < numCardsDiscarded; i++){
                giliran.reverseArah();
            }   
            currentPlayer = giliran.next();
        } else if (powerCard.getCardType().equals("WILDCOLOR")){
            System.out.println("Choose color: ");
            for(int i=0; i < warna.length; i++){
                System.out.println((i+1) + warna[i]);
            }
            int pilihan = sc.nextInt();
            currentColor = new Color(warna[pilihan-1]);
            currentPlayer = giliran.next();
        } else if (powerCard.getCardType().equals("DRAW 4")){
            System.out.println("Choose color: ");
            for(int i=0; i < warna.length; i++){
                System.out.println((i+1) + warna[i]);
            }
            int pilihan = sc.nextInt();
            currentColor = new Color(warna[pilihan-1]);
            currentPlayer = giliran.next();
            for(int i = 0; i < numCardsDiscarded; i++){
                drawFour(currentPlayer);
            }
        } else {
            
        }
    }

    public void discard(Card currentCard, Player currentPlayer, Scanner sc, GiliranPemain giliran, Color currentColor){
        boolean isMultipleDiscard = false;
        int numDiscarded = 0;
            String option;
            while(!isMultipleDiscard){
                System.out.println("Masukkan kartu yang ingin kamu mainkan: ");
                currentPlayer.getPlayerCards().showListCards(); //ngeprint list kartu
                int pilihan = sc.nextInt();
                while (pilihan > currentPlayer.getTotalPlayerCards() || pilihan < 1){
                    System.out.println("Range pilihan tidak valid! Masukkan pilihan lagi: ");
                    pilihan = sc.nextInt();
                }
                // ArrayListGenerics<Card> cardsToDiscard = new ArrayListGenerics<>();
                Card discardedCard = currentPlayer.getPlayerCards().getCard(pilihan-1);
                if(!isCardValidNonMultipleDiscard(currentCard, discardedCard, currentColor)){
                    System.out.println("Kartu yang kamu pilih tidak valid!");
                } else {
                    numDiscarded += 1;
                    currentCard = discardedCard; // ngeliat paling atas di discarded pile
                    currentPlayer.getPlayerCards().discardCard(discardedCard);
                    System.out.println("Kartu berhasil di discard!");
                    currentColor = new Color(discardedCard.getColor());
                }
                System.out.println("Apakah kamu ingin mengeluarkan kartu lagi? (y/n): ");
                sc.nextLine();
                option = sc.nextLine(); // y/n
                if(option.equals("y")){
                    isMultipleDiscard = true;
                } else {
                    break;
                }
            }

            while(isMultipleDiscard){
                System.out.println("Masukkan kartu yang ingin kamu mainkan: ");
                currentPlayer.getPlayerCards().showListCards(); //ngeprint list kartu
                int pilihan = sc.nextInt();
                while (pilihan > currentPlayer.getTotalPlayerCards() || pilihan < 1){
                    System.out.println("Range pilihan tidak valid! Masukkan pilihan lagi: ");
                    pilihan = sc.nextInt();
                }
                // ArrayListGenerics<Card> cardsToDiscard = new ArrayListGenerics<>();
                Card discardedCard = currentPlayer.getPlayerCards().getCard(pilihan-1);
                if(!isCardValid(currentCard, discardedCard)){
                    System.out.println("Kartu yang kamu pilih tidak valid!");
                } else {
                    numDiscarded += 1;
                    currentCard = discardedCard; // ngeliat paling atas di discarded pile
                    currentPlayer.getPlayerCards().discardCard(discardedCard);
                    System.out.println("Kartu berhasil di discard!");
                    currentColor = new Color(discardedCard.getColor());
                }
                System.out.println("Apakah kamu ingin mengeluarkan kartu lagi? (y/n): ");
                sc.nextLine();
                option = sc.nextLine(); // y/n
                if(option.equals("n")){
                    isMultipleDiscard = false;
                }
            }
            applyPower(numDiscarded, currentCard, currentPlayer, giliran, currentColor, sc);
        }
        
    

    public Card peekTopCard(){
        // untuk melihat kartu bagian atas
        return discardPile.peek();
    }

    public void drawCards(int numberOfCards, Player player){
        for(int i=0; i < numberOfCards; i++){
            Card theCard = generateRandomCard();
            player.getPlayerCards().addCard(theCard);
        }
        // if (numberOfCards == 2){
        //     generateRandomCard();
        //     generateRandomCard();
        // }else if (numberOfCards == 4){
        //     generateRandomCard();
        //     generateRandomCard();
        //     generateRandomCard();
        //     generateRandomCard();
        // }else{
        //     generateRandomCard();
        // }
    }

    public void drawTwo(Player player){
        drawCards(2, player);
    }

    public void drawFour(Player player){
        drawCards(4, player);
    }
    
    public boolean isGameOver(){
        return winner != null;
    }

    public void setWinner(Player player){
        this.winner = player;
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
