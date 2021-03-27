public class Angka extends Card{
    private int number;

    public Angka(int number, Color color) {
        super(color.getColor(), "ANGKA");
            if ((number == 1 || number == 2 || number == 3
                    || number == 4 || number == 5 || number == 6
                    || number == 7 || number == 8 || number == 9
                    || number == 0)) {
                this.number = number;
            } else {
                try {
                    throw new InvalidNumberException(number);
                } catch (InvalidNumberException e) {
                    System.err.println(e.getErrorMessage());
                }
            }
    }
    public int getNumber() {
        return number;
    }

    public boolean equals(Object o){
        return this == o;
    }
    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return this.number + " " + this.color;
    }
}
