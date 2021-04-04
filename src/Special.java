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
        if(special.equals("WILDCOLOR") || special.equals("DRAW 4")) {
            this.special = special;
        }else{
            try {
                if (!wild.getColorCard()) {throw new InvalidCardException(wild);}
                else{throw new InvalidSpecialException(wild, special);}
            } catch(InvalidSpecialException e){
                System.err.println(e.getErrorMessage());
            } catch(InvalidCardException e){
                e.printStackTrace();
            }
        }

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
            if (this.getSpecial().equals(c.getSpecial())) {
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
