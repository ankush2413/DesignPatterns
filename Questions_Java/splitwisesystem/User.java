package splitwisesystem;


public class User {
    private String id;
    private String name;
    private UserBalanceSheet balanceSheet;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        this.balanceSheet = new UserBalanceSheet();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public UserBalanceSheet getBalanceSheet() { return balanceSheet; }
}