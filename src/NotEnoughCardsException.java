public class NotEnoughCardsException extends Exception {
    // Digunakan untuk class Carrd Deck, untuk kartu jika kurang saat mau dibagikan
    public NotEnoughCardsException(){
        super("Kartu kurang!");
    }
    public String getErrorMessage(){
        return NotEnoughCardsException.super.getMessage();
    }
    
}

// public class InvalidColorException extends Exception{
//     //Digunakan di class Color, buat object Color yang warnanya udah ditentukan
//     // "RED", "GREEN", "BLUE", "YELLOW", "WILD"
//     public InvalidColorException(String warna){
//         super("Warna " + warna + " tidak valid, set ulang!");
//     }
//     public String getErrorMessage(){
//         return InvalidColorException.super.getMessage();
//     }
// }