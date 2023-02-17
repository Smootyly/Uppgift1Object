//Peter Söder
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String firstName;
    private String lastName;
    private String personalID;
    public List<Account> accountList;

    //Crete a customer and add it to list
    public Customer(String firstName,String lastName, String personalID){
        accountList = new ArrayList<> ();
        this.firstName=firstName;
        this.lastName=lastName;
        this.personalID=personalID;
    }

    //Create a account and add it to list
    public void createAccount(String typeOfAccount){
        Account account=new Account(typeOfAccount);
        accountList.add (account);
    }

    //remove accoount
    public void removeAccount (Account account){
        accountList.remove (account);
    }

    //Get first name (not in use)
    public String getFirstName() {
        return firstName;
    }

    //Get last name (not in use)
    public String getLastName() {
        return lastName;
    }

    //Get personal id (Not yet in use)
    public String getPersonalID() {
        return personalID;
    }

    public List<Account> getCustomerList() {
        return accountList;
    }

    //Setter set first name
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    //Setter set last name
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    //Customer info in a string for tests
    public String customerInfo(){
        return  this.personalID + " "+this.firstName + " " + this.lastName;
    }


}


