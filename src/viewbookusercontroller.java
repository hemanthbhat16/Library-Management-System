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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class viewbookusercontroller {

    private Stage stage;
    private Scene scene;
    private Parent root;
    Connection con;
    PreparedStatement pst;
    ResultSet rst;

    @FXML
    private TableView<DataModel> ba;

    @FXML
    private TableColumn<DataModel, Integer> bid;

    @FXML
    private TableColumn<DataModel, String> bname;

    @FXML
    private TableColumn<DataModel, String> genre;

    @FXML
    private TableColumn<DataModel, Integer> pr;

     @FXML
    void back(ActionEvent event) throws IOException {
         FXMLLoader loader=new FXMLLoader(getClass().getResource("userfunction.fxml"));
        root=loader.load();
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() throws SQLException {
        bid.setCellValueFactory(new PropertyValueFactory<DataModel,Integer>("field1"));
        bname.setCellValueFactory(new PropertyValueFactory<DataModel,String>("field2"));
        genre.setCellValueFactory(new PropertyValueFactory<DataModel,String>("field3"));
        pr.setCellValueFactory(new PropertyValueFactory<DataModel,Integer>("field4"));
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/libmanage", "root", "@N4cnd9k2ay");
        pst=con.prepareStatement("SELECT * from books");
        rst=pst.executeQuery();

        while (rst.next()) 
        {
            int bookid=rst.getInt("BID");
            String booknm=rst.getString("BNAME");
            String auth=rst.getString("GENRE");
            int pric=rst.getInt("PRICE"); 
            DataModel data= new DataModel(bookid,booknm, auth,pric); 
            ba.getItems().add(data);       
        }
    }
    public class DataModel {

        private int field1;
        private String field2;
        private String field3;
        private int field4;
      
    
        public DataModel(int field1, String field2, String field3, int field4) {
            this.field1 = field1;
            this.field2 = field2;
            this.field3 = field3;
            this.field4 = field4;
        }

        public int getField1() {
            return field1;
        }
    
        public String getField2() {
            return field2;
        }

        public String getField3() {
            return field3;
        }
    
        public int getField4() {
            return field4;
        }
    }

}
