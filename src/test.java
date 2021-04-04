import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class test {
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        CardDeck deck = new CardDeck();
        boolean isHiji = false; //  menentukan game masih berjalan atau tidak
        Stack<Card> discardPile = new Stack<>(); // untuk dapat melihat top of stack
        Card curCard; // kartu yang ada di discardPile paling atas
        ArrayListGenerics<Player> players = new ArrayListGenerics<>(); // list of players
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
                Game.help();
            }
            else if (commando.equals("START"))
            {   
                // Memasukkan jumlah pemain
                System.out.println("Masukkan jumlah pemain :");
                int numPlayers = input.nextInt();
                while (numPlayers>6 || numPlayers<2) {
                    System.out.println("Jumlah pemain tidak valid, jumlah pemain harus diantara 2-6");
                    numPlayers = input.nextInt();
                }
                // membuat pemain & membagikan kartu
                players = GameBuilder.generatePlayers(numPlayers, deck);
                System.out.println("Dealer membagi kartu...");
                // Thread.sleep(3000);
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
                            option = input.nextLine(); // y/n
                            }while(!option.equals("n"));
                        
                        
                        
                        

                        // Game.discard();
                    case 3:
                        Card drawedCard;
                        drawedCard = game.generateRandomCard();
                        curPlayer.getPlayerCards().addCard(drawedCard);
                    // case 4:
                    //     Game.declareHiji();
                    // case 5:
                    //     Game.getPlayers();
                    // case 6:
                    //     Game.curPlayer;
                    // case 7:
                    //     Game.help();
                    default:
                        break;


                }
            }
            // System.out.println("Terimakasih sudah bermain HIJI bersama OOPah Koriya!");
            // break;
        }
    //     Card randomCard;
    //     Game game = new Game();
    //     randomCard = game.generateFirstCard();
    //     System.out.println(randomCard.getType()); 
    //     Color green = new Color("GREEN");
    //     Color biru = new Color("BLUE");
    //     Card card1 = new Angka(3, green);
    //     Card card2 = new Angka(4, green);
    //     Card card3 = new Action(green, "SKIP");
    //     Card card4 = new Action(biru, "SKIP");


    //     if (card4.equals(card3)){
    //         System.out.println("ya");
    //     } else {
    //         System.out.println("ga");
    //     }
    // }
    
    }
}    

