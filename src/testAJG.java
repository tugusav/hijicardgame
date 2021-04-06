import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class testAJG {  
    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        CardDeck deck = new CardDeck();
        Card curCard; // kartu yang ada di discardPile paling atas
        List<Player> players = new ArrayList<>(); // list of players
        Player winner = null;
        Player curPlayer;
        Arah arah = Arah.SEARAH_JARUM_JAM;
        GiliranPemain listPemain = new GiliranPemain(players, arah);
        Random randomizer = new Random();
        Game game = new Game();
        Card discardedCard;
        Color currentColor;
        boolean isStartGame = false;
        System.out.println("-------WELCOME TO HIJI GAMES-------");
        System.out.println("1. START");
        System.out.println("2. HELP");
        System.out.println("3. EXIT");

        String command = input.next();

        while (!command.toLowerCase().equals("exit")){
            if (command.toLowerCase().equals("help")){
                Game.help();
                System.out.println("1. START");
                System.out.println("2. HELP");
                System.out.println("3. EXIT");
                System.out.println(" ");
                command = input.next();
            }

            else if (command.toLowerCase().equals("start") && (!isStartGame)){   
                // Memasukkan jumlah pemain
                deck.shuffleCard();
                System.out.println("Masukkan jumlah pemain :");
                int numPlayers = input.nextInt();
                while (numPlayers>6 || numPlayers<2) {
                    System.out.println("Jumlah pemain tidak valid, jumlah pemain harus diantara 2-6");
                    numPlayers = input.nextInt();
                }
                // membuat pemain & membagikan kartu
                players = GameBuilder.generatePlayers(numPlayers, deck, input);
                listPemain.setListGiliran(players);
                //System.out.println(players.size());
                System.out.println("Dealer membagi kartu...");
                Thread.sleep(3000);
                for(Player player: players){
                    System.out.println(player.getNamePlayer());
                }
                
                // memilih orang pertama untuk bermain
                int indexGiliran = randomizer.nextInt(((numPlayers-1) - 0) + 1) + 0;
                // rand.nextInt((max - min) + 1) + min -> untuk random dengan range min-max
                // memasang giliran pemain di listPemain
                //System.out.println("GW GANTENG" + " " + indexGiliran);
                listPemain.setGiliran(indexGiliran);
                // memasang current player pertama

                curPlayer = listPemain.getGiliranPlayer();
                curPlayer.setIsPlaying(); // sekarang pemain sedang bermain

                // generate kartu yang akan dimainkan pertama
                System.out.println(" ");
                System.out.println("Pemain pertama yang akan bermain adalah " + curPlayer.getNamePlayer());
                curCard = game.generateFirstCard();
                System.out.println("Anda sedang bermain sebagai " + curPlayer.getNamePlayer());
                System.out.println("Generating random card...");
                Thread.sleep(2000);
                System.out.println("The first card is :" + curCard.getType());
                currentColor = new Color(curCard.getColor());
                isStartGame = true;

                System.out.println("Masukkan perintah menu yang ingin kamu lakukan! kalau lupa ketik (command)");
                System.out.println(" ");
                command = input.next();
                boolean haveWinner = false;
                loop3:
                while(isStartGame){
                    
                    if (command.toLowerCase().equals("cards") && (isStartGame)){
                        curPlayer.getPlayerCards().showListCards();
                        System.out.println("Masukkan perintah menu yang ingin kamu lakukan! kalau lupa ketik (command)");
                        System.out.println(" ");
                        command = input.next();
                    }
                    
                    else if (command.toLowerCase().equals("discard") && (isStartGame)){
                        // discard cards
                        boolean isMultipleDiscard = false;
                        boolean timeForPower = false;
                        boolean isDrawTwo = false;
                        boolean loop = true;
                        boolean loop2 = true;
                        int numDiscarded = 0;
                        String option = "";
                        boolean gakJadi = false;
                        System.out.println("Current Card: " + curCard.getType());
                        System.out.println("Current Color: " + currentColor.getColor());
                        declareHiji1:
                        while (!isMultipleDiscard && loop) {
                            System.out.println("Masukkan kartu yang ingin kamu mainkan: ");
                            curPlayer.getPlayerCards().showListCards(); //ngeprint list kartu
                            int pilihan = input.nextInt();
                            while (pilihan > curPlayer.getTotalPlayerCards() || pilihan < 1) {
                                System.out.println("Range pilihan tidak valid! Masukkan pilihan lagi: ");
                                pilihan = input.nextInt();
                            }
                            discardedCard = curPlayer.getPlayerCards().getCard(pilihan - 1);
                            System.out.println("Kartu sekarang: " + curCard.getType());
                            System.out.println("Kartu yang dipilih: " + discardedCard.getType());

                            if (!game.isCardValidNonMultipleDiscard(curCard, discardedCard, currentColor)) {
                                System.out.println("Kartu yang kamu pilih tidak valid!");
                            } else {
                                numDiscarded += 1;
                                curCard = discardedCard; // ngeliat paling atas di discarded pile
                                curPlayer.getPlayerCards().discardByIndex(pilihan - 1);
                                System.out.println("Kartu berhasil di discard!");
                                if(game.isLeft1Card(curPlayer)){
                                    System.out.println("ini declareHiji1");
                                    Declare declareHiji = new Declare(curPlayer);
                                    declareHiji.threading();
                                    curPlayer.setIsNotPlaying();
                                    curPlayer = listPemain.next();
                                    break declareHiji1;
                                }
                                if(game.isLeft0Card(curPlayer)){
                                    haveWinner = true;
                                    isStartGame = false;
                                    winner = curPlayer;
                                    break loop3;
                                }
                                currentColor = new Color(discardedCard.getColor());
                            }
                            System.out.println("Apakah kamu ingin mengeluarkan kartu lagi? (y/n): ");
                            input.nextLine();
                            option = input.next(); // y/n
                            if (numDiscarded == 0) {
                                //belum ambil kartu
                                isMultipleDiscard = false;
                                gakJadi = true;
                                loop = false;
                            } else {
                                if (option.equals("y")) {
                                    isMultipleDiscard = true;
                                } else {
                                    timeForPower = true;
                                    loop = false;
                                }
                            }
                        }
                        declareHiji2:
                        while (isMultipleDiscard && loop2) {
                            // System.out.println("hayoloh masuk multiple");
                            System.out.println("Masukkan kartu yang ingin kamu mainkan: ");
                            curPlayer.getPlayerCards().showListCards(); //ngeprint list kartu
                            int pilihan = input.nextInt();
                            while (pilihan > curPlayer.getTotalPlayerCards() || pilihan < 1) {
                                System.out.println("Range pilihan tidak valid! Masukkan pilihan lagi: ");
                                pilihan = input.nextInt();
                            }
                            // ArrayListGenerics<Card> cardsToDiscard = new ArrayListGenerics<>();
                            discardedCard = curPlayer.getPlayerCards().getCard(pilihan - 1);
                            System.out.println(discardedCard.getType());
                            System.out.println(curCard.getType());
                            if (!game.isCardValid(curCard, discardedCard, currentColor)) {
                                System.out.println("Kartu yang kamu pilih tidak valid!");
                            } else {
                                numDiscarded += 1;
                                curCard = discardedCard; // ngeliat paling atas di discarded pile
                                curPlayer.getPlayerCards().discardByIndex(pilihan - 1);
                                System.out.println("Kartu berhasil di discard!");
                                currentColor = new Color(discardedCard.getColor());
                                if(game.isLeft1Card(curPlayer)){
                                    System.out.println("ini declareHiji2");
                                    Declare declareHiji = new Declare(curPlayer);
                                    declareHiji.threading();
                                    // break declareHiji2;
                                }
                                if(game.isLeft0Card(curPlayer)){
                                    haveWinner = true;
                                    isStartGame = false;
                                    winner = curPlayer;
                                    break loop3;
                                }
                            }
                            System.out.println("Apakah kamu ingin mengeluarkan kartu lagi? (y/n): ");
                            input.nextLine();
                            option = input.nextLine(); // y/n
                            if (option.equals("n")) {
                                isMultipleDiscard = false;
                                timeForPower = true;
                                loop2 = false;
                            } else {
                                isMultipleDiscard = true;
                            }
                        }
                        if (numDiscarded < 1 && !isMultipleDiscard) {
                            // dia masuk menu discard, tapi gajadi ngeluarin kartu
                            Card drawedCard;
                            drawedCard = game.generateRandomCard(); // generate random card
                            curPlayer.getPlayerCards().addCard(drawedCard);
                            System.out.println("Kartu kamu bertambah 1!");
                            System.out.println("Giliran kamu diakhiri !");
                            curPlayer.setIsNotPlaying();
                            curPlayer = listPemain.next();
                            curPlayer.setIsPlaying();
                            System.out.println("Sekarang yang main adalah " + curPlayer.getNamePlayer());
                            // System.out.println("Masukkan command yang ingin kamu lakukan!");
                            // command = input.next();
                            
                        } else {
                            power:
                            while (timeForPower) {
                                // System.out.println("Masuk ke bagian sini");
                                Game.clearScreen();
                                String[] warna = {"RED", "GREEN", "BLUE", "YELLOW"};
                                curPlayer.setIsNotPlaying();
                                //System.out.println(curCard.getCardType());
                                if (curCard instanceof Action) {
                                    Action c = (Action) curCard;
                                    if (c.getAction().equals("DRAW 2")) {
                                        isDrawTwo = true;
                                        curPlayer = listPemain.next();
                                        declareHiji3:
                                        while(isDrawTwo) {
                                            int secondaryDiscarded = 0;
                                            boolean isMultipleDrawDiscard = false;
                                            boolean loop3 = true;
                                            boolean loop4 = true;
                                            // Special s = (Special) curCard;
                                            System.out.println("Giliran selanjutnya!");
                                            System.out.println(curPlayer.getNamePlayer() + " Keluarkan draw 2 lain jika ingin selamat");
                                            while (!isMultipleDrawDiscard && loop3) {
                                                System.out.println("Masukkan kartu yang ingin kamu mainkan: ");
                                                curPlayer.getPlayerCards().showListCards(); //ngeprint list kartu
                                                int pilihan = input.nextInt();
                                                while (pilihan > curPlayer.getTotalPlayerCards() || pilihan < 1) {
                                                    System.out.println("Range pilihan tidak valid! Masukkan pilihan lagi: ");
                                                    pilihan = input.nextInt();
                                                }
                                                discardedCard = curPlayer.getPlayerCards().getCard(pilihan - 1);
                                                System.out.println("Kartu sekarang: " + curCard.getType());
                                                System.out.println("Kartu yang dipilih: " + discardedCard.getType());

                                                if (!game.isCardValidNonMultipleDiscard(curCard, discardedCard, currentColor)) {
                                                    System.out.println("Kartu yang kamu pilih tidak valid!");
                                                } else {
                                                    numDiscarded += 1;
                                                    secondaryDiscarded += 1;
                                                    curCard = discardedCard; // ngeliat paling atas di discarded pile
                                                    curPlayer.getPlayerCards().discardByIndex(pilihan - 1);
                                                    System.out.println("Kartu berhasil di discard!");
                                                    currentColor = new Color(discardedCard.getColor());
                                                    if(game.isLeft1Card(curPlayer)){
                                                        System.out.println("ini declareHiji3");
                                                        Declare declareHiji = new Declare(curPlayer);
                                                        declareHiji.threading();
                                                        curPlayer.setIsNotPlaying();
                                                        curPlayer = listPemain.next();
                                                        // break declareHiji3;
                                                    }
                                                    if(game.isLeft0Card(curPlayer)){
                                                        haveWinner = true;
                                                        isStartGame = false;
                                                        winner = curPlayer;
                                                        break loop3;
                                                    }
                                                }
                                                System.out.println("Apakah kamu ingin mengeluarkan kartu lagi? (y/n): ");
                                                input.nextLine();
                                                option = input.next(); // y/n
                                                if (secondaryDiscarded == 0) {
                                                    isMultipleDrawDiscard = false;
                                                    gakJadi = true;
                                                    loop3 = false;
                                                } else {
                                                    if (option.equals("y")) {
                                                        isMultipleDrawDiscard = true;
                                                    } else {
                                                        timeForPower = true;
                                                        loop3 = false;
                                                    }
                                                }
                                            }
                                            declareHiji4:
                                            while (isMultipleDrawDiscard && loop4) {
                                                // System.out.println("hayoloh masuk multiple");
                                                System.out.println("Masukkan kartu yang ingin kamu mainkan: ");
                                                curPlayer.getPlayerCards().showListCards(); //ngeprint list kartu
                                                int pilihan = input.nextInt();
                                                while (pilihan > curPlayer.getTotalPlayerCards() || pilihan < 1) {
                                                    System.out.println("Range pilihan tidak valid! Masukkan pilihan lagi: ");
                                                    pilihan = input.nextInt();
                                                }
                                                // ArrayListGenerics<Card> cardsToDiscard = new ArrayListGenerics<>();
                                                discardedCard = curPlayer.getPlayerCards().getCard(pilihan - 1);
                                                System.out.println(discardedCard.getType());
                                                System.out.println(curCard.getType());
                                                if (!game.isCardValid(curCard, discardedCard, currentColor)) {
                                                    System.out.println("Kartu yang kamu pilih tidak valid!");
                                                } else {
                                                    numDiscarded += 1;
                                                    secondaryDiscarded += 1;
                                                    curCard = discardedCard; // ngeliat paling atas di discarded pile
                                                    curPlayer.getPlayerCards().discardByIndex(pilihan - 1);
                                                    System.out.println("Kartu berhasil di discard!");
                                                    currentColor = new Color(discardedCard.getColor());
                                                    if(game.isLeft1Card(curPlayer)){
                                                        System.out.println("ini declareHiji4");
                                                        Declare declareHiji = new Declare(curPlayer);
                                                        declareHiji.threading();
                                                        // break declareHiji4;
                                                    }
                                                    if(game.isLeft0Card(curPlayer)){
                                                        haveWinner = true;
                                                        isStartGame = false;
                                                        winner = curPlayer;
                                                        break loop3;
                                                    }
                                                }
                                                System.out.println("Apakah kamu ingin mengeluarkan kartu lagi? (y/n): ");
                                                input.nextLine();
                                                option = input.nextLine(); // y/n
                                                if (option.equals("n")) {
                                                    isMultipleDrawDiscard = false;
                                                    timeForPower = true;
                                                    loop4 = false;
                                                } else {
                                                    isMultipleDrawDiscard = true;
                                                }
                                            }
                                            if (secondaryDiscarded < 1 && !isMultipleDrawDiscard) {
                                                for (int i = 0; i < numDiscarded; i++) {
                                                    game.drawTwo(curPlayer);
                                                }
                                                isDrawTwo = false;
                                            }
                                            curPlayer = listPemain.next();
                                        }
                                    } else if (c.getAction().equals("SKIP")) {
                                        for (int i = 0; i < numDiscarded; i++) {
                                            curPlayer = listPemain.skip();
                                        }
                                    } else if (c.getAction().equals("REVERSE")) {
                                        for (int i = 0; i < numDiscarded; i++) {
                                            listPemain.reverseArah();
                                        }
                                        curPlayer = listPemain.next();
                                    }
                                } else if (curCard instanceof Special) {
                                    Special c = (Special) curCard;
                                    if (c.getSpecial().equals("WILDCOLOR")) {
                                        System.out.println("Choose color: ");
                                        for (int i = 0; i < warna.length; i++) {
                                            System.out.println((i + 1) + ". " + warna[i]);
                                        }
                                        int pilihan = input.nextInt();
                                        while (pilihan < 0 || pilihan > warna.length) {
                                            System.out.println("Warna tidak valid, silakan masukkan warna lagi!: ");
                                            pilihan = input.nextInt();
                                        }
                                        currentColor = new Color(warna[pilihan - 1]);
                                        curPlayer = listPemain.next();
                                    } else if (c.getSpecial().equals("DRAW 4")) {
                                        System.out.println("Choose color: ");
                                        for (int i = 0; i < warna.length; i++) {
                                            System.out.println((i + 1) + ". " + warna[i]);
                                        }
                                        int pilihan = input.nextInt();
                                        while (pilihan < 0 || pilihan > warna.length) {
                                            System.out.println("Warna tidak valid, silakan masukkan warna lagi!: ");
                                            pilihan = input.nextInt();
                                        }
                                        currentColor = new Color(warna[pilihan - 1]);
                                        curPlayer = listPemain.next();
                                        for (int i = 0; i < numDiscarded; i++) {
                                            game.drawFour(curPlayer);
                                        }
                                    }
                                } else {
                                    curPlayer = listPemain.next();
                                    currentColor = new Color(curCard.getColor());
                                }
                                break power; //ini break unutk keluar dari discard function

                            }

                        }
                        // saatnya ganti pemain
                        
                        curPlayer.setIsPlaying();
                        System.out.println("Sekarang giliran " + curPlayer.getNamePlayer());
                        System.out.println("Masukkan perintah menu yang ingin kamu lakukan! kalau lupa ketik (command)");
                        System.out.println(" ");
                        command = input.next();
                        // break;
                }
    
                    else if (command.toLowerCase().equals("draw") && (isStartGame)){
                        Card drawedCard;
                        drawedCard = game.generateRandomCard();
                        curPlayer.getPlayerCards().addCard(drawedCard);
                        System.out.println("Kartu kamu bertambah 1!");
                        System.out.println("giliran kamu diakhiri !");
                        curPlayer.setIsNotPlaying();
                        curPlayer = listPemain.next();
                        curPlayer.setIsPlaying();
                        System.out.println("Sekarang yang main adalah " + curPlayer.getNamePlayer());
                        System.out.println("Masukkan perintah menu yang ingin kamu lakukan! kalau lupa ketik (command)");
                        System.out.println(" ");
                        command = input.next();
                    }
    
                    else if (command.toLowerCase().equals("hiji") && (isStartGame)){
                        //declare hiji
                        Declare declare = new Declare(curPlayer);
                        declare.threading();
                        System.out.println("giliran kamu diakhiri !");
                        curPlayer.setIsNotPlaying();
                        curPlayer = listPemain.next();
                        curPlayer.setIsPlaying();
                        System.out.println("Sekarang yang main adalah " + curPlayer.getNamePlayer());
                        System.out.println("Masukkan perintah menu yang ingin kamu lakukan! kalau lupa ketik (command)");
                        System.out.println(" ");
                        command = input.next();
                    }

                    else if (command.toLowerCase().equals("players") && (isStartGame)){
                        game.showPlayers(listPemain);
                        System.out.println("Masukkan perintah menu yang ingin kamu lakukan! kalau lupa ketik (command)");
                        System.out.println(" ");
                        command = input.next();
                    }
                    
                    else if (command.toLowerCase().equals("turn") && (isStartGame)){
                        game.viewPlayerInTurn(listPemain, listPemain.getArah());
                        System.out.println("Masukkan perintah menu yang ingin kamu lakukan! kalau lupa ketik (command)");
                        System.out.println(" ");
                        command = input.next();
                    }

                    else if (command.toLowerCase().equals("help")){
                        Game.help();
                        System.out.println("Masukkan perintah menu yang ingin kamu lakukan! kalau lupa ketik (command)");
                        System.out.println(" ");
                        command = input.next();
                    }
                    
                    else if (command.toLowerCase().equals("command")){
                        game.showCommmands2();
                        System.out.println("Masukkan perintah menu yang ingin kamu lakukan! kalau lupa ketik (command)");
                        System.out.println(" ");
                        command = input.next();
                    }
                    
                    else if (command.toLowerCase().equals("end")){
                        System.out.println("Yakin nih mau udahan?? (y/n)");
                        String in = input.next();
                        if (in.toLowerCase().equals("y")){
                            isStartGame = false;
                            break loop3;
                        } else if (in.toLowerCase().equals("n")){
                            System.out.println("Daebakk!! Ayo lanjutin permainan");
                            System.out.println("Masukkan perintah menu yang ingin kamu lakukan! kalau lupa ketik (command)");
                            System.out.println(" ");
                            command = input.next();
                        } else{
                            System.out.println("Yakin nih mau udahan?? (y/n)");
                            in = input.next();
                        }
                    }

                    else{
                        System.out.println("Kamu salah memasukkan perintah cuk!!!");
                        System.out.println("Masukkan perintah menu yang ingin kamu lakukan! kalau lupa ketik (command)");
                        System.out.println(" ");
                        command = input.next();
                    }
                }
                if (haveWinner) {
                    System.out.println();
                    System.out.println("Selamat " + winner.getNamePlayer() + " kamu telah memenangkan game HIJI ini!");
                    System.out.println("--------------------------------------------------------------------------------");
                    winner = null;
                }
                System.out.println("Permainan sudah berakhir!!");
                System.out.println("1. START");
                System.out.println("2. HELP");
                System.out.println("3. EXIT");
                command = input.next();
            }
            
            else{
                System.out.println("MASUKKAN SALAH!!! SILAKAN PILIH DARI 3 MENU BERIKUT!");
                System.out.println("1. START");
                System.out.println("2. HELP");
                System.out.println("3. EXIT");
                System.out.println(" ");
                command = input.next();
            }

        }
        System.out.println("Terimakasih sudah bermain HIJI bersama OOPpah Koriya!");
        
    }
    
}
    

