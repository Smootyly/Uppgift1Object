//Peter Söder

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BankLogic {
    private List<Customer> customerList;

    public BankLogic () {
        this.customerList = new ArrayList<> ();

    }

    //Get all customers
    public ArrayList<String> getAllCustomers () {
        ArrayList<String> tempCustList = new ArrayList<> ();
        for (Customer customer : customerList) {
            tempCustList.add (customer.customerInfo ());
        }
        return tempCustList;

    }

    //Create customer
    public boolean createCustomer (String name, String surname, String pNo) {
        //loop list customerList
        for (Customer customer : customerList) {
            //If personal number exist in the list
            if (customer.getPersonalID ().equals (pNo)) {
                //Did not add customer
                return false;
            }
        }
        //Added Customer
        customerList.add (new Customer (name, surname, pNo));
        return true;
    }

    //Get customer info
    public ArrayList<String> getCustomer (String pNo) {
        ArrayList<String> customerInfoPrint = new ArrayList<> ();
        //loop list customerList
        for (Customer customer : customerList) {
            //If personal number exist in the list
            if (customer.getPersonalID ().equals (pNo)) {
                customerInfoPrint.add (customer.customerInfo ());
                //Loop list accountList
                for (Account account : customer.accountList) {
                    customerInfoPrint.add (account.accountInfo ());
                }
            }
        }
        if (customerInfoPrint.isEmpty ()) {
            //Could not find customer
            return null;
        } else {
            //Customer found
            return customerInfoPrint;
        }
    }
    //Get account info
    public String getAccount(String pNo, int accountId) {
        //Loop list customerList
        for (Customer customer : customerList) {
            //If personal number exist in the list
            if (customer.getPersonalID ().equals (pNo)) {
                //Loop list accountList
                for (Account account : customer.accountList) {
                    if (account.getAccountNumber () == accountId) {
                        return account.accountInfo ();

                    }
                }
            }
        }
        return null;
    }
    //Create a savings account for excisting customer
    public int createSavingsAccount (String pNo) {
        //loop list customerList
        for (Customer customer : customerList) {
            //If personal number exist in the list
            if (customer.getPersonalID ().equals (pNo)) {
                customer.createAccount ("Sparkonto");
                return customer.accountList.get (customer.accountList.size () - 1).getAccountNumber ();
            }
        }
        return -1;

    }

    //Change first name or last name or both
    public boolean changeCustomerName (String name, String surname, String pNo) {
        //loop list customerList
        for (Customer customer : customerList) {
            if (customer.getPersonalID ().equals (pNo)) {
                if (surname!=""||name!="") {
                    if (surname != "")
                        customer.setLastName (surname);
                    if (name != "")
                        customer.setFirstName (name);
                    return true;
                }
            }
        }
        return false;
    }

    //Deposit chash from account
    public Boolean deposit(String pNo, int accountId, int amount){
        //Check if account is 0 or negative
        if(amount>0) {
            //loop list customerList
            for (Customer customer : customerList) {
                //If personal number exist in the list
                if (customer.getPersonalID ().equals (pNo)) {
                    //If personal number exist in the list
                    for (Account account : customer.accountList) {
                        if (account.getAccountNumber () == accountId) {
                            //System.out.println (accountId);
                            account.transferred (new BigDecimal (amount));
                            return true;
                        }
                    }
                }
            }
        }
            return false;
    }
    //Withdraw cash from account
    public boolean withdraw(String pNo, int accountId, int amount){
        //Check if account is 0 or negative
        if(amount>0) {
            //Loop list customerList
            for (Customer customer : customerList) {
                //If personal number exist in the list
                if (customer.getPersonalID ().equals (pNo)) {
                    //Loop list accountList
                    for (Account account : customer.accountList) {
                        if (account.getAccountNumber () == accountId) {
                            //Kolla om den är större
                            int res=account.getBalance ().compareTo (BigDecimal.valueOf(amount));
                            //First value is bigger
                            if (res == 1 || res==0) {
                                account.withdraw (new BigDecimal (amount));
                                return true;
                            }
                            else;
                            return false;
                        }
                    }
                }
            }
        }
        return false;
    }
    //Close an account for an excisting customer
    public String closeAccount(String pNo, int accountId) {
        //loop list customerList
        for (Customer customer : customerList) {
            //If personal number exist in the list
            if (customer.getPersonalID ().equals (pNo)) {
                //Loop list accountList
                for (Account account : customer.accountList) {
                    if (account.getAccountNumber () == accountId) {
                        account.accountInfoWithIntrestEarned ();
                        customer.removeAccount (account);
                        return account.accountInfoWithIntrestEarned ();
                    }
                }
            }
        }
        return null;
    }
    //Delete a customers dara from the array customer and its accounts
    public ArrayList<String> deleteCustomer(String pNo){
        ArrayList<String> deleted = new ArrayList<> ();
        //loop list customerList
        for (Customer customer : customerList) {
            //If personal number exist in the list
            if (customer.getPersonalID ().equals (pNo)) {
                deleted.add (customer.customerInfo ());
                for (Account account : customer.accountList) {
                        deleted.add (account.accountInfoWithIntrestEarned ());
                    }
                customerList.remove (customer);
                return deleted;
                }
            }
        return null;
        }

    }

