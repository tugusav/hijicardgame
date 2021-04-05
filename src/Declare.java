// package Hiji;


import java.util.Scanner;

public class Declare {
    private boolean udahDraw = false;
    static Scanner sc = new Scanner(System.in);
    private final Player curPlayer;
    private final Game game;

    public Declare(Player curPlayer){
        this.curPlayer = curPlayer;
        game = new Game();
    }

    public class DeclaringHIJI implements Runnable{
        public void run(){
            String hiji = sc.next();
            if (!Thread.interrupted() && !udahDraw) {
                if (hiji.equals("HIJI")) {
                    System.out.println("anjayyy gakena jebakan batman, NEXT!");
                } else {
                    game.drawTwo(curPlayer);
                    System.out.println("draw 2 kartu, salah declare!");
                }

            } else {
                System.out.println("Next Player!!, " + curPlayer.getNamePlayer() + " Telat ngomong!");
            }
            sc.close();
        }
    }
    public void threading() throws InterruptedException{
        if(game.isLeft1Card(curPlayer)) {
            long wait = 3000;
            long start = System.currentTimeMillis();
            Thread t = new Thread(new Declare.DeclaringHIJI());
            System.out.println("Ketik HIJI kak!");
            t.start();

            while (t.isAlive()) {
                t.join(3000);
                if (((System.currentTimeMillis() - start) > wait) && t.isAlive()) {
                    System.out.println("draw 2 kartu, udah lebih dari 3 detik!!!");
                    game.drawTwo(curPlayer);
                    udahDraw = true;
                    t.interrupt();
                    t.join();
                }
            }
        }else{
            game.drawTwo(curPlayer);
            System.out.println("Kartu belum 1 ngapain declare HIJI pak!, dapet hadiah 2 kartu");
        }
    }
}
