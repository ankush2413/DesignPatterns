package splitwisesystem;
import java.util.*;

class Group {
    private String id;
    private String name;
    private List<User> members;
    private List<Expense> expenses;

    public Group(String id, String name) {
        this.id = id;
        this.name = name;
        this.members = new ArrayList<>();
        this.expenses = new ArrayList<>();
    }
    public void addMember(User u) { members.add(u); }
    public void addExpense(Expense e) { expenses.add(e); }
}