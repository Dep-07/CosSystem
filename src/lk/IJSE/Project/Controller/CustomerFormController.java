package lk.IJSE.Project.Controller;


import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.IJSE.Project.Db.Database;
import lk.IJSE.Project.Model.Customer;
import lk.IJSE.Project.view.CustomerTM;

import java.io.IOException;

import static lk.IJSE.Project.Db.Database.customerList;


public class CustomerFormController {
    public AnchorPane contextOfCustomerForm;

    public TextField txtCId;
    public TextField txtCustomer;
    public TextField txtCSalary;
    public TextField txtCAddress;
    public TableView<CustomerTM> tblCustomer;
    public TableColumn tblCusId;
    public TableColumn tblCusName;
    public TableColumn tblCusSalary;
    public TableColumn tblCusAddress;
    public TableColumn tblCusOperate;
    public Button btnSave;
    public TextField txtSearch;

    public void initialize(){
        tblCusId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        tblCusName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCusSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        tblCusAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tblCusOperate.setCellValueFactory(new PropertyValueFactory<>("btn"));
        loadAllCustomer();

        //------------------------------------------tableView Select Column listener
        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{
            //System.out.println(newValue);
             if (newValue != null){
                 setData(newValue);

             }
        } );

        //---------------------------------------------
    }


    public void ClickOnActionSave(ActionEvent actionEvent) {
        Customer customer1 = new Customer(txtCId.getText(),
                txtCustomer.getText(),
                txtCAddress.getText(),
                Double.parseDouble(txtCSalary.getText()));
        //Save Customer---------------------------------------------------
        if(btnSave.getText().equals("Save Customer")){


            //boolean issAdd = Database.customerList.add(C1);
            if(customerList.add(customer1)){
                new Alert(Alert.AlertType.CONFIRMATION,"Done", ButtonType.OK).show();
                loadAllCustomer();
            }else{

                new Alert(Alert.AlertType.CONFIRMATION,"Try Again",ButtonType.CLOSE).show();
            }
            //------------------------------------------------------------------------------------
//        loadAllCustomer();
        }else{
            //update Customer-------------------------------------------------------------------
            for (int i = 0; i <customerList.size() ; i++) {
                if(txtCId.getText().equals(customerList.get(i).getId())){
                    customerList.remove(i);
                    if(customerList.add(customer1)){
                        loadAllCustomer();
                        new Alert(Alert.AlertType.CONFIRMATION,"Updated",ButtonType.OK).show();
                    }else{
                        new Alert(Alert.AlertType.CONFIRMATION,"Try Again",ButtonType.CLOSE).show();
                    }

                }
            }
            //---------------------------------------------------------------------------------------
        }



    }

    public void loadAllCustomer(){
        ObservableList<CustomerTM> observableList
                = FXCollections.observableArrayList();
        for (Customer c : Database.customerList
        ) {
            Button btn = new Button("Delete");

            observableList.add(
                    new CustomerTM(c.getId(), c.getName(), c.getAddress(), c.getSalary(), btn)
            );
            //Delete Customer------------------------------
            btn.setOnAction(e -> {
                if (customerList.remove(c)) {
                    loadAllCustomer();
                }
            });
            //---------------------------------------------
        }

        tblCustomer.setItems(observableList);

    }

    public void setData(CustomerTM customerTM){
        txtCId.setText(customerTM.getId());
        txtCustomer.setText(customerTM.getName());
        txtCAddress.setText(customerTM.getAddress());
        //txtCSalary.setText(customerTM.getSalary()+"");
        txtCSalary.setText(String.valueOf(customerTM.getSalary()));
        btnSave.setText("Update Customer");
    }
    //--------------------------------------------------Add new Customer
    public void ClickOnActionNewCustomer(ActionEvent actionEvent) {
        btnSave.setText("Save Customer");
        txtCId.clear();
        txtCustomer.clear();
        txtCSalary.clear();
        txtCAddress.clear();
    }
    //----------------------------------------------------------------------

    //------------------------------------------------------------------------Search Item
      /*String searchText = "";*/
    public void Search(KeyEvent keyEvent) {

        /*searchText = searchText +""+keyEvent.getText();
        System.out.println(searchText);

*/
        System.out.println(txtSearch.getText());
        ObservableList<CustomerTM> searchTM =FXCollections.observableArrayList();
        for(Customer c : customerList){

            if(c.getId().contains(txtSearch.getText())||
            c.getName().contains(txtSearch.getText())||
            c.getAddress().contains(txtSearch.getText())){
                Button btn = new Button("Delete");
                searchTM.add(new CustomerTM(c.getId(),c.getName(),c.getAddress(),c.getSalary(),btn));
            }
        }
        tblCustomer.setItems(searchTM);

    }
    //----------------------------------------------------------------------------------

    //-------------------------------------------------------------------Back To Home
    public void BackToHome(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) contextOfCustomerForm.
                getScene().getWindow();
        stage.setScene(new Scene(
                FXMLLoader.load(getClass()
                        .getResource
                                ("../view/Dashbord.fxml"))));
    }
    //-------------------------------------------------------------------

}
