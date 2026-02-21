package builder_pattern;

public class Main{
    public static void main(String[] args) {
        User user = new User.Builder("Ankush", 26)
        .email("ankush@gmail.com")
        .phone("9999999999")
        .address("Bangalore")
        .isActive(true)
        .build();

        user.print();

        User user2 = new User.Builder("John",30).build();
        user2.print();
    }
}

/*
Why this is beautiful
 - Self-documenting
 - Order doesn’t matter
 - Easy to extend
 - No constructor explosion

When should you use Builder?
 - Use Builder when:
 - Object has many optional fields
 - You want immutable objects
 - You want readable object creation
 - Constructor overloads are getting ugly