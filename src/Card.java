//import java.util.Arrays;

public abstract class Card {
    final String color; //ini kalo di-private gabisa diakses sama subclass nya
    final String type; //ini kalo di-private gabisa diakses sama subclass nya

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
