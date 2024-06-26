import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
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

public class returnbookcontroller {

    private Stage stage;
    private Scene scene;
    private Parent root;
    Connection con;
    PreparedStatement pst,pst1;
    ResultSet rst;

    @FXML
    private TextField IId;

    @FXML
    private DatePicker date;

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
    void rturn(ActionEvent event) throws SQLException, IOException {
        String issuid=IId.getText();
        LocalDate retdate=date.getValue();

        if(issuid.isEmpty()||retdate==null)
        {
            wr.setText("Plese fill all the fields");
        }
        else
        {
            Long IID =Long.parseLong(issuid);
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/libmanage", "root", "@N4cnd9k2ay");
            pst=con.prepareStatement("SELECT * from issued WHERE iid = ?");
            pst.setLong(1, IID);
            rst=pst.executeQuery();
            if(rst.next())
            {
                Date runtdate=rst.getDate("RETURN_DATE");
                LocalDate rdte=runtdate.toLocalDate();
                int fine = rdte.compareTo(retdate);
                if(fine<0)
                {
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("message.fxml"));
                    root=loader.load();
                    String mes="RS 300 FINE";
                    messagecontroller mg=loader.getController();
                    mg.givevlu(mes);
                    stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                    scene=new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
                else{
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("message.fxml"));
                    root=loader.load();
                    String mes="Sucessfully Updated";
                    messagecontroller mg=loader.getController();
                    mg.givevlu(mes);
                    stage=(Stage)((Node)event.getSource()).getScene().getWindow();
                    scene=new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
                pst1=con.prepareStatement("DELETE FROM issued WHERE IID = ?");
                pst1.setLong(1, IID);
                pst1.executeUpdate();
            }
            else
            {
                wr.setText("Wrong IID please check");
            }
        }
    }

}
