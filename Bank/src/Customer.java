import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String firstName;
    private String lastName;
    private int personalID;
    //Slipper hålla reda på konton i båda
    public List<Account> customerList;

    public Customer(){

    }

    //Incapsulation
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getPersonalID() {
        return personalID;
    }

    public List<Account> getCustomerList() {
        return customerList;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}


