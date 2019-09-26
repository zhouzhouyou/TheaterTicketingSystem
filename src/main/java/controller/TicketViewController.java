package controller;

import com.jfoenix.controls.JFXListView;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import logic.Ticket;


import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class TicketViewController extends AnchorPane implements Initializable {
    public JFXListView<TicketController> ticketList;
    public static FXMLLoader loader = new FXMLLoader(TicketViewController.class.getResource("/ticketView.fxml"));

    public TicketViewController() {
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void initialize(URL location, ResourceBundle resources) {
        addTicket();
    }

    public void addTicket() {
        TicketController ticketController = new TicketController();
        ObservableList<TicketController> observableList = ticketList.getItems();
        if (!observableList.isEmpty()) ticketController.ticketName.setText(observableList.get(observableList.size() - 1).getName() + "#");
        observableList.add(ticketController);
        boolean flag = observableList.size() <= 1;
        for (TicketController controller : ticketList.getItems()) {
            controller.removeButton.setDisable(flag);
        }
    }

    public List<Ticket> getTicketList() {
        List<Ticket> list = new LinkedList<Ticket>();
        for (TicketController ticketController : ticketList.getItems()) {
            list.add(new Ticket(ticketController.getName(), ticketController.getTotalNumber()));
        }
        return list;
    }

    public void removeTicket(TicketController ticketController) {
        ticketList.getItems().remove(ticketController);
        if (ticketList.getItems().size() <= 1) {
            for (TicketController controller : ticketList.getItems()) {
                controller.removeButton.setDisable(true);
            }
        }
    }

    public void clear() {
        ticketList.getItems().clear();
        addTicket();
    }
}
