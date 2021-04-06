public class Special extends Card {
    private String special;
    //special consist of wildcard and draw4 card
    public Special(Color wild, String special) {
        // special = wildcard / draw 4
        // type = special
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
        return this.getSpecial();
    }
}
