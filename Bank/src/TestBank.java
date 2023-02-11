import java.io.*;
import java.util.ArrayList;

//import qwerty1.BankLogic;

/**
 * D0018D, Lab 1: Testar att klasserna Account, Customer and BankLogic
 * fungerar som de ska. Notera att klassen kan uppdateras under kursens gång.
 *
 * Denna klass placeras i default package OBS!
 * Ändra paketets namn <qwerty1> i importen ovan, så den matchar
 * ditt pakets namn <användarid>
 *
 * Testprogrammet kontrollerar att det som returneras från dina metoder är det
 * som förväntas returneras. Detta betyder att du måste justera utskriften så
 * den är exakt så som specificeras i uppgiften.
 *
 * Last changes:  2022-08-24
 * @author Susanne Fahlman, susanne.fahlman@ltu.se
 */
public class TestBank
{
    private BankLogic bank = new BankLogic();
    private int testCounter = 1;

    //-----------------------------------------------------------------------------------
    // Runs the tests
    //-----------------------------------------------------------------------------------
    public void test() throws FileNotFoundException
    {
	String 	customerName;
	String 	customerSurname;
	String 	personalNumber;
	int 	accountNumber;
	int 	amount;

	// #1 Gets the empty list
	testingGetAllCustomers("[]");

	// #2 Create customer
	customerName = "Olle";
	customerSurname = "Ohlsson";
	personalNumber = "0005221898";
	testingCreateCustomer(customerName, customerSurname, personalNumber, true);

	// #3 Create customer
	customerName = "Karl";
	customerSurname = "Carlsson";
	personalNumber = "8505221898";
	testingCreateCustomer(customerName, customerSurname, personalNumber, true);

	// #4 Create customer with a persunal number that already exists
	customerName = "Donald";
	customerSurname = "Duck";
	personalNumber = "8505221898";
	testingCreateCustomer(customerName, customerSurname, personalNumber, false);

	// #5 Create customer
	customerName = "Pelle";
	customerSurname = "Persson";
	personalNumber = "6911258876";
	testingCreateCustomer(customerName, customerSurname, personalNumber, true);

	// #6 Create customer
	customerName = "Lotta";
	customerSurname = "Larsson";
	personalNumber = "7505121231";
	testingCreateCustomer(customerName, customerSurname, personalNumber, true);

	// #7 Delete customer
	personalNumber = "0005221898";
	testingDeleteCustomer(personalNumber, "[0005221898 Olle Ohlsson]");

	// #8 Delete customer that don't exists
	personalNumber = "0005221898";
	testingDeleteCustomer(personalNumber, "null");

	// #9 Get all customers in the list
	testingGetAllCustomers("[8505221898 Karl Carlsson, 6911258876 Pelle Persson, 7505121231 Lotta Larsson]");

	// #10 Change a customers name
	customerName = "Kalle";
	customerSurname = "";
	personalNumber = "8505221898";
	testingChangeName(customerName, customerSurname, personalNumber, true);

	// #11 Change a customers name
	customerName = "";
	customerSurname = "Karlsson";
	personalNumber = "8505221898";
	testingChangeName(customerName, customerSurname, personalNumber, true);

	// #12 Change a  name for a customer that don't exists
	customerName = "Olle";
	customerSurname = "Karlsson";
	personalNumber = "9905225166";
	testingChangeName(customerName, customerSurname, personalNumber, false);

	// #13 Get information about the customer
	testingGetCustomer("8505221898", "[8505221898 Kalle Karlsson]");

	// #14 Gets customer that don't exists
	testingGetCustomer("9905225166", "null");

	// #15 Creates accounts
	personalNumber = "8505221898";
	testingCreateSavingsAccount(personalNumber, 1001);

	// #16 Creates accounts for customer that don't exist
	personalNumber = "9905225166";
	testingCreateSavingsAccount(personalNumber, -1);

	// #17 Creates accounts
	personalNumber = "6911258876";
	testingCreateSavingsAccount(personalNumber, 1002);

	// #18 Creates accounts
	personalNumber = "8505221898";
	testingCreateSavingsAccount(personalNumber, 1003);

	// #19 Creates accounts
	personalNumber = "7505121231";
	testingCreateSavingsAccount(personalNumber, 1004);

	// #20 Get information about the customer including accounts
	testingGetCustomer("8505221898", "[8505221898 Kalle Karlsson, 1001 0,00 kr Sparkonto 1,2 %, 1003 0,00 kr Sparkonto 1,2 %]");

	// #21 Get information about the customer including accounts
	testingGetCustomer("6911258876", "[6911258876 Pelle Persson, 1002 0,00 kr Sparkonto 1,2 %]");

	// #22 Get information about the customer including accounts
	testingGetCustomer("7505121231", "[7505121231 Lotta Larsson, 1004 0,00 kr Sparkonto 1,2 %]");

	// #23 Transaction with customer that is not the owner of the account
	personalNumber 	= "8505221898";
	accountNumber  	= 1002;
	amount 			= 700;
	testingDeposit(personalNumber, accountNumber, amount, false);

	// #24 Transaction
	personalNumber 	= "8505221898";
	accountNumber  	= 1001;
	amount 			= 500;
	testingDeposit(personalNumber, accountNumber, amount, true);

	// #25 Transaction not enough money
	personalNumber 	= "8505221898";
	accountNumber  	= 1001;
	amount 			= 501;
	testingWithdraw(personalNumber, accountNumber, amount, false);

	// #26 Transaction
	personalNumber 	= "8505221898";
	accountNumber  	= 1001;
	amount 			= 500;
	testingWithdraw(personalNumber, accountNumber, amount, true);

	// #27 Transaction not enough money
	personalNumber 	= "8505221898";
	accountNumber  	= 1001;
	amount 			= 1;
	testingWithdraw(personalNumber, accountNumber, amount, false);

	// #28 Transaction
	personalNumber 	= "8505221898";
	accountNumber  	= 1001;
	amount 			= 1000;
	testingDeposit(personalNumber, accountNumber, amount, true);

	// #29 Get information about the customer including accounts
	testingGetCustomer("8505221898", "[8505221898 Kalle Karlsson, 1001 1 000,00 kr Sparkonto 1,2 %, 1003 0,00 kr Sparkonto 1,2 %]");

	// #30 Get information about the customer including accounts
	testingGetCustomer("6911258876", "[6911258876 Pelle Persson, 1002 0,00 kr Sparkonto 1,2 %]");

	// #31 Get information about the customer including accounts
	testingGetCustomer("7505121231", "[7505121231 Lotta Larsson, 1004 0,00 kr Sparkonto 1,2 %]");

	// #32 Get information about the account
	testingGetAccount("7505121231", 1004, "1004 0,00 kr Sparkonto 1,2 %");

	// #33 Get information about the account
	testingGetAccount("8505221898", 1001, "1001 1 000,00 kr Sparkonto 1,2 %");

	// #34 Get information about the account
	testingGetAccount("8505221898", 1002, "null");

	// #35 Closes the account
	testingCloseAccount("8505221898", 1001, "1001 1 000,00 kr Sparkonto 12,00 kr");

	// #36 Get information about all customers
	testingGetAllCustomers("[8505221898 Kalle Karlsson, 6911258876 Pelle Persson, 7505121231 Lotta Larsson]");

	// #37 Transaction
	personalNumber 	= "8505221898";
	accountNumber  	= 1003;
	amount 			= 5000;
	testingDeposit(personalNumber, accountNumber, amount, true);

	// #38 Transaction
	personalNumber 	= "8505221898";
	accountNumber  	= 1003;
	amount 			= 5000;
	testingDeposit(personalNumber, accountNumber, amount, true);

	// #39 Creates account
	personalNumber = "7505121231";
	testingCreateSavingsAccount(personalNumber, 1005);

	// #40 Get information about the customer including accounts
	testingGetCustomer("8505221898", "[8505221898 Kalle Karlsson, 1003 10 000,00 kr Sparkonto 1,2 %]");


	// #41 Get information about the customer including accounts
	testingGetCustomer("6911258876", "[6911258876 Pelle Persson, 1002 0,00 kr Sparkonto 1,2 %]");

	// #42 Get information about the customer including accounts
	testingGetCustomer("7505121231", "[7505121231 Lotta Larsson, 1004 0,00 kr Sparkonto 1,2 %, 1005 0,00 kr Sparkonto 1,2 %]");

	// #43 Transaction
	personalNumber 	= "7505121231";
	accountNumber  	= 1005;
	amount 			= 1000;
	testingDeposit(personalNumber, accountNumber, amount, true);

	// #44 Transaction
	personalNumber 	= "7505121231";
	accountNumber  	= 1005;
	amount 			= 100;
	testingWithdraw(personalNumber, accountNumber, amount, true);

	// #45 Transaction
	personalNumber 	= "7505121231";
	accountNumber  	= 1005;
	amount 			= 100;
	testingWithdraw(personalNumber, accountNumber, amount, true);

	// #46 Transaction
	personalNumber 	= "7505121231";
	accountNumber  	= 1005;
	amount 			= 100;
	testingWithdraw(personalNumber, accountNumber, amount, true);

	// #47 Transaction If a negative number is used for withdraw or deposit the transaction should not work
	personalNumber 	= "7505121231";
	accountNumber  	= 1005;
	amount 			= -100;
	testingWithdraw(personalNumber, accountNumber, amount, false);

	// #48 Transaction If a negative number is used for withdraw or deposit the transaction should not work
	personalNumber 	= "7505121231";
	accountNumber  	= 1005;
	amount 			= -1000;
	testingDeposit(personalNumber, accountNumber, amount, false);

	// #49 Get information about the customer including accounts
	testingGetCustomer("7505121231", "[7505121231 Lotta Larsson, 1004 0,00 kr Sparkonto 1,2 %, 1005 700,00 kr Sparkonto 1,2 %]");

	// #50 Creates accounts
	testingCreateSavingsAccount("7505121231", 1006);

	// #51 Closes the account
	testingCloseAccount("7505121231", 1006, "1006 0,00 kr Sparkonto 0,00 kr");

	// #52 Deletes the customer including accounts
	testingDeleteCustomer("7505121231", "[7505121231 Lotta Larsson, 1004 0,00 kr Sparkonto 0,00 kr, 1005 700,00 kr Sparkonto 8,40 kr]");

	// #53 Tries to delete a customer that don't exists
	testingDeleteCustomer("9905225166", "null");

	// #54 Tries to delete a account that don't exists
	testingCloseAccount("7505121231", 1009, "null");

	// #55 Get information about all customers
	testingGetAllCustomers("[8505221898 Kalle Karlsson, 6911258876 Pelle Persson]");

	// #56 Get information about the account
	testingGetAccount("6911258876", 1003, "null");

	// #57 Transaction
	personalNumber 	= "6911258876";
	accountNumber  	= 1003;
	amount 			= 900;
	testingDeposit(personalNumber, accountNumber, amount, false);

	// #58 Transaction
	personalNumber 	= "6911258876";
	accountNumber  	= 1002;
	amount 			= 900;
	testingDeposit(personalNumber, accountNumber, amount, true);

	// #59 Get information about the customer including accounts
	testingGetCustomer("6911258876", "[6911258876 Pelle Persson, 1002 900,00 kr Sparkonto 1,2 %]");

	// #60 Transaction
	personalNumber 	= "6911258876";
	accountNumber  	= 1002;
	amount 			= 900;
	testingWithdraw(personalNumber, accountNumber, amount, true);

	// #61 Transaction not owner of account
	personalNumber  = "6911258876";
	accountNumber   = 1003;
	amount          = 900;
	testingWithdraw(personalNumber, accountNumber, amount, false);

	// #62 Change a customers name
	customerName = "";
	customerSurname = "";
	personalNumber = "8505221898";
	testingChangeName(customerName, customerSurname, personalNumber, false);


	// #63 Get information about the customer including accounts
	testingGetCustomer("6911258876", "[6911258876 Pelle Persson, 1002 0,00 kr Sparkonto 1,2 %]");

	// #64 Deletes the customer
	testingDeleteCustomer("6911258876", "[6911258876 Pelle Persson, 1002 0,00 kr Sparkonto 0,00 kr]");

	// #65 Deletes the customer
	testingDeleteCustomer("8505221898", "[8505221898 Kalle Karlsson, 1003 10 000,00 kr Sparkonto 120,00 kr]");

	// #66 No customers left...
	testingGetAllCustomers("[]");

    }


    /**
     * Tests the method getAllCustomers
     * @param String facit - the facit that we compares with
     */
    private void testingGetAllCustomers(String facit)
    {
	boolean pass = false;

	// Calls the function
	ArrayList<String> result = bank.getAllCustomers();

	// Convert the ArrayList to a String that we can compare with
	String str = result.toString();
	pass = facit.equals(str);

	// Give feedback to the user
	if(pass)
	    System.out.println("Test " + testCounter++ + ": PASS");
	else
	{
	    System.out.println("Test " + testCounter++ + ": FAIL - getAllCustomers()");
	    System.out.println("Expected : " + facit);
	    System.out.println("Factual  : " + str);
	}
    }

    /**
     * Tests the method getCustomer
     * @param String facit - the facit that we compares with
     */
    private void testingGetCustomer(String pNo, String facit)
    {
	boolean pass = false;
	ArrayList<String> result = bank.getCustomer(pNo);

	// If the customer isn't in the bank result should be null
	// I want to use the string "null" to compare with
	String str = "null";

	// If the customer is found
	if(result != null)
	{
	    // Convert the ArrayList to a string that we can compare with
	    str = result.toString();

	    // Because of the format of balance we have to
	    // replace non-breaking-space with a normal space
	    str = str.replaceAll("\\u00a0"," ");
	}

	// Compare the strings
	pass = facit.equals(str);

	// Give feedback to the user
	if(pass)
	    System.out.println("Test " + testCounter++ + ": PASS");
	else
	{
	    System.out.println("Test " + testCounter++ + ": FAIL - testingGetCustomer("+ pNo +")");
	    System.out.println("Expected : " + facit);
	    System.out.println("Factual  : " + str);
	}
    }

    /**
     * Tests the method getAccount
     * @param pNo - Personal number of customer that owns the account
     * @param accountId - Id of the account that will be printed
     * @param String facit - the facit that we compares with
     */
    private void testingGetAccount(String pNo, int accountId, String facit)
    {
	boolean pass = false;
	String result = bank.getAccount(pNo, accountId);

	// If we cant find that account for that customer result should be null
	// I want to use the string "null" to compare with
	String str = "null";

	//If account is found for that customer
	if(result != null)
	{
	    // Convert the ArrayList to a string that we can compare with
	    str = result.toString();

	    // Because of the format of balance we have to
	    // replace non-breaking-space with a normal space
	    str = str.replaceAll("\\u00a0"," ");
	}

	// Compare the strings
	pass = facit.equals(str);

	// Give feedback to the user
	if(pass)
	    System.out.println("Test " + testCounter++ + ": PASS");
	else
	{
	    System.out.println("Test " + testCounter++ + ": FAIL - getAccount("+ pNo +", " + accountId + ")");
	    System.out.println("Expected : " + facit);
	    System.out.println("Factual  : " + str);
	}
    }

    /**
     * Tests the method createCustomer
     * @param name - Name of the customer
     * @param pNo - Personal number of customer
     * @param boolean facit - true if customer should be created otherwice false
     */
    private void testingCreateCustomer(String name, String surname, String pNo, boolean facit)
    {
	// Just check if we get the right value back and give feedback to the user
	if(bank.createCustomer(name, surname, pNo) == facit)
	    System.out.println("Test " + testCounter++ + ": PASS");
	else
	{
	    System.out.println("Test " + testCounter++ + ": FAIL - createCustomer("+name+", "+ surname+", "+ pNo+")");
	}
    }

    /**
     * Tests the method changeName
     * @param name - The new name
     * @param surname - The new surname
     * @param pNo - Personal number of customer that is getting a new name
     * @param facit - true if customer name should change otherwise false
     */
    private void testingChangeName(String name, String surname, String pNo, boolean facit)
    {
	// Just check if we get the right value back and give feedback to the user
	if(bank.changeCustomerName(name, surname, pNo) == facit)
	    System.out.println("Test " + testCounter++ + ": PASS");
	else
	{
	    System.out.println("Test " + testCounter++ + ": FAIL - testingChangeName("+name+", "+ surname+", "+ pNo+")");
	}
    }

    /**
     * Tests the method createSavingsAccount
     * @param pNo - Personal number of customer that is getting a new account
     * @param facit - "-1" if a account is not created otherwise a accountnumber
     */
    private void testingCreateSavingsAccount(String pNo, int facit)
    {
	int accountNo = bank.createSavingsAccount(pNo);
	// Check if we get the right value back and give feedback to the user
	if(accountNo == facit)
	    System.out.println("Test " + testCounter++ + ": PASS");
	else
	{
	    System.out.println("Test " + testCounter++ + ": FAIL - createSavingsAccount("+pNo+")");
	    System.out.println("Expected : " + facit);
	    System.out.println("Factual  : " + accountNo);
	}
    }

    /**
     * Tests the method deposit
     * @param pNo - The personal number of the customer that owns the account
     * @param accountId -  The id of the account
     * @param amount - The amount
     * @param facit - true if the amount is deposit false otherwise
     */
    private void testingDeposit(String pNo, int accountId, int amount, boolean facit)
    {
	// Check if we get the right value back and give feedback to the user
	if(bank.deposit(pNo, accountId, amount) == facit)
	    System.out.println("Test " + testCounter++ + ": PASS");
	else
	{
	    System.out.println("Test " + testCounter++ + ": FAIL - deposit("+ pNo +", "+ accountId+", " + amount +")");
	}
    }

    /**
     * Tests the method withdraw
     * @param pNr - The personal number of the customer that owns the account
     * @param accountId -  The id of the account
     * @param amount - The amount
     * @param facit - true if the amount is withdraw false otherwise
     */
    private void testingWithdraw(String pNo, int accountId, int amount, boolean facit)
    {
	// Check if we get the right value back and give feedback to the user
	if(bank.withdraw(pNo, accountId, amount) == facit)
	    System.out.println("Test " + testCounter++ + ": PASS");
	else
	{
	    System.out.println("Test " + testCounter++ + ": FAIL - withdraw("+ pNo +", "+ accountId+", " + amount +")");
	}
    }

    /**
     * Tests the method withdraw closeAccount
     * @param pNo - The personal number of the customer that owns the account
     * @param accountId - The id of the account that is to be closed
     * @param String facit - the facit that we compares with
     */
    private void testingCloseAccount(String pNo, int accountId, String facit)
    {
	boolean pass = false;
	String result = bank.closeAccount(pNo, accountId);

	// If the account wasn't closed result should be null
	// I want to use the string "null" to compare with
	String str = "null";

	// If the account is closed
	if(result != null)
	{
	    // Convert the ArrayList to a string that we can compare with
	    str = result.toString();

	    // Because of the format of balance we have to
	    // replace non-breaking-space with a normal space
	    str = str.replaceAll("\\u00a0"," ");
	}

	// Compare the two strings
	pass = facit.equals(str);

	// Give feedback to the user
	if(pass)
	    System.out.println("Test " + testCounter++ + ": PASS");
	else
	{
	    System.out.println("Test " + testCounter++ + ": FAIL - closeAccount("+pNo+", " + accountId + ")");
	    System.out.println("Expected : " + facit);
	    System.out.println("Factual  : " + str);
	}
    }

    /**
     * Tests the method withdraw deleteCustomer
     * @param pNo - The personal number of the customer that is to be deleted
     * @param String facit - the facit that we compares with
     */
    private void testingDeleteCustomer(String pNo, String facit)
    {
	boolean pass = false;
	ArrayList<String> result = bank.deleteCustomer(pNo);

	// If the account wasn't closed result should be null
	// I want to use the string "null" to compare with
	String str = "null";

	// If the customer is deleted
	if(result != null)
	{
	    // Convert the ArrayList to a string that we can compare with
	    str = result.toString();

	    // Because of the format of balance we have to
	    // replace non-breaking-space with a normal space
	    str = str.replaceAll("\\u00a0"," ");
	}

	// Compare the two strings
	pass = facit.equals(str);

	// Give feedback to the user
	if(pass)
	    System.out.println("Test " + testCounter++ + ": PASS");
	else
	{
	    System.out.println("Test " + testCounter++ + ": FAIL - testingDeleteCustomer("+pNo+")");
	    System.out.println("Expected : " + facit);
	    System.out.println("Factual  : " + str);
	}

    }

    public static void main(String[] args) throws FileNotFoundException
    {
	TestBank bankMenu = new TestBank();
	bankMenu.test();
    }
}
