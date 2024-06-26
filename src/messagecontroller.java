import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class messagecontroller {

    @FXML
    private Label wr;

    public void givevlu(String mes) {
        wr.setText(mes);
    }



}
