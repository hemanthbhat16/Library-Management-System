
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class signincontroller {

    @FXML
    private TextField accountno;

    @FXML
    private PasswordField password;

    @FXML
    private Label warnl;

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;
    Connection con;
    PreparedStatement pst;
    ResultSet rst;

    @FXML
    void signin(ActionEvent event) throws IOException, SQLException {
        String acc=accountno.getText();
        String pass=password.getText();

        if (acc.isEmpty() || pass.isEmpty()) {
            warnl.setText("Please enter correct details");
        } else {
            try {
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/libmanage", "root", "@N4cnd9k2ay");
                pst=con.prepareStatement("SELECT * FROM users WHERE USERNAME = ? AND PASSWORD = ?");
                pst.setString(1, acc);
                pst.setString(2, pass);
                rst=pst.executeQuery();
                
                if (rst.next()) {
                    boolean adm=rst.getBoolean("ADMIN");
                    int uid=rst.getInt("UID");
                    if(adm==true)
                    {
                        FXMLLoader loader=new FXMLLoader(getClass().getResource("adminmenu.fxml"));
                        root=loader.load();
                        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                        scene=new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    }
                    else
                    {
                        FXMLLoader loader=new FXMLLoader(getClass().getResource("userfunction.fxml"));
                        root=loader.load();
                        userfunctioncontroller uf=loader.getController();
                        uf.getusr(acc,uid);
                        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                        scene=new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    }
                   
                }else{
                    warnl.setText("Please enter correct details");
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            
        }
        
    }

}
