import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

public class Account {

    //Private properties
    private BigDecimal balance;
    private BigDecimal interest =new BigDecimal("1.2");
    private int accountNumber;
    private String accountType;
    private static int lastAssignedNumber = 1000;

    //Metod och en Konstruktor
    //Konstruktor körs direkt när en klass startas
    //Konstruktor rerturnar alldrig saker

    //Overloading
    //Eftersom om man använder string i main så använder man denna istället
    public Account(String accountType){
        this.accountType=accountType;

        lastAssignedNumber++;
        accountNumber=lastAssignedNumber;

        System.out.println("This is card number "+accountNumber);
        this.balance=new BigDecimal (0);

    }

    public void transferred(BigDecimal ammount){
        balance=balance.add (ammount);
        System.out.println ("Grattis du har "+ ammount +" kr");
    }

    public void withdraw(BigDecimal ammount){
        balance=balance.subtract (ammount);
    }

    public String interest(){
        NumberFormat percentFormat = NumberFormat.getPercentInstance(new Locale ("sv", "SE"));
        percentFormat.setMaximumFractionDigits(1); // Anger att vi vill ha max 1 decimal
        String percentStr = percentFormat.format(interest.divide (new BigDecimal (100), RoundingMode.DOWN));

        //System.out.println (calculate);
        return percentStr;
    }
    //Incaptulation
    public void presentation (){
        System.out.println ("Your balance is "+balance+" SEK");
        System.out.println ("And your intrest is "+interest()+"%");

    }




    public String getBalance() {
        String balanceStr = NumberFormat.getCurrencyInstance(new Locale("sv","SE")).format(balance);
        return balanceStr;
    }

    public BigDecimal getInterestRate() {
        return interest;
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
        this.interest = intrestRate;
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
                this.accountType + " "+ this.interest + " % ";
    }
}
