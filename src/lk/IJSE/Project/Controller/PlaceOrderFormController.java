package lk.IJSE.Project.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.IJSE.Project.Model.Customer;
import lk.IJSE.Project.Model.Item;
import lk.IJSE.Project.view.CartTM;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static lk.IJSE.Project.Db.Database.customerList;
import static lk.IJSE.Project.Db.Database.itemList;

public class PlaceOrderFormController {
    public TextField txtCName;
    public TextField txtCAddress;
    public TextField txtCSalary;
    public TextField txtDescription;
    public TextField txtQTYOnHand;
    public TextField txtUnitePrice;
    public TextField txtQTY;
    public Label txtTime;
    public Label txtDate;
    public Label txtOrderId;
    public AnchorPane contextOfPlaceOrderForm;
    public ComboBox cmbCustomerId;
    public ComboBox cmbItemCode;
    public TableView<CartTM> PlaceOrderTable;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colQty;
    public TableColumn colUnitePrice;
    public TableColumn colTotal;

    public void initialize(){
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitePrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        lordDate();
        //lordCustomerId();


        //comboBox Selection event listners--------------------------------------------------------------------
        cmbCustomerId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,newValue) -> {
            System.out.println(newValue);
            if(newValue != null){
                lordAllCustomer((String) newValue);
            }
        });
        //------------------------------------------------------------------------------------------------------
        //comboBox selection event listners-------------------------------------------------------------------

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue !=null){
                lordAllItem((String) newValue);
            }
        });
        //------------------------------------------------------------------------------------------------------
        }


    //lord Item Information to text Boxes-------------------------------------------------------------------------
    private void lordAllItem(String Value) {
        for(Item I : itemList){
            if(I.getItemCode().contains(Value)){
                txtDescription.setText(I.getDescription());
                txtQTYOnHand.setText(String.valueOf(I.getQty()));
                txtUnitePrice.setText(String.valueOf(I.getUnitePrice()));
                break;
            }
        }
    }
    //-------------------------------------------------------------------------------------------------------------


    //lord cutomer information to from-----------------------------------------------------------------------
    private void lordAllCustomer(String value) {
        for(Customer c : customerList){
            if(c.getId().contains(value)){
                txtCName.setText(c.getName());
                txtCAddress.setText(c.getAddress());
                txtCSalary.setText(String.valueOf(c.getSalary()));
                break;
            }
        }
    }
    //-----------------------------------------------------------------------------------------------------------









    //lord values into ComboBox- method 2---------------------------------------------------------------------------
    /*private void lordCustomerId() {
        ObservableList<String> obsCustomer = FXCollections.observableArrayList();
        for(Customer c1 : customerList){
            System.out.println(c1.getId());
            obsCustomer.add(c1.getId());
        }
        cmbCustomerId.setItems(obsCustomer);
    }*/
    //-------------------------------------------------------------------------------------------------------------

    //set Date and Time------------------------------------------------------------------------------------------
    public void lordDate(){
        Date date = new Date();
        System.out.println(date);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        txtDate.setText(dateFormat.format(date));
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss");
        txtTime.setText(timeFormat.format(date));

    }
    //-------------------------------------------------------------------------------------------------------------


    //Back TO Home--------------------------------------------------------------------------------------------------
    public void BackToHome(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) contextOfPlaceOrderForm.
                getScene().getWindow();
        stage.setScene(new Scene(
                FXMLLoader.load(getClass()
                        .getResource
                                ("../view/Dashbord.fxml"))));
    }
    //-------------------------------------------------------------------------------------------------------------


    //lord values into comboBox------------------------------------------------------------------------------------
    public void inputCustomerId(MouseEvent mouseEvent) {
        ObservableList<String> obsCustomer = FXCollections.observableArrayList();
        for(Customer c1 : customerList){
            //System.out.println(c1.getId());
            obsCustomer.add(c1.getId());
        }
        cmbCustomerId.setItems(obsCustomer);
    }
    //--------------------------------------------------------------------------------------------------------------

    public void lordItemCode(MouseEvent mouseEvent) {
        ObservableList<String> obsItem = FXCollections.observableArrayList();
        for(Item I1 :itemList){
            //System.out.println(c1.getId());
            obsItem.add(I1.getItemCode());
        }
        cmbItemCode.setItems(obsItem);

    }
    ObservableList<CartTM> cartList = FXCollections.observableArrayList();
    // Add Value to the table-------------------------------------------------------------------------------------
    public void AddToCart(ActionEvent actionEvent) {
       CartTM cart = new CartTM(
               (String) cmbItemCode.getValue(),
               txtDescription.getText(),
               Integer.parseInt(txtQTY.getText()),
               Double.parseDouble(txtUnitePrice.getText())
       );

       cartList.add(cart);
       PlaceOrderTable.setItems(cartList);
    }
   //-------------------------------------------------------------------------------------------------------------
}
