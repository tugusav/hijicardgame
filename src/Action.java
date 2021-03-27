public class Action extends Card {
    String action;
    // action consist of skip, reverse, and draw2 card

    public Action(Color color, String action) {
        super(color.getColor(), action);
        this.action = action;
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
            if (color == c.getColor() && action == c.getAction()) {
                return true;
            }
        }
        return false;
    }
    @Override
    public String getType() {
        // TODO Auto-generated method stub
        return this.action + " " + this.color;
    }
}
