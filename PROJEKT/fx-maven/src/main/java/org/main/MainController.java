package org.main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.JMetroStyleClass;
import jfxtras.styles.jmetro.Style;
import org.dao.AbstractGenericDao;
import org.dao.UserDaoImpl;
import org.entities.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernateutil.HibernateUtil;

import java.io.IOException;

public class MainController {
    public Button userList;
    public Button manageUsers;
    public Button activeUsers;
    public Button taskList;
    public AnchorPane anchorPane;

    @FXML
    void accessCheck() {
        switch (LoginController.grantAccess) {
            case 1:
                userList.setDisable(true);
                manageUsers.setDisable(true);
                activeUsers.setDisable(true);
                break;
            case 2:
                userList.setDisable(true);
                manageUsers.setDisable(true);
                break;


        }

    }

    @FXML
    private void openTasksWindow() throws IOException {
        Scene scene;
        FXMLLoader fxmlLoader = new
                FXMLLoader(getClass().getResource("tasks.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        scene = (new Scene(root1));
        stage.setScene(scene);
        JMetro jMetro = new JMetro(Style.DARK);
        jMetro.setScene(scene);
        stage.setResizable(false);
        stage.show();


    }
    @FXML
    private void openUsersWindow() throws IOException {

        Scene scene;
        FXMLLoader fxmlLoader = new
                FXMLLoader(getClass().getResource("users.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        scene = (new Scene(root1));
        stage.setScene(scene);
        JMetro jMetro = new JMetro(Style.DARK);
        jMetro.setScene(scene);
        stage.setResizable(false);
        stage.show();


    }



    //Okno zadan
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        openTasksWindow();
    }

    @FXML
    private void handleButtonActionUsers(ActionEvent event) throws IOException {
        openUsersWindow();
    }



    @FXML
    void initialize() {
        UserDaoImpl impl = new UserDaoImpl();
        impl.findById(1);
        anchorPane.getStyleClass().add(JMetroStyleClass.BACKGROUND);
        accessCheck();


    }
}
