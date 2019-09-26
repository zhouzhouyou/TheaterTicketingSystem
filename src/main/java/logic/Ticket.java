package logic;

import java.util.Vector;

public class Ticket {
    private String name;
    private int totalAmount;
    private Vector<Integer> tickets;

    public Ticket(String name, int totalAmount) {
        this.name = name;
        this.totalAmount = totalAmount;
        tickets = new Vector<Integer>();
        for (int i = 1; i <= totalAmount; i++) tickets.add(i);
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return tickets.size();
    }

    public synchronized int getTicket() {
        if (isEmpty()) return 0;
        int random = (int)(Math.random()*(tickets.size()));
        int out = tickets.get(random);
        tickets.remove(random);
        return out;
    }

    public synchronized String sellTicket() {
        final int ticketNumber = getTicket();
        if (ticketNumber == 0) return null;
        return " sold a " + name + " ticket: " + ticketNumber;
    }

    public boolean isEmpty() {
        return tickets.isEmpty();
    }
}
