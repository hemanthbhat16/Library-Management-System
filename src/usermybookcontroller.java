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

public class usermybookcontroller {

    private Stage stage;
    private Scene scene;
    private Parent root;
    Connection con;
    PreparedStatement pst,pst1;
    ResultSet rst,rst1;
    public static int UID;

    public void getvalue(int usname) throws SQLException {
        UID=usname;
        initialize(UID);
    }

    @FXML
    private TableColumn<DataModel, String> ath;

    @FXML
    private TableColumn<DataModel, Integer> bId;

    @FXML
    private TableColumn<DataModel, String> bn;

    @FXML
    private TableColumn<DataModel, Date> id;

    @FXML
    private TableColumn<DataModel, Integer> pr;

    @FXML
    private TableColumn<DataModel, Date> rd;

    @FXML
    private TableView<DataModel> ub;

    @FXML
   void back(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("userfunction.fxml"));
        root=loader.load();
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize(int uid) throws SQLException {
        bId.setCellValueFactory(new PropertyValueFactory<DataModel,Integer>("field1"));
        bn.setCellValueFactory(new PropertyValueFactory<DataModel,String>("field2"));
        ath.setCellValueFactory(new PropertyValueFactory<DataModel,String>("field3"));
        pr.setCellValueFactory(new PropertyValueFactory<DataModel,Integer>("field4"));
        id.setCellValueFactory(new PropertyValueFactory<DataModel,Date>("field5"));
        rd.setCellValueFactory(new PropertyValueFactory<DataModel,Date>("field6"));
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/libmanage", "root", "@N4cnd9k2ay");
        pst=con.prepareStatement("SELECT * from issued WHERE uid = ?");
        pst.setLong(1, uid);
        rst=pst.executeQuery();
        while (rst.next()) 
        {
            int bookid=rst.getInt("BID");
            Date issudate=rst.getDate("ISSUED_DATE");
            LocalDate idte=issudate.toLocalDate();
            Date runtdate=rst.getDate("RETURN_DATE");
            LocalDate rdte=runtdate.toLocalDate();
            pst1=con.prepareStatement("SELECT * from books WHERE BID = ?");
            pst1.setLong(1, bookid);
            rst1=pst1.executeQuery();
            while(rst1.next())
            {
                String booknm=rst1.getString("BNAME");
                String auth=rst1.getString("GENRE");
                int pric=rst1.getInt("PRICE"); 
                DataModel data= new DataModel(bookid,booknm, auth, pric, idte, rdte); 
                ub.getItems().add(data);
            }       
        }
    
    }
    public class DataModel {

        private int field1;
        private String field2;
        private String field3;
        private int field4;
        private LocalDate field5;
        private LocalDate field6;
      
    
        public DataModel(int field1, String field2, String field3, int field4, LocalDate field5, LocalDate field6) {
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
    
        public String getField2() {
            return field2;
        }

        public String getField3() {
            return field3;
        }
    
        public int getField4() {
            return field4;
        }

        public LocalDate getField5() {
            return field5;
        }

        public LocalDate getField6() {
            return field6;
        }
    }
    

}
