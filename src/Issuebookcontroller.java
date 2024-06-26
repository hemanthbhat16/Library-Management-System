import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Issuebookcontroller{

    private Stage stage;
    private Scene scene;
    private Parent root;
    Connection con;
    PreparedStatement pst,pst1;

    @FXML
    private TextField bid;

    @FXML
    private DatePicker date;

    @FXML
    private TextField day;

    @FXML
    private TextField uid;

    @FXML
    private Label wr;   
    
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
    void sub(ActionEvent event) throws SQLException {
        String boisued=bid.getText();
        String usr=uid.getText();
        String d=day.getText();
        LocalDate issdate=date.getValue();

        if(boisued.isEmpty()||usr.isEmpty()||d.isEmpty()||issdate==null)
        {
            wr.setText("Plese fill all the fields");
        }
        else{
            int BID=Integer.parseInt(boisued);
            int UID=Integer.parseInt(usr);
            int day=Integer.parseInt(d);
            LocalDate rdate=issdate.plusDays(day);
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/libmanage", "root", "@N4cnd9k2ay");
            pst=con.prepareStatement("SELECT * FROM issued WHERE BID = ?");
            pst.setLong(1, BID);
            ResultSet rst = pst.executeQuery();
            if(rst.next())
            {
                wr.setText("Book is not Available");
            }
            else
            {
                pst1=con.prepareStatement("INSERT INTO issued(UID,BID,ISSUED_DATE,RETURN_DATE,PERIOD) VALUES (?, ?, ?, ?, ?)");
                pst1.setLong(1, UID);
                pst1.setLong(2, BID);
                pst1.setDate(3, java.sql.Date.valueOf(issdate));
                pst1.setDate(4, java.sql.Date.valueOf(rdate));
                pst1.setLong(5, day);
                pst1.executeUpdate();
                wr.setText("Successfully Updated");
            }
        }
    }

}
