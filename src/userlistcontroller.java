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

public class userlistcontroller {

    private Stage stage;
    private Scene scene;
    private Parent root;
    Connection con;
    PreparedStatement pst;
    ResultSet rst;

    @FXML
    private TableColumn<DataModel, Boolean> adm;

    @FXML
    private TableColumn<DataModel, String> pwd;

    @FXML
    private TableColumn<DataModel, Integer> uid;

    @FXML
    private TableView<DataModel> ul;

    @FXML
    private TableColumn<DataModel, String> uname;

    @FXML
    void back(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("adminmenu.fxml"));
        root=loader.load();
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() throws SQLException {
        uid.setCellValueFactory(new PropertyValueFactory<DataModel,Integer>("field1"));
        uname.setCellValueFactory(new PropertyValueFactory<DataModel,String>("field2"));
        pwd.setCellValueFactory(new PropertyValueFactory<DataModel,String>("field3"));
        adm.setCellValueFactory(new PropertyValueFactory<DataModel,Boolean>("field4"));
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/libmanage", "root", "@N4cnd9k2ay");
        pst=con.prepareStatement("SELECT * from users");
        rst=pst.executeQuery();

        while (rst.next()) 
        {
            int userid=rst.getInt("UID");
            String usernm=rst.getString("USERNAME");
            String pass=rst.getString("PASSWORD");
            boolean admin=rst.getBoolean("ADMIN");
            DataModel data= new DataModel(userid,usernm,pass,admin); 
            ul.getItems().add(data);       
        }
    }
    public class DataModel {

        private int field1;
        private String field2;
        private String field3;
        private Boolean field4;
      
    
        public DataModel(int field1, String field2, String field3, Boolean field4) {
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
    
        public Boolean getField4() {
            return field4;
        }
    }

}
