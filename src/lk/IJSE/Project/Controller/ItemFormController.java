package lk.IJSE.Project.Controller;

import com.sun.xml.internal.ws.api.streaming.XMLStreamWriterFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.IJSE.Project.Model.Item;
import lk.IJSE.Project.view.CustomerTM;
import lk.IJSE.Project.view.ItemTM;


import java.io.IOException;
import java.util.ArrayList;

import static lk.IJSE.Project.Db.Database.customerList;
import static lk.IJSE.Project.Db.Database.itemList;

public class ItemFormController {
    public AnchorPane ContestOfItemForm;
    public TextField txtItemCode;
    public TextField txtQTYOnHand;
    public TextField txtUnitePrice;
    public TextField txtDescription;
    public Button btnSaveItem;
    public TableView<ItemTM> tableItem;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colUnitPrice;
    public TableColumn colQty;
    public TableColumn colOperate;
    public TextField txtSearch;

    ObservableList<ItemTM> obsList = FXCollections.observableArrayList();
    public void initialize(){
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("unitePrice"));
        colOperate.setCellValueFactory(new PropertyValueFactory<>("btn"));

        loadAllItems("");

        // row selection listeners-------------------------------------------------------------------------------------
        tableItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->{
            //System.out.println(newValue);
             if (newValue != null){
                setDataItem(newValue);

            }
        } );
        //-------------------------------------------------------------------------------------------------------------
    }


    //Back To Home-----------------------------------------------------------------------------------------------------
    public void BackToHome(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ContestOfItemForm.
                getScene().getWindow();
        stage.setScene(new Scene(
                FXMLLoader.load(getClass()
                        .getResource
                                ("../view/Dashbord.fxml"))));
    }
    //---------------------------------------------------------------------------------------------------------------



    public void SaveOnAction(ActionEvent actionEvent) {
        Item item1 = new Item(txtItemCode.getText(),
                txtDescription.getText(),
                Integer.parseInt(txtQTYOnHand.getText()),
                Double.parseDouble(txtUnitePrice.getText())
        );
        //Save Value--------------------------------------------------------------------------------------------------
        if(btnSaveItem.getText().equals("Save Item")){
            //itemList.add(new Item(item1.getItemCode(),item1.getDescription(),item1.getQty(),item1.getUnitePrice()));
            /*boolean isAdd = itemList.add(item1);*/
            if(itemList.add(item1)){
                new Alert(Alert.AlertType.CONFIRMATION,"Value Added", ButtonType.OK).show();
            }else{
                new Alert(Alert.AlertType.CONFIRMATION,"Try Again",ButtonType.CLOSE).show();
            }
        //------------------------------------------------------------------------------------------------------------
        }else{
            // Value Updated------------------------------------------------------------------------------------------
            /*for (int i = 0; i <itemList.size(); i++) {
                if(txtItemCode.getText().equals(itemList.get(i).getItemCode())){
                    itemList.remove(i);
                    if(itemList.add(item1)){
                        loadAllItems("");
                        new Alert(Alert.AlertType.CONFIRMATION,"Value Updated",ButtonType.OK).show();
                    }else{
                        new Alert(Alert.AlertType.CONFIRMATION,"Try Again",ButtonType.CLOSE).show();
                    }
                }
            }*/
            int count = 0;
            for(Item I : itemList){
                if(I.getItemCode().equals(txtItemCode.getText())){
                    itemList.get(count).setItemCode(txtItemCode.getText());
                    itemList.get(count).setDescription(txtDescription.getText());
                    itemList.get(count).setQty(Integer.parseInt(txtQTYOnHand.getText()));
                    itemList.get(count).setUnitePrice(Double.parseDouble(txtUnitePrice.getText()));
                    loadAllItems("");
                    break;
                }
                count++;
            }
            //-------------------------------------------------------------------------------------------------------


        }






    }

    //load All items to table but not displayed------------------------------------------------------------------------
  /*  public void loadAllItems(){
        ObservableList<ItemTM> observableListItem = FXCollections.observableArrayList();
        ArrayList<ItemTM> addList = new ArrayList<>();
        for(Item I : itemList){
            Button btn = new Button("Delete");
             observableListItem.add(new ItemTM(I.getItemCode(),I.getDescription(),I.getQty(),I.getUnitePrice(),btn));

            //Delete Customer------------------------------
            btn.setOnAction(e -> {
                if (itemList.remove(I)) {
                    loadAllItems();
                }
            });
            //---------------------------------------------

        }

        tableItem.setItems(observableListItem);

    }*/
    public void loadAllItems(String searText){
            obsList.clear();
        //System.out.println(txtSearch.getText());
            for(Item I: itemList ){
                if(I.getItemCode().contains(searText)||I.getDescription().contains(searText)){
                    Button btn = new Button("Delete");
                    obsList.add(new ItemTM(I.getItemCode(), I.getDescription(), I.getQty(),I.getUnitePrice(),btn));

                    btn.setOnAction(e -> {
                        if (itemList.remove(I)) {
                            loadAllItems("");
                        }
                    });
                }

            }


            tableItem.setItems(obsList);
    }
    //-----------------------------------------------------------------------------------------------------------------


     //set data to text fields----------------------------------------------------------------------------------------
     public void setDataItem(ItemTM tm){
        txtItemCode.setText(tm.getItemCode());
        txtDescription.setText(tm.getDescription());
        txtQTYOnHand.setText(String.valueOf(tm.getQty()));
        txtUnitePrice.setText(String.valueOf(tm.getUnitePrice()));
        btnSaveItem.setText("Update Values");
    }
    //----------------------------------------------------------------------------------------------------------------


    // clear text fields and change button text to "Save Value"-------------------------------------------------------
    public void NewItem(ActionEvent actionEvent) {
        txtItemCode.clear();
        txtDescription.clear();
        txtUnitePrice.clear();
        txtQTYOnHand.clear();
        btnSaveItem.setText("Save Item");
    }
    //---------------------------------------------------------------------------------------------------------------
    public void search(KeyEvent keyEvent) {
         loadAllItems(txtSearch.getText());
    }


}
