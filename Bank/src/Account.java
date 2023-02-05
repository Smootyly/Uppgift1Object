import java.math.BigDecimal;
import java.math.RoundingMode;

public class Account {

    //Private properties
    private BigDecimal balance;
    private BigDecimal intrestRate =new BigDecimal("1.2");
    private int accountNumber;
    private String accountType;
    private static int lastAssignedNumber = 1000;

    //Metod och en Konstruktor
    //Konstruktor körs direkt när en klass startas
    //Konstruktor rerturnar alldrig saker
/*    public Account(){
        lastAssignedNumber++;
        accountNumber=lastAssignedNumber;

        System.out.println(accountNumber);
        this.balance=new BigDecimal (0);

    }*/

    //Overloading
    //Eftersom om man använder string i main så använder man denna istället
    public Account(String accountType){
        this.accountType=accountType;

        lastAssignedNumber++;
        accountNumber=lastAssignedNumber;

        System.out.println("This is card number "+accountNumber);
        this.balance=new BigDecimal (0);
    }

    public void deposit(BigDecimal ammount){
        balance=balance.add (ammount);
    }

    public void withdraw(BigDecimal ammount){
        balance=balance.subtract (ammount);
    }

    public BigDecimal intrest(){
        BigDecimal calculate=balance.multiply(intrestRate).divide (new BigDecimal (100), RoundingMode.DOWN);

        //System.out.println (calculate);
        return calculate;
    }
    //Incaptulation
    public void presentation (){
        System.out.println ("Your balance is "+balance+" SEK");
        System.out.println ("And your intrest is "+intrest()+"%");

    }



    public BigDecimal getBalance() {
        return balance;
    }

    public BigDecimal getIntrestRate() {
        return intrestRate;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public static int getLastAssignedNumber() {
        return lastAssignedNumber;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setIntrestRate(BigDecimal intrestRate) {
        this.intrestRate = intrestRate;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public static void setLastAssignedNumber(int lastAssignedNumber) {
        Account.lastAssignedNumber = lastAssignedNumber;
    }
    public String accountInfo(){
        return  this.accountNumber + " "+this.balance + " kr " +
                this.accountType + " "+ this.intrestRate + " % ";
    }
}
