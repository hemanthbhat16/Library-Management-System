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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class addbookcontroller {

    private Stage stage;
    private Scene scene;
    private Parent root;
    Connection con;
    PreparedStatement pst;

    @FXML
    private TextField bn;

    @FXML
    private TextField ge;

    @FXML
    private TextField pr;

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
        String book=bn.getText();
        String auth=ge.getText();
        String pric=pr.getText();

        if(book.isEmpty()||auth.isEmpty()||pric.isEmpty())
        {
            wr.setText("Plese fill all the fields");
        }
        else{
            Long price=Long.parseLong(pric);
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/libmanage", "root", "@N4cnd9k2ay");
            pst=con.prepareStatement("INSERT INTO books(BNAME,GENRE,PRICE) VALUES (?, ?, ?)");
            pst.setString(1, book);
            pst.setString(2, auth);
            pst.setLong(3, price);
            pst.executeUpdate();
            wr.setText("Successfully Updated");
        }
    }   

}
