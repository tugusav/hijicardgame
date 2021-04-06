public class Color {
    //Dibuat untuk membuat color tiap kartu
    private String color = "NULL";
    private boolean trueColor;

    public Color(String color) {
        if (color.equals("RED") || color.equals("GREEN")
                || color.equals("BLUE") || color.equals("YELLOW")
                || color.equals("WILD")){
            this.color = color;
            trueColor = true;
        }
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean getColorCard(){
        return trueColor;
    }

    public String getColor() {
        return color;
    }
}
