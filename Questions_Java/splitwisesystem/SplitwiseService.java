package splitwisesystem;
import java.util.*;
import splitwisesystem.interfaces.CurrencyConverter;
import splitwisesystem.enums.Currency;

public class SplitwiseService {
    private static SplitwiseService instance;
    private Map<String, User> users;
    private Map<String, Group> groups;
    private CurrencyConverter currencyConverter;

    private SplitwiseService() {
        users = new HashMap<>();
        groups = new HashMap<>();
        currencyConverter = new SimpleCurrencyConverter();
    }

    public static SplitwiseService getInstance() {
        if (instance == null) instance = new SplitwiseService();
        return instance;
    }

    public void addUser(User user) { users.put(user.getId(), user); }
    public void addGroup(Group group) { groups.put(group.toString(), group); } // Simplified key

    public void addExpense(String groupId, Expense expense) {
        if (!expense.validate()) {
            System.out.println("Validation failed for expense: " + expense.getAmount());
            return;
        }

        User payer = expense.getPaidBy();

        for (Split split : expense.getSplits()) {
            User borrower = split.getUser();
            
            // If payer is part of the split, they don't owe themselves money
            if (borrower.getId().equals(payer.getId())) continue;

            // 1. Convert debt to Base Currency (USD) for consistency
            double amountInUSD = currencyConverter.convert(split.getAmount(), expense.getCurrency(), Currency.USD);

            // 2. Update Payer's Sheet (Borrower owes Payer +Amount)
            payer.getBalanceSheet().updateBalance(borrower, amountInUSD);

            // 3. Update Borrower's Sheet (Borrower owes Payer -Amount)
            borrower.getBalanceSheet().updateBalance(payer, -amountInUSD);
        }
        System.out.println("Expense added: " + expense.getAmount() + " " + expense.getCurrency());
    }

    public void showBalance(String userId) {
        User user = users.get(userId);
        System.out.println("Balances for " + user.getName() + " (in USD):");
        for (Map.Entry<User, Double> entry : user.getBalanceSheet().getBalances().entrySet()) {
            double bal = entry.getValue();
            if (bal != 0) {
                if (bal > 0) System.out.println("  " + entry.getKey().getName() + " owes you: $" + String.format("%.2f", bal));
                else System.out.println("  You owe " + entry.getKey().getName() + ": $" + String.format("%.2f", Math.abs(bal)));
            }
        }
    }
}