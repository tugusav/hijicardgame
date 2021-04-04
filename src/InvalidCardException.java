public class InvalidCardException extends Exception{
    public InvalidCardException(Color color){
        super("Bukan kartu HIJI!!!");
    }
    public String getErrorMessage(){
        return InvalidCardException.super.getMessage();
    }
}