package logic;


public class Window implements Runnable{
    private String name;
    private Theater theater = Theater.getInstance();

    public Window(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void run() {
        try {
            do {
                Thread.sleep((long) (Math.random() * 200));
            } while (theater.sellTicket(this));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Window #" + name;
    }
}
