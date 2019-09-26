package controller;

import com.jfoenix.controls.JFXDialog;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import logic.Theater;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public static FXMLLoader loader = new FXMLLoader(MainController.class.getResource("/main.fxml"));

    public StackPane stack;
    public Text current_number;
    public BorderPane mainPane;
    public BorderPane topPane;

    private boolean started = false;

    //private TicketViewController ticketView = TicketViewController.loader.getController();
    private TicketViewController ticketView = new TicketViewController();
    private WindowViewController windowView = new WindowViewController();

    private Theater theater = Theater.getInstance();

    public void initialize(URL location, ResourceBundle resources) {
        topPane.setCenter(ticketView);
        mainPane.setCenter(windowView);
    }

    public void sellTicket(String info, String window) {
        windowView.insertSell(info, window);
    }

    public void start() {
        if (started) {
            info();
            return;
        }
        started = true;
        theater.initialTickets(ticketView.getTicketList());
        theater.initialWindows(windowView.getWindowList());
        theater.start();
    }

    public void clear() {
        theater.clear();
        ticketView.clear();
        windowView.clear();
        started = false;
    }

    public void setCurrentNumber(int number) {
        current_number.setText(String.valueOf(number));
    }

    private void info() {
        JFXDialog dialog = new JFXDialog();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("/info.fxml"));
            Parent root = fxmlLoader.load();
            Region content = (Region) root;
            dialog.setContent(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.show(stack);
    }
}
