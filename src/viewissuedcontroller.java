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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class viewissuedcontroller {

    private Stage stage;
    private Scene scene;
    private Parent root;
    Connection con;
    PreparedStatement pst;
    ResultSet rst;

    @FXML
    private TableColumn<DataModel, Integer> bid;

    @FXML
    private TableView<DataModel> bookid;

    @FXML
    private TableColumn<DataModel, Integer> iid;

    @FXML
    private TableColumn<DataModel, Date> isdt;

    @FXML
    private TableColumn<DataModel, Integer> pri;

    @FXML
    private TableColumn<DataModel, Date> rtdt;

    @FXML
    private TableColumn<DataModel, Integer> uid;

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
        iid.setCellValueFactory(new PropertyValueFactory<DataModel,Integer>("field1"));
        uid.setCellValueFactory(new PropertyValueFactory<DataModel,Integer>("field2"));
        bid.setCellValueFactory(new PropertyValueFactory<DataModel,Integer>("field3"));
        isdt.setCellValueFactory(new PropertyValueFactory<DataModel,Date>("field4"));
        rtdt.setCellValueFactory(new PropertyValueFactory<DataModel,Date>("field5"));
        pri.setCellValueFactory(new PropertyValueFactory<DataModel,Integer>("field6"));
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/libmanage", "root", "@N4cnd9k2ay");
        pst=con.prepareStatement("SELECT * from issued");
        rst=pst.executeQuery();

        while (rst.next()) 
        {
            int issuid=rst.getInt("IID");
            int usrid=rst.getInt("UID");
            int bokid=rst.getInt("BID");
            Date issudate=rst.getDate("ISSUED_DATE");
            LocalDate idte=issudate.toLocalDate();
            Date runtdate=rst.getDate("RETURN_DATE");
            LocalDate rdte=runtdate.toLocalDate();
            int perd=rst.getInt("PERIOD"); 
            DataModel data= new DataModel(issuid,usrid,bokid,idte,rdte,perd); 
            bookid.getItems().add(data);       
        }
    }
    public class DataModel {

        private int field1;
        private int field2;
        private int field3;
        private LocalDate field4;
        private LocalDate field5;
        private int field6;
      
    
        public DataModel(int field1, int field2, int field3, LocalDate field4, LocalDate field5, int field6) {
            this.field1 = field1;
            this.field2 = field2;
            this.field3 = field3;
            this.field4 = field4;
            this.field5 = field5;
            this.field6 = field6;
        }

        public int getField1() {
            return field1;
        }
    
        public int getField2() {
            return field2;
        }

        public int getField3() {
            return field3;
        }
    
        public LocalDate getField4() {
            return field4;
        }

        public LocalDate getField5() {
            return field5;
        }

        public int getField6() {
            return field6;
        }
    }
}
