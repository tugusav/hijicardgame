public class NotEnoughCardsException extends Exception {
    // Digunakan untuk class Carrd Deck, untuk kartu jika kurang saat mau dibagikan
    public NotEnoughCardsException(){
        super("Kartu kurang!");
    }
    public String getErrorMessage(){
        return NotEnoughCardsException.super.getMessage();
    }
    
}

