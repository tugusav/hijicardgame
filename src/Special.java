public class Special extends Card {
    private String special;
    //special consist of wildcard and draw4 card

    //ini masih salah sih constructor special nya
    public Special(Color wild, String special) {
        // special = wildcard / draw 4
        // type = special
        /* ini bingung nge supernya gmn soalnya kan special itu colornya semua jenis
        trs kalo special ini di-getColor in jadi punya warna dong(?)
        */
        super(wild.getColor(), "SPECIAL");
        this.special = special; 

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
            if (this.getSpecial() == c.getSpecial()) {
                return true;
            }
        }
        return false;
    }
    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return this.getSpecial();
    }
}