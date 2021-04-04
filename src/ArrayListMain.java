public class ArrayListMain {
    public static void main(String[] args) {
        ArrayListGenerics<Card> cardList = new ArrayListGenerics<>();
        if(cardList.isEmpty()){
            System.out.println("kosong");
        }
        System.out.println(cardList.size());
        Color blue = new Color("BLUE");
        Card first = new Angka(3, blue);
        Card second = new Angka(4, blue);
        Card third = new Angka(5, blue);
        cardList.add(first);
        cardList.add(second);
        cardList.add(third);
        System.out.println(cardList.size());
        Card gotCard = cardList.get(cardList.size()-1); // harusnya adalah 5 blue
        Card gotCard2 = cardList.get(cardList.size()-2); // harusnya adalah 4 blue
        System.out.println(gotCard.getType()); 
        System.out.println(gotCard2.getType()); 
        cardList.removeLast();
        Card gotCard3 = cardList.get(cardList.size());
        System.out.println(gotCard3.getType()); 
        
    }
}
