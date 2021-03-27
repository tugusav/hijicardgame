public class Special extends Card {
    String special;
    //special consist of wildcard and draw4 card

    //ini masih salah sih constructor special nya
    public Special(Color color, String special) {
        /* ini bingung nge supernya gmn soalnya kan special itu colornya semua jenis
        trs kalo special ini di-getColor in jadi punya warna dong(?)
        */
        super(color.getColor(), special);

    }
    /* masih blm ngerti jadi di-comment dulu
    public void draw4() {
        next();
        for (int i = 0; i < 4; i++) {
            //player add 4 from deck
        }
    }
    
    public void wildcard() {
        next();
        nge-random color(?)
    }
    */

    public String getSpecial() {
        return special;
    }

    public boolean equals(Object o) {
        if (o instanceof Special) {
            Special c = (Special) o;
            if (special == c.getSpecial()) {
                return true;
            }
        }
        return false;
    }
    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return this.type + " " + this.special;
    }
}