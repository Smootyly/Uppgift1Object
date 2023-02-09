import java.math.BigDecimal;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {

        BankLogic bank = new BankLogic ();
        bank.createCustomer ("Peter", "Soder", "199404249337");
        bank.getCustomer("199404249337");
        bank.getCustomer("123");
        bank.createSavingsAccount("199404249337");
        bank.getCustomer("199404249337");
        bank.changeCustomerName ("Marcus", "","199404249337");
        bank.getCustomer ("199404249337");
        bank.Deposit ("199404249337",1002,-500);
    }
}
