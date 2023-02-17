//Peter Söder
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

//Encapsulation
public class Account {

    //Private properties
    private BigDecimal balance;
    private BigDecimal interest =new BigDecimal("1.2");
    private int accountNumber;
    private String accountType;
    private static int lastAssignedNumber = 1000;

    //Fråga marcus vad denna kallas
    public Account(String accountType){
        this.accountType=accountType;
        lastAssignedNumber++;
        accountNumber=lastAssignedNumber;
        this.balance=new BigDecimal (0.00);
    }

    //Transfer money, add to balance card balance
    public void transferred(BigDecimal ammount){
        balance=balance.add (ammount);
    }

    //Transfor money, withdraw from card balance
    public void withdraw(BigDecimal ammount){
            balance = balance.subtract (ammount);
    }

    //Calculate interest on account in sek
    public String calculateIntrest(){
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale ("sv", "SE"));
        currencyFormat.setMaximumFractionDigits(2); // Anger att vi vill ha max 1 decimal
        String intrestSek = currencyFormat.format(balance.multiply (interest).divide (new BigDecimal (100), RoundingMode.DOWN));
        return intrestSek;
    }

    //Setting balance in sek
    public String balanceSek (BigDecimal balance){
        String balanceStr = NumberFormat.getCurrencyInstance(new Locale("sv","SE")).format(balance);
        return balanceStr;
    }

    //Show intrest rate
    public String getInterestRate(BigDecimal interest) {
        NumberFormat percentFormat = NumberFormat.getPercentInstance(new Locale("sv","SE"));
        percentFormat.setMaximumFractionDigits(1);
        String percentStr = percentFormat.format(interest.divide (new BigDecimal (100)));

        return percentStr;
    }

    //Getter method for balance Marcus
    public BigDecimal getBalance () {
        return balance;
    }

    //Getter method for account number
    public int getAccountNumber() {
        return accountNumber;
    }

    //Gets account type (Not yet in use)
    public String getAccountType() {
        return accountType;
    }

    //Getter for last assigned number (Marcus remove?)
    public static int getLastAssignedNumber() {
        return lastAssignedNumber;
    }

    //Set balance (Not yet in use)(Marcus remove?)
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    //Set an intrest rate (Not yet in use)(MArcus remove??)
    public void setIntrestRate(BigDecimal intrestRate) {
        this.interest = intrestRate;
    }

    //Set account type, (Not yet in use MAcrus kvar?)
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    //Marcus remove??
    public static void setLastAssignedNumber(int lastAssignedNumber) {
        Account.lastAssignedNumber = lastAssignedNumber;
    }
    //Wright accountinfo as a string for the tests
    public String accountInfo(){
        return  this.accountNumber + " "+this.balanceSek (balance) + " " +
                this.accountType + " "+ this.getInterestRate (interest) ;
    }
    //Wright account info as a string used for tests
    public String accountInfoWithIntrestEarned(){
        return  this.accountNumber + " "+balanceSek (balance) + " " +
                this.accountType + " "+ this.calculateIntrest();
    }

}
