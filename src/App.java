import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        CardDeck deck = new CardDeck();

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
                GameBuilder();

                System.out.println("1. List Cards");
                System.out.println("2. Discard");
                System.out.println("3. Draw");
                System.out.println("4. Declare HIJI");
                System.out.println("5. List Players");
                System.out.println("6. View Player in Turn");
                System.out.println("7. Help");
                System.out.println("8. EXIT");

                Scanner input = new Scanner(System.in);
                String command = input.next();

                Switch(command){
                    case 1:
                        listplayer();

                    case 2:
                        Game.discard();
                    case 3:
                        Game.drawCards()
                    case 4:
                        Game.declareHiji();
                    case 5:
                        Game.getPlayers();
                    case 6:
                        Game.curPlayer;
                    case 7:
                        Game.help();
                    case 8:
                        break;


                }
            }
            System.out.println("Terimakasih sudah bermain HIJI bersama OOPah Koriya!");
            break;
        }*/

        
    //     Color red = new Color("GREEN");
    //     Card card1 = new Angka(3, red);
    //     Card card2 = new Angka(4, red);
    //     List<Card> playerCards = new ArrayList<>();
    //     playerCards.add(card1);
    //     playerCards.add(card2);
    //     PlayerCards holyCard = new PlayerCards(playerCards);
    //     Player player1 = new Player("Agung", holyCard);

    //     System.out.println(player1.getNamePlayer());
    //     System.out.println(player1.getTotalPlayerCards());
    //     System.out.println(player1.isPlaying());
    //     player1.getPlayerCards().drawCard(card2);
    //     player1.getPlayerCards().showListCards();
        
    //     Card topCard = deck.peekTopCard();
    //     System.out.println(topCard.getType() + " " + deck.getTotalDeck());
    //     deck.shuffleCard();
    //     topCard = deck.peekTopCard();
    //     System.out.println(topCard.getType() + " " + deck.getTotalDeck());
    //     List<Card> shuffledCard = deck.assignCardsToPlayer();
    //     player1.getPlayerCards().addPlayerCards(shuffledCard);
    //     player1.getPlayerCards().showListCards();
    //     System.out.println(deck.getTotalDeck());



        // System.out.println("-------WELCOME TO HIJI GAMES-------");
        // System.out.println("1. HELP");
        // System.out.println("2. START");
        // System.out.println("3. EXIT");

        // Scanner input = new Scanner(System.in);
        // String commando = input.next(); 

        // while (!commando.equals("EXIT")){
        //     if (commando.equals("HELP"))
        //     {
        //         help();
        //     }
        //     else if (commando.equals("START"))
        //     {
        //         GameBuilder();

        //         System.out.println("1. List Cards");
        //         System.out.println("2. Discard");
        //         System.out.println("3. Draw");
        //         System.out.println("4. Declare HIJI");
        //         System.out.println("5. List Players");
        //         System.out.println("6. View Player in Turn");
        //         System.out.println("7. Help");
        //         System.out.println("8. EXIT");

        //         Scanner input = new Scanner(System.in);
        //         String command = input.next();

        //         Switch(command){
        //             case 1:
        //                 listplayer();

        //             case 2:
        //                 Game.discard();
        //             case 3:
        //                 Game.drawCards()
        //             case 4:
        //                 Game.declareHiji();
        //             case 5:
        //                 Game.getPlayers();
        //             case 6:
        //                 Game.curPlayer;
        //             case 7:
        //                 Game.help();
        //             case 8:
        //                 break;


        //         }
        //     }
        //     System.out.println("Terimakasih sudah bermain HIJI bersama OOPah Koriya!");
        //     break;
        // }*/




    // }
}
