package splitwisesystem;
import splitwisesystem.enums.Currency;
import splitwisesystem.interfaces.SplitStrategy;
import splitwisesystem.Split;
import java.util.List;

public class Expense {
    private String id;
    private double amount;
    private Currency currency;
    private User paidBy;
    private List<Split> splits;
    private SplitStrategy strategy;

    public Expense(String id, double amount, User paidBy, List<Split> splits, SplitStrategy strategy) {
        this.id = id;
        this.amount = amount;
        this.currency = currency;
        this.paidBy = paidBy;
        this.splits = splits;
        this.strategy = strategy;
    }

    public boolean validate() {
        if (!strategy.validateSplit(splits, amount)) return false;
        strategy.calculateSplits(splits, amount);
        return true;
    }

    public List<Split> getSplits() { return splits; }
    public User getPaidBy() { return paidBy; }
    public double getAmount() { return amount; }
    public Currency getCurrency() { return currency; }
}