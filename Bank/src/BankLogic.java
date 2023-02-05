import java.util.ArrayList;
import java.util.List;

public class BankLogic {
    private List<Customer> customerList;

    public BankLogic(){
        this.customerList=new ArrayList<>();

        customer.setLastName("Marcus");
        System.out.println (customer.getLastName ());
        System.out.println (customer2.customerInfo ());
        System.out.println (customer2.toString ());
        Customer customer= new createCustomer ("Peter", "Svantesson", "199404249337");
        customer.createAccount ("Sparkonto");
        customerList.add(customer2);
        customerList.add(customer3);
        customerList.add(customer3);
        createCustomer ("Peter", "Svantesson", "199404249337");
        getCustomer("199404249337");
        getCustomer("123");
    }
    public ArrayList<String> getAllCustomers (){
        ArrayList<String> tempCustList = new ArrayList<> ();
        for (Customer customer:customerList){
            tempCustList.add (customer.customerInfo ());
        }
        return tempCustList;


    }
    public boolean createCustomer(String name, String surname, String pNo){
        for(Customer customer:customerList){
            if (customer.getPersonalID().equals (pNo) ) {
                System.out.println ("Ja las inte till");
                return false;
            }
        }
        customerList.add (new Customer (name, surname, pNo));
        System.out.println ("Ja las till");
        return true;
    }

    public ArrayList<String> getCustomer(String pNo){
        ArrayList<String> customerInfoPrint = new ArrayList<>();
        for (Customer customer:customerList) {
            if(customer.getPersonalID().equals (pNo) ){
                customerInfoPrint.add (customer.customerInfo ());
                for(Account account: customer.accountList){
                    customerInfoPrint.add (account.accountInfo ());
                }
            }
        }
        if (customerInfoPrint.isEmpty ()) {
            System.out.println ("Ingen kund hittades");
            return null;
        }
        else {
            System.out.println (customerInfoPrint);
            return customerInfoPrint;
        }
    }


}
