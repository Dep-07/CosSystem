package lk.IJSE.Project.Controller;

import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoardFormController {

    public AnchorPane contextOfDashBoard;

    public void openCustomerForm(MouseEvent mouseEvent) throws IOException {


        /*Stage stage = (Stage) contextOfDashBoard.getScene().getWindow();
        stage.setScene(new Scene(
                FXMLLoader.load(getClass()
                        .getResource
                                ("../view/CustomerForm.fxml"))));*/
        setPage("CustomerForm");

    }

    public void openItemForm(MouseEvent mouseEvent) throws IOException {

       /* Stage stage = (Stage) contextOfDashBoard.getScene().getWindow();
        stage.setScene(new Scene(
                FXMLLoader.load(getClass()
                        .getResource
                                ("../view/ItemForm.fxml"))));*/
        setPage("ItemForm");

    }

    public void setPage(String label) throws IOException {

        Stage stage = (Stage) contextOfDashBoard.getScene().getWindow();
        stage.setScene(new Scene(
                FXMLLoader.load(getClass()
                        .getResource
                                ("../view/"+label+".fxml"))));
    }

    public void openPlaceOrderForm(MouseEvent mouseEvent) throws IOException {
              setPage("PlaceOrderForm");
    }
}
