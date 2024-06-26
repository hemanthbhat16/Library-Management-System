import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class userdetailscontroller {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private RadioButton ad;

    @FXML
    private Label wr;

   @FXML
    private PasswordField ps;

    @FXML
    private TextField us;

    @FXML
    private RadioButton usr;
    Connection con;
    PreparedStatement pst;

    @FXML
    void back(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("adminmenu.fxml"));
        root=loader.load();
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void crte(ActionEvent event) throws IOException, SQLException {
        String usrr=us.getText();
        String pass=ps.getText();
        Boolean admin=true;

        if(usrr.isEmpty()||pass.isEmpty()||(ad.isSelected()==false && usr.isSelected()==false))
        {
            wr.setText("Plese fill all the fields");
        }
        else{
            if(ad.isSelected()==false)
                admin=false;
            try{
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/libmanage", "root", "@N4cnd9k2ay");
                pst=con.prepareStatement("INSERT INTO users (USERNAME, PASSWORD, ADMIN) VALUES (?, ?, ?)");
                pst.setString(1, usrr);
                pst.setString(2, pass);
                pst.setBoolean(3, admin);
                pst.executeUpdate();
                wr.setText("Successfully Updated");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
            
    }

    

}
