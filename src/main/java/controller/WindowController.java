package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.io.IOException;

public class WindowController extends AnchorPane {
    public JFXListView<Label> sellList;
    public JFXButton removeButton;
    public JFXTextField windowName;
    public Text sum;
    private WindowViewController windowView = WindowViewController.loader.getController();
    private int count = 0;

    public WindowController() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/window.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void insertSell(String sell) {
        sellList.getItems().add(new Label(sell));
        count++;
        sum.setText(String.valueOf(count));
    }

    public void addWindow() {
        windowView.addWindow();
    }

    public void removeWindow() {
        windowView.removeWindow(this);
    }

    public String getWindowName() {
        return windowName.getText();
    }

    public void lockName() {
        windowName.setDisable(true);
    }

}
