//package Hiji;

public class Color {
    //Dibuat untuk membuat color tiap kartu
    private String color = "NULL";

    public Color(String color) {
        if (color.equals("RED") || color.equals("GREEN")
                || color.equals("BLUE") || color.equals("YELLOW")){
            this.color = color;
        }else{
            try{
                throw new InvalidColorException(color);
            } catch(InvalidColorException e){
                System.err.println(e.getErrorMessage());
            }
        }
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
