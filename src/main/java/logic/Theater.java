package logic;

import controller.MainController;
import javafx.application.Platform;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;


public class Theater {
    private static Theater ourInstance = new Theater();
    private ReentrantLock lock = new ReentrantLock();
    public static Theater getInstance() {
        return ourInstance;
    }
    private List<Ticket> ticketList;
    private List<Window> windowList;
    private MainController controller;
    private ExecutorService service;


    private Theater() {
    }

    public void initialTickets(List<Ticket> list) {
        ticketList = list;
    }

    public void initialWindows(List<Window> list) {
        windowList = list;
    }

    private synchronized String getTicket() {
        if (ticketList.isEmpty()) return null;
        int random = (int)(Math.random()*(ticketList.size()));
        Ticket ticket = ticketList.get(random);
        if (ticket.isEmpty()) {
            ticketList.remove(ticket);
            return null;
        }
        String result = ticket.sellTicket();
        if (result == null) ticketList.remove(ticket);
        return result;
    }

    public synchronized boolean sellTicket(final Window window) {
        lock.lock();
        final String result = getTicket();
        lock.unlock();
        if (result == null) return false;
        Platform.runLater(new Runnable() {
            public void run() {
                controller.sellTicket(result, window.getName());
            }
        });
        updateCurrentTickets();
        return true;
    }

    public void start() {
        controller = MainController.loader.getController();
        service = Executors.newFixedThreadPool(windowList.size());
        for (Window window : windowList) {
            service.execute(window);
        }
    }

    public void close() {
        if (service == null) return;
        service.shutdown();
    }

    public void clear() {
        close();
    }

    public void updateCurrentTickets() {
        Platform.runLater(new Runnable() {
            public void run() {
                int currentTickets = 0;
                for (Ticket ticket : ticketList) currentTickets += ticket.getAmount();
                controller.setCurrentNumber(currentTickets);
            }
        });
    }
}
