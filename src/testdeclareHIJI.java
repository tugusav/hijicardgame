import java.util.Scanner;

public class testdeclareHIJI {
    static void threadMessage(String message){
        String threadName = Thread.currentThread().getName();
        System.out.format("%s: %s%n", threadName,message);
    }

    private static class DeclaringHIJI implements Runnable{
        public void run(){
            // ArrayListGenerics<Player> players = new ArrayListGenerics<>(); // list of players
            // Player curPlayer;
            // Game game = new Game()
            Scanner input = new Scanner(System.in);
            String hiji = input.nextLine();
            if (!Thread.interrupted()) {
                if (hiji.equals("HIJI")) {
                    threadMessage("anjayyy gakena jebakan batman");
                } else if (exit == true){
                    threadMessage("mampus ambil 2 kartu hahahah"); 
                }
            } else {
                threadMessage("MAMPUS ANJGG GAJADI MENANG LO");
            }
            input.close();

            // try{
            //     Thread.sleep(3000);
            // } catch (InterruptedException e){
            //     threadMessage("Lupa ngomong HIJI gwe!");
            // }

        }

    }
    static boolean exit = false;
    public static void main(String[] args) throws InterruptedException{
        long wait = 3000;
        // threadMessage("Start to Declaring HIJI!");
        long start = System.currentTimeMillis();
        Thread t = new Thread(new DeclaringHIJI());
        t.start();

        // Scanner input = new Scanner(System.in);
        // String command = input.nextLine();
        // if (command.equals("discard")) {
        //     threadMessage("Wait for player to Declare HIJI!");
        if(t.isAlive()){
            // threadMessage("still wait player to Declare HIJI!");
            t.join(3000);
            if(((System.currentTimeMillis() - start) > wait) && t.isAlive()){
                threadMessage("Udah lebih dari 3 detik cuk!");
                exit = true;
            //draw card for penalty karena tidak declare HIJI
            }  
        }
    }
}