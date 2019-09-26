package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class TicketController extends AnchorPane implements Initializable {
    public JFXTextField ticketName;
    public JFXSlider slider;
    public JFXTextField totalNumber;
    public JFXButton removeButton;

    private TicketViewController ticketView = TicketViewController.loader.getController();

    public TicketController() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ticket.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void initialize(URL location, ResourceBundle resources) {
        setSliderProperty();
        setTotalNumberProperty();

    }

    private void setSliderProperty() {
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                int value = (int)slider.getValue();
                slider.setValue(value);
                totalNumber.setText(String.valueOf(value));
            }
        });
    }

    private void setTotalNumberProperty() {
        totalNumber.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    totalNumber.setText(newValue.replaceAll("[^\\d]", ""));
                } else {
                    int number = Integer.valueOf(totalNumber.getText());
                    if (number < 1) {
                        number = 1;
                        totalNumber.setText("1");
                    }
                    if (number <= 100) slider.setValue(number);
                }
            }});
    }

    public void add() {
        ticketView.addTicket();
    }

    public void remove() {
        ticketView.removeTicket(this);
    }

    public String getName() {
        return ticketName.getText();
    }

    public int getTotalNumber() {
        return Integer.valueOf(totalNumber.getText());
    }
}
