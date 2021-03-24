//import java.util.Arrays;

public abstract class Card {
    private final String color;
    private final String type;

    public Card(String color, String type){
        this.color = color;
        this.type = type;
    }

    public String getColor(){
        return color;
    }
    public String getType(){
        return type;
    }
    public abstract boolean equals(Object o);

}
