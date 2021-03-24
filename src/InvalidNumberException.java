package Hiji;

public class InvalidNumberException extends Exception{
    //Digunakan di class Angka, semisal buat kartu angka di luar range akan muncul pesan error
    // Antara 0 - 9
    public InvalidNumberException(int number){
        super(number + " tidak masuk range, range antara 0 - 9! " );
    }
    public String getErrorMessage(){
        return InvalidNumberException.super.getMessage();
    }
}
