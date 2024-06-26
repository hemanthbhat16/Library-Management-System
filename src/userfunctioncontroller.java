import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class userfunctioncontroller {

    public static String usname;
    public static int UID;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    Connection con;
    PreparedStatement pst;
    ResultSet rst;

    public void getusr(String acc, int uidd) {
        usname=acc;
        UID=uidd;
    }

    @FXML
    void mybo(ActionEvent event) throws IOException, SQLException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("usermybook.fxml"));
        root=loader.load();
        usermybookcontroller ub=loader.getController();
        ub.getvalue(UID);
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }

    @FXML
    void vibo(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("viewbookuser.fxml"));
        root=loader.load();
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void back(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("signin.fxml"));
        root=loader.load();
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    

}
