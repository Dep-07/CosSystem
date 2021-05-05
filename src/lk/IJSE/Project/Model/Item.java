package lk.IJSE.Project.Model;

public class Item {
    String ItemCode;
    String Description;
    int qty;
    double unitePrice;

    public Item() {

    }

    public Item(String itemCode, String description, int qty, double unitePrice) {
        this.ItemCode = itemCode;
        this.Description = description;
        this.qty = qty;
        this.unitePrice = unitePrice;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        this.ItemCode = itemCode;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
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
}
