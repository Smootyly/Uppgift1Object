import java.math.BigDecimal;

public class Account {

    //Private properties
    private BigDecimal balance;
    private BigDecimal intrestRate =new BigDecimal(1.2);
    private int accountNumber;
    private String accountType;
    private static int lastAssignedNumber = 1000;

    //Metod och en Konstruktor
    //Konstruktor körs direkt när en klass startas
    //Konstruktor rerturnar alldrig saker
    public Account(){
        lastAssignedNumber++;
        accountNumber=lastAssignedNumber;

        System.out.println(accountNumber);

    }

    //Overloading
    //Eftersom om man använder string i main så använder man denna istället
    public Account(String accountType){
        this.accountType=accountType;

        lastAssignedNumber++;
        accountNumber=lastAssignedNumber;

        System.out.println(accountNumber);

    }

    public void deposit(BigDecimal ammount){
        balance+=ammount;
    }

    //Incaptulation
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
}
