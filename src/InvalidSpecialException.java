public class InvalidSpecialException extends InvalidCardException{
    String special;
    public InvalidSpecialException(Color color,String special){
        super(color);
        this.special = special;
    }
    public String getErrorMessage(){
        return "Action  " + special + " tidak valid!!!";
    }
}