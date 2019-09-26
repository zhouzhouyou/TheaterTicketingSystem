package controller;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXMasonryPane;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import logic.Window;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class WindowViewController extends JFXListView<WindowController> implements Initializable {
    public JFXListView<WindowController> view;
    public static FXMLLoader loader = new FXMLLoader(WindowViewController.class.getResource("/windowView.fxml"));

    public WindowViewController() {
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void initialize(URL location, ResourceBundle resources) {
        addWindow();
    }

    public void addWindow() {
        WindowController windowController = new WindowController();
        ObservableList<WindowController> observableList = view.getItems();
        if (!observableList.isEmpty()) windowController.windowName.setText(observableList.get(observableList.size()-1).getWindowName() + "#");
        observableList.add(windowController);
        boolean flag = observableList.size() <= 1;
        for (WindowController controller : view.getItems()) {
            controller.removeButton.setDisable(flag);
        }
    }

    public void removeWindow(WindowController windowController) {
        view.getItems().remove(windowController);
        if (view.getItems().size() <= 1) {
            for (WindowController controller : view.getItems()) {
                controller.removeButton.setDisable(true);
            }
        }
    }

    public List<Window> getWindowList() {
        List<Window> list = new LinkedList<Window>();
        for (WindowController controller : view.getItems()) {
            list.add(new Window(controller.getWindowName()));
        }

        return list;
    }

    public void lockWindowNames() {
        for (WindowController controller : view.getItems()) {
            controller.lockName();
        }
    }

    public void insertSell(String info, String windowName) {
        for (WindowController controller : view.getItems()) {
            if (!controller.getWindowName().equals(windowName)) continue;
            controller.insertSell(info);
        }
    }

    public void clear() {
        view.getItems().clear();
        addWindow();
    }
}
