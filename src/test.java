public class test {
    
    public static void main(String[] args) {
        Card randomCard;
        Game game = new Game();
        randomCard = game.generateFirstCard();
        System.out.println(randomCard.getType()); 
        Color green = new Color("GREEN");
        Color biru = new Color("BLUE");
        Card card1 = new Angka(3, green);
        Card card2 = new Angka(4, green);
        Card card3 = new Action(green, "SKIP");
        Card card4 = new Action(biru, "SKIP");


        if (card4.equals(card3)){
            System.out.println("ya");
        } else {
            System.out.println("ga");
        }
    }
    


}
