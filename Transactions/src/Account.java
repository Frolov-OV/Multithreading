import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {

    private long money;
    private String accNumber;
    private Boolean isBlocked;

    public Account (String accNumber, long money) {
        this.accNumber = accNumber;
        this.money = money;
        isBlocked = false;
    }

    public boolean getStatus() {
        return isBlocked;
    }
    public void blockedAccount() {
        isBlocked = true;
        setMoney(0);
    }

    public String toString() {
        return "Your account number: " + getAccNumber() +
                ". And Your balance: " + getMoney() + " rub.";
    }
}
