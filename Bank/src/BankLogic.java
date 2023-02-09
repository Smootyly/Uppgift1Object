import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BankLogic {
    private List<Customer> customerList;

    public BankLogic () {
        this.customerList = new ArrayList<> ();

    }

    public ArrayList<String> getAllCustomers () {
        ArrayList<String> tempCustList = new ArrayList<> ();
        for (Customer customer : customerList) {
            tempCustList.add (customer.customerInfo ());
        }
        return tempCustList;

    }

    public boolean createCustomer (String name, String surname, String pNo) {
        for (Customer customer : customerList) {
            if (customer.getPersonalID ().equals (pNo)) {
                System.out.println ("Ja las inte till");
                return false;
            }
        }
        customerList.add (new Customer (name, surname, pNo));
        System.out.println ("Ja las till");
        return true;
    }

    public ArrayList<String> getCustomer (String pNo) {
        ArrayList<String> customerInfoPrint = new ArrayList<> ();
        for (Customer customer : customerList) {
            if (customer.getPersonalID ().equals (pNo)) {
                customerInfoPrint.add (customer.customerInfo ());
                for (Account account : customer.accountList) {
                    customerInfoPrint.add (account.accountInfo ());
                }
            }
        }
        if (customerInfoPrint.isEmpty ()) {
            System.out.println ("Ingen kund hittades");
            return null;
        } else {
            System.out.println ("Kund " + customerInfoPrint + " hittades");
            return customerInfoPrint;
        }
    }

    public int createSavingsAccount (String pNo) {
        for (Customer customer : customerList) {
            if (customer.getPersonalID ().equals (pNo)) {
                customer.createAccount ("Sparkonto");
                System.out.println ("Skapa ett nytt konto for " + pNo);
                return customer.accountList.get (customer.accountList.size () - 1).getAccountNumber ();
            }
        }
        return -1;

    }

    public boolean changeCustomerName (String name, String surname, String pNo) {
        for (Customer customer : customerList) {
            if (customer.getPersonalID ().equals (pNo)) {
                if (surname!="")
                customer.setLastName (surname);
                if(name!="")
                customer.setFirstName (name);
                return true;
            }
        }
        return false;

    }

    public Boolean Deposit(String pNo, int accountId, int amount){
        if(amount>0) {
            for (Customer customer : customerList) {
                if (customer.getPersonalID ().equals (pNo)) {
                    for (Account account : customer.accountList) {
                        if (account.getAccountNumber () == accountId) {
                            account.transferred (new BigDecimal (amount));
                            return true;
                        }

                    }
                }
            }
        }
            System.out.println ("YOU FOOL");
            return false;
    }
    public boolean withdraw(String pNo, int accountId, int amount){

    }
}
