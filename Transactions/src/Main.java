import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String someClientAccount = "408" + RandomStringUtils.randomNumeric(15);
    //private static final String toAccount = "408" + RandomStringUtils.randomNumeric(15);
    private static final long amount = (long)(Math.random() * (99999 - 10000) + 10000) + 50000;
    private static final long transferMoney = (long)(Math.random() * (99999 - 10000) + 10000);

    public static void main(String[] args) {

        Bank bank = new Bank();
        Account client = new Account(someClientAccount, amount);
        Account client2 = new Account(someClientAccount, amount);
        Account client3 = new Account(someClientAccount, amount);
        bank.addAccount(client);
        bank.addAccount(client2);
        bank.addAccount(client3);
        //bank.getSumAllAccounts();

        List<Thread> threadList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            threadList.add(
                    new Thread(() -> {
                        System.out.println("Старт поток");
                        for (int t = 0; t < 10_000; t++) {
                            try {
                                bank.transfer(client.getAccNumber(), client2.getAccNumber(), transferMoney);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("Стоп поток");
                    })
            );
        }

        threadList.forEach(Thread::start);
    }

}
