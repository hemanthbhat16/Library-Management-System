import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class adminmenucontroller {

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    Connection con;
    PreparedStatement pst;
    ResultSet rst;

    @FXML
    private AnchorPane commu;

    @FXML
    void ab(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("addbook.fxml"));
            root=loader.load();
            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    void au(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("userdetails.fxml"));
            root=loader.load();
            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    @FXML
    void ib(ActionEvent event) {
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("Issuebook.fxml"));
            root=loader.load();
            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @FXML
    void vb(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("viewbook.fxml"));
        root=loader.load();
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void vib(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("viewissued.fxml"));
        root=loader.load();
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void vu(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("userlist.fxml"));
        root=loader.load();
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void logout(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("signin.fxml"));
        root=loader.load();
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void retbook(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("returnbook.fxml"));
        root=loader.load();
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
