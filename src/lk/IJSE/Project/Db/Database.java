package lk.IJSE.Project.Db;

import lk.IJSE.Project.Model.Customer;
import lk.IJSE.Project.Model.Item;

import java.util.ArrayList;

public class Database {


    public static ArrayList<Customer> customerList = new ArrayList/*<>*/();
    public static ArrayList<Item> itemList = new ArrayList/*<>*/();

    static {
        customerList.add(new Customer("C-001","Kamal","galle",25000));
        customerList.add(new Customer("c-002","Nimal","Colombo",4000));

        itemList.add(new Item("I-001","new One",20,240));
        itemList.add(new Item("I-002","Old One",50,260));


    }

}
