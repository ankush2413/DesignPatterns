package splitwisesystem;
import java.util.*;
import splitwisesystem.interfaces.SplitStrategy;
import splitwisesystem.enums.Currency;
import splitwisesystem.User;
import splitwisesystem.Expense;
import splitwisesystem.EqualSplitStrategy;

public class Main {
    public static void main(String[] args) {
        SplitwiseService service = SplitwiseService.getInstance();

        User u1 = new User("1", "Alice");
        User u2 = new User("2", "Bob");
        User u3 = new User("3", "Charlie");

        service.addUser(u1);
        service.addUser(u2);
        service.addUser(u3);

        // Scenario 1: Alice pays 3000 INR for everyone (EQUAL)
        List<Split> splits1 = new ArrayList<>();
        splits1.add(new Split(u1, 0));
        splits1.add(new Split(u2, 0));
        splits1.add(new Split(u3, 0));

        Expense e1 = new Expense("E1", 3000, Currency.INR, u1, splits1, new EqualSplitStrategy());
        service.addExpense("G1", e1);

        // Scenario 2: Bob pays 100 USD (Alice owes 40, Charlie owes 60) (EXACT)
        List<Split> splits2 = new ArrayList<>();
        splits2.add(new Split(u1, 40));
        splits2.add(new Split(u3, 60));
        
        Expense e2 = new Expense("E2", 100, Currency.USD, u2, splits2, new EqualSplitStrategy());
        service.addExpense("G1", e2);

        // Show Balances (Everything converted to USD)
        service.showBalance("1"); // Alice
        service.showBalance("2"); // Bob
    }
}