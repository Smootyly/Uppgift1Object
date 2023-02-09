import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String firstName;
    private String lastName;
    private String personalID;
    //Slipper hålla reda på konton i båda
    public List<Account> accountList;

    public Customer(String firstName,String lastName, String personalID){
        accountList = new ArrayList<> ();
        createAccount ("Sparkonto");
        this.firstName=firstName;
        this.lastName=lastName;
        this.personalID=personalID;
    }

    public void createAccount(String typeOfAccount){
        Account account=new Account(typeOfAccount);
        accountList.add (account);
    }

    public void removeAccount (int accountNumber){
        for (int i =0; i < accountList.size(); i++){
            System.out.println (accountList);
            if (accountList.get(i).getAccountNumber () == accountNumber){
                accountList.remove (accountList.get(i));
                System.out.println ("Account removed");
                System.out.println (accountList);
            }
        }

    }



    //Incapsulation
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPersonalID() {
        return personalID;
    }

    public List<Account> getCustomerList() {
        return accountList;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String customerInfo(){
        return  this.personalID + " "+this.firstName + " " + this.lastName;
    }


}


