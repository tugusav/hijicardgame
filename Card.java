//import java.util.Arrays;

public abstract class Card {
    private final String[] color = new String[] {"RED", "BLUE", "GREEN", "YELLOW"};
    private final String[] type = new String[] {"NUMBER", "SKIP", "REVERSE", "DRAW_TWO", "WILD_COLOR", "WILD_FOUR"};

    public Card(){}

    public String[] getColor(){
        return color;
        // Kalo mau print list color -> System.out.println(Arrays.toString(color));
        // Kalo mau akses elemen list -> color[0], color[1] dst;
    }
    public String[] getType(){
        return type;
    }
    public abstract boolean equals(Object o);
}
