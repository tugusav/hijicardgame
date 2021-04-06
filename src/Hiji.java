import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;
public class Hiji {
    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        CardDeck deck = new CardDeck();
        boolean isHiji = false; //  menentukan game masih berjalan atau tidak
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
        System.out.println("-------WELCOME TO HIJI GAMES-------");
        System.out.println("1. HELP");
        System.out.println("2. START");
        System.out.println("3. EXIT");

        String commando = input.next();

        while (!commando.toLowerCase().equals("exit")){
            if (commando.toLowerCase().equals("help"))
            {
                Game.help();
                commando = input.next();
            }
            else if (commando.toLowerCase().equals("start"))
            {   
                // Memasukkan jumlah pemain
                deck.shuffleCard(); // mengocok dek kartu
                System.out.println("Masukkan jumlah pemain :");
                int numPlayers = input.nextInt();
                while (numPlayers>6 || numPlayers<2) {
                    System.out.println("Jumlah pemain tidak valid, jumlah pemain harus diantara 2-6");
                    numPlayers = input.nextInt();
                }
                // membuat pemain & membagikan kartu
                players = GameBuilder.generatePlayers(numPlayers, deck, input);
                listPemain.setListGiliran(players);
                System.out.println(players.size());
                System.out.println("Dealer sedang membagikan kartu...");
                Thread.sleep(300);
                for(Player player: players){
                    System.out.println(player.getNamePlayer());
                }
                
                
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
                Thread.sleep(1000);
                System.out.println("The first card is: " + curCard.getType() + "!");
                currentColor = new Color(curCard.getColor());

                game.showCommmands();

                int command = input.nextInt();

                while(command != 8){
                    game.clearScreen();
                    switch (command) {
                        case 1:
                        // list cards
                            System.out.println("Kartu yang " +curPlayer.getNamePlayer() + " miliki saat ini: ");
                            curPlayer.getPlayerCards().showListCards();
                            System.out.println("Masukkan command yang ingin kamu lakukan!");
                            game.showCommmands();
                            command = input.nextInt();
                            break;
                        case 2:
                        // discard cards
                            boolean isMultipleDiscard = false;
                            boolean timeForPower = false;
                            int numDiscarded = 0;
                            String option = "";
                            boolean gakJadi = false;
                            System.out.println("Current Card: " + curCard.getType());
                            System.out.println("Current Color: " + currentColor.getColor());
                            loop:
                            while(!isMultipleDiscard){  
                                System.out.println("Masukkan kartu yang ingin kamu mainkan: ");
                                curPlayer.getPlayerCards().showListCards(); //ngeprint list kartu
                                int pilihan = input.nextInt();
                                while (pilihan > curPlayer.getTotalPlayerCards() || pilihan < 1){
                                    System.out.println("Range pilihan tidak valid! Masukkan pilihan lagi: ");
                                    pilihan = input.nextInt();
                                }
                                discardedCard = curPlayer.getPlayerCards().getCard(pilihan-1);
                                System.out.println("Kartu sekarang: " + curCard.getType());
                                System.out.println("Kartu yang dipilih: " + discardedCard.getType());
                                
                                if(!game.isCardValidNonMultipleDiscard(curCard, discardedCard, currentColor)){
                                    System.out.println("Kartu yang kamu pilih tidak valid!");
                                } else {
                                    numDiscarded += 1;
                                    curCard = discardedCard; // ngeliat paling atas di discarded pile
                                    curPlayer.getPlayerCards().discardByIndex(pilihan-1);
                                    System.out.println("Kartu berhasil di discard!");
                                    currentColor = new Color(discardedCard.getColor());
                                }
                                System.out.println("Apakah kamu ingin mengeluarkan kartu lagi? (y/n): ");
                                input.nextLine();
                                option = input.next(); // y/n
                                if(numDiscarded == 0){
                                    //belum ambil kartu
                                    isMultipleDiscard = false;
                                    gakJadi = true;
                                    break loop;
                                } else {
                                    if(option.equals("y")){
                                        isMultipleDiscard = true;
                                    } else {
                                        timeForPower = true;
                                        break loop;
                                    }
                                }
                            }
                            loop2:
                            while(isMultipleDiscard){
                                // System.out.println("hayoloh masuk multiple");
                                System.out.println("Masukkan kartu yang ingin kamu mainkan: ");
                                curPlayer.getPlayerCards().showListCards(); //ngeprint list kartu
                                int pilihan = input.nextInt();
                                while (pilihan > curPlayer.getTotalPlayerCards() || pilihan < 1){
                                    System.out.println("Range pilihan tidak valid! Masukkan pilihan lagi: ");
                                    pilihan = input.nextInt();
                                }
                                // ArrayListGenerics<Card> cardsToDiscard = new ArrayListGenerics<>();
                                discardedCard = curPlayer.getPlayerCards().getCard(pilihan-1);
                                System.out.println(discardedCard.getType());
                                System.out.println(curCard.getType());
                                if(!game.isCardValid(curCard, discardedCard, currentColor)){
                                    System.out.println("Kartu yang kamu pilih tidak valid!");
                                } else {
                                    numDiscarded += 1;
                                    curCard = discardedCard; // ngeliat paling atas di discarded pile
                                    curPlayer.getPlayerCards().discardByIndex(pilihan-1);
                                    System.out.println("Kartu berhasil di discard!");
                                    currentColor = new Color(discardedCard.getColor());
                                }
                                System.out.println("Apakah kamu ingin mengeluarkan kartu lagi? (y/n): ");
                                input.nextLine();
                                option = input.nextLine(); // y/n
                                if(option.equals("n")){
                                    isMultipleDiscard = false;
                                    timeForPower = true;
                                    break loop2;
                                } else {
                                    isMultipleDiscard = true;
                                }
                            }
                            if(numDiscarded < 1 && !isMultipleDiscard){
                                Card drawedCard;
                                drawedCard = game.generateRandomCard();
                                curPlayer.getPlayerCards().addCard(drawedCard);
                                System.out.println("Kartu kamu bertambah 1!");
                                System.out.println("Giliran kamu diakhiri !");
                                curPlayer.setIsNotPlaying();
                                curPlayer = listPemain.next();
                                curPlayer.setIsPlaying();
                                System.out.println("Sekarang yang main adalah " + curPlayer.getNamePlayer());
                                System.out.println("Masukkan command yang ingin kamu lakukan!");
                                command = input.nextInt();
                                break;
                            } else {
                                while(timeForPower){
                                    // System.out.println("Masuk ke bagian sini");
                                    game.clearScreen();
                                    String[] warna = {"RED", "GREEN", "BLUE", "YELLOW"};
                                    curPlayer.setIsNotPlaying();
                                    System.out.println(curCard.getCardType());
                                    if(curCard instanceof Action){
                                        Action c = (Action) curCard;
                                        if(c.getAction().equals("DRAW 2")){
                                            curPlayer = listPemain.next();
                                            for(int i = 0; i < numDiscarded; i++){
                                                game.drawTwo(curPlayer);
                                            }
                                            curPlayer = listPemain.next();
                                        } else if(c.getAction().equals("SKIP")){
                                            for(int i = 0; i < numDiscarded; i++){
                                                curPlayer = listPemain.skip();
                                            }    
                                        } else if(c.getAction().equals("REVERSE")){
                                            for(int i = 0; i < numDiscarded; i++){
                                                listPemain.reverseArah();
                                            }   
                                            curPlayer = listPemain.next();
                                        }
                                    } else if (curCard instanceof Special){
                                        Special c = (Special) curCard;
                                        if (c.getSpecial().equals("WILDCOLOR")){
                                            System.out.println("Choose color: ");
                                            for(int i=0; i < warna.length; i++){
                                                System.out.println((i+1) + ". "+ warna[i]);
                                            }
                                            int pilihan = input.nextInt();
                                            while(pilihan < 0 || pilihan > warna.length){
                                                System.out.println("Warna tidak valid, silakan masukkan warna lagi!: ");
                                                pilihan = input.nextInt();
                                            }
                                            currentColor = new Color(warna[pilihan-1]);
                                            curPlayer = listPemain.next();
                                        } else if (c.getSpecial().equals("DRAW 4")){
                                            System.out.println("Choose color: ");
                                            for(int i=0; i < warna.length; i++){
                                                System.out.println((i+1) + ". "+ warna[i]);
                                            }
                                            int pilihan = input.nextInt();
                                            while(pilihan < 0 || pilihan > warna.length){
                                                System.out.println("Warna tidak valid, silakan masukkan warna lagi!: ");
                                                pilihan = input.nextInt();
                                            }
                                            currentColor = new Color(warna[pilihan-1]);
                                            curPlayer = listPemain.next();
                                            for(int i = 0; i < numDiscarded; i++){
                                                game.drawFour(curPlayer);
                                            }
                                        }
                                    }else {
                                        curPlayer = listPemain.next();
                                    }
                                    break;

                            }
                            
                            }
                            // saatnya ganti pemain
                            curPlayer.setIsPlaying();
                            System.out.println("Sekarang giliran " + curPlayer.getNamePlayer());
                            System.out.println("Masukkan command yang ingin kamu lakukan!");
                            game.showCommmands();
                            command = input.nextInt();
                            break;
                        case 3:
                            Card drawedCard;
                            drawedCard = game.generateRandomCard();
                            curPlayer.getPlayerCards().addCard(drawedCard);
                            System.out.println("Karena gagal mengeluarkan kartu, Kartu kamu bertambah 1!");
                            System.out.println("Sekarang, giliran kamu diakhiri!");
                            curPlayer.setIsNotPlaying();
                            curPlayer = listPemain.next();
                            curPlayer.setIsPlaying();
                            System.out.println("Sekarang yang main adalah " + curPlayer.getNamePlayer());
                            System.out.println("Masukkan command yang ingin kamu lakukan!");
                            game.showCommmands();
                            command = input.nextInt();
                            break;
                        default:
                            break;
                        }

                    }
    
                }
            }
        }
}