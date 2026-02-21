package splitwisesystem;
import java.util.HashMap;
import java.util.Map;

public class UserBalanceSheet {
    // Stores: "User X owes me Y amount"
    // Positive value: They owe me. Negative value: I owe them.
    private Map<User, Double> friendsBalance;

    public UserBalanceSheet() {
        this.friendsBalance = new HashMap<>();
    }

    public void updateBalance(User friend, double amount) {
        friendsBalance.put(friend, friendsBalance.getOrDefault(friend, 0.0) + amount);
    }

    public Map<User, Double> getBalances() {
        return friendsBalance;
    }
}