package lk.IJSE.Project.Model;

public class Customer {
    String Id;
    String name;
    String Address;
    double Salary;

    public Customer(String id, String name, String address, double salary) {
        this.Id = id;
        this.name = name;
        this.Address = address;
        this.Salary = salary;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }

    public double getSalary() {
        return Salary;
    }

    public void setSalary(double salary) {
        this.Salary = salary;
    }
}
