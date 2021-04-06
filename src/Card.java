public abstract class Card {
    private final String color;
    private final String type;
    public static Color red = new Color("RED");
    public static Color green = new Color("GREEN");
    public static Color blue = new Color("BLUE");
    public static Color yellow = new Color("YELLOW");
    public static Color wild = new Color("WILD");

    public Card(String color, String type){
        this.color = color;
        this.type = type;
    }

    public String getColor(){
        return color;
    }

    public String getCardType(){
        return this.type;
    }
    public abstract String getType();
    public abstract boolean equals(Object o);

}
