import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class testAgung {  
    public static void main(String[] args) throws InterruptedException {
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
        Color currentColor;
        boolean isStartGame = false;
        System.out.println("-------WELCOME TO HIJI GAMES-------");
        System.out.println("1. START");
        System.out.println("2. Cards");
        System.out.println("3. Discard");
        System.out.println("4. Draw");
        System.out.println("5. HIJI");
        System.out.println("6. Players");
        System.out.println("7. Turn");
        System.out.println("8. Help");
        System.out.println("9. EXIT");

        String command = input.next();

        while (!command.toLowerCase().equals("exit")){
            if (command.toLowerCase().equals("help")){
                Game.help();
                command = input.next();
            }
            else if (command.toLowerCase().equals("start") && (!isStartGame))
            {   
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
                System.out.println("Pemain pertama yang akan bermain adalah " + curPlayer.getNamePlayer());
                curCard = game.generateFirstCard();
                System.out.println("Anda sedang bermain sebagai " + curPlayer.getNamePlayer());
                System.out.println("Generating random card...");
                Thread.sleep(2000);
                System.out.println("The first card is :" + curCard.getType());
                currentColor = new Color(curCard.getColor());
                isStartGame = true;
                System.out.println("Masukkan perintah menu yang ingin kamu lakukan!");
                command = input.next();
                
                while (!command.toLowerCase().equals("end game")){

                    if (command.toLowerCase().equals("cards") && (isStartGame)){
                        curPlayer.getPlayerCards().showListCards();
                        //command = input.next();
                    }
                    
                    else if (command.toLowerCase().equals("discard") && (isStartGame)){
                        //game.discard(curCard, curPlayer, input, listPemain, currentColor);
                        System.out.println("fungsinya belom ada hehehe");
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


                        //command = input.next();
                    }
    
                    else if (command.toLowerCase().equals("hiji") && (isStartGame)){
                        //declare hiji
                        Declare declare = new Declare(curPlayer);
                        declare.threading();
                    }

                    else if (command.toLowerCase().equals("players") && (isStartGame)){
                        for(int i = 0; i < players.size(); i++){
                            System.out.printf("Pemain %d : %s", i+1, players.get(i).getNamePlayer());
                            System.out.println("Jumlah Kartu: " + players.get(i).getTotalPlayerCards());
                            if(players.get(i).isPlaying()){
                                System.out.println("Sedang giliran");
                                System.out.println(" ");
                            } else {
                                System.out.println("Tidak sedang giliran");
                                System.out.println(" ");
                            }
                        }
                        //command = input.next();
                    }
                    
                    else if (command.toLowerCase().equals("turn") && (isStartGame)){
                        for(int i = 0; i < players.size(); i++){
                            if (players.get(i).isPlaying()) {
                                System.out.println("Player dalam giliran: " + players.get(i).getNamePlayer());
                                if (listPemain.getArah() == Arah.SEARAH_JARUM_JAM) {
                                    System.out.println("Player selanjutnya: " + players.get((i+1) % players.size()).getNamePlayer());
                                } else {
                                    System.out.println("Player selanjutnya: " + players.get((i-1 + players.size()) % players.size()).getNamePlayer());
                                }
                            } else {
                
                            }
                        }
                        // System.out.println("Pemain yang sedang bermain adalah: "+ " " + curPlayer.getNamePlayer());
                        // System.out.println("Pemain yang akan bermain selanjutnya: "+ " " + listPemain.next().getNamePlayer());
                        
                        //command = input.next();
                    }

                    else if (command.toLowerCase().equals("help")){
                        Game.help();
                        //command = input.next();
                    }
                    command = input.next();

                    

                }
                System.out.println("kamu keluar permainan");
                
            }
        
    
        
        }
        System.out.println("Terimakasih sudah bermain HIJI bersama OOPah Koriya!");
        
 
    }
    
}
    

