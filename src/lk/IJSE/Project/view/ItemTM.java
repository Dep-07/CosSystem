package lk.IJSE.Project.view;

import javafx.scene.control.Button;

public class ItemTM {
    String itemCode;
    String description;
    int qty;
    double unitePrice;
    Button btn;

    public ItemTM() {

    }



    public ItemTM(String itemCode, String description, int qty, double unitePrice, Button btn) {
        this.itemCode = itemCode;
        this.description = description;
        this.qty = qty;
        this.unitePrice = unitePrice;
        this.btn = btn;


    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitePrice() {
        return unitePrice;
    }

    public void setUnitePrice(double unitePrice) {
        this.unitePrice = unitePrice;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
