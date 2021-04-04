public class InvalidActionException extends InvalidCardException{

    String action;
    public InvalidActionException(Color color,String action){
        super(color);
        this.action = action;
    }
    public String getErrorMessage(){
        return "Action  " + action + " tidak valid!!!";
    }
}