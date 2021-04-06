
public class InvalidNumberException extends InvalidCardException{
    //Digunakan di class Angka, semisal buat kartu angka di luar range akan muncul pesan error
    // Antara 0 - 9
    int number;
    public InvalidNumberException(Color color,int number){
        super(color);
        this.number = number;
    }
    public String getErrorMessage(){
        return "Angka " + number + " tidak valid!!!";
    }
}
