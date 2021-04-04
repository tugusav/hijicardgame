public class Action extends Card {
    private String action;
    // action consist of skip, reverse, and draw2 card

    public Action(Color color, String action) {
        super(color.getColor(), "ACTION");
        if(action.equals("SKIP") || action.equals("REVERSE")
                ||action.equals("DRAW 2") ) {
            this.action = action;
        }else{
            try {
                if (!color.getColorCard()) {throw new InvalidCardException(color);}
                else{throw new InvalidActionException(color, action);}
            } catch(InvalidActionException e){
                System.err.println(e.getErrorMessage());
            } catch(InvalidCardException e){
                e.printStackTrace();
            }
        }
    }

    /* masih blm ngerti jadi di-comment dulu
    public void skip() {
        next();
        next();
    }
    
    
    public void reverse() {
        reverseArah();
        next();
    }

    public void draw2() {
        next();
        for (int i = 0; i < 2; i++) {
            //player add 2 from deck
        }
    }
    */

    public String getAction() {
        return action;
    }

    public boolean equals(Object o) {
        if (o instanceof Action) {
            Action c = (Action) o;
            if (this.getColor() == c.getColor() || this.getAction() == c.getAction()) {
                return true;
            }
        }
        return false;
    }
    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return this.getAction() + " " + this.getColor();
    }
}
