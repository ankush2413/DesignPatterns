package builder_pattern;

public class User {

    // Required fields
    private final String name;
    private final int age;

    // Optional fields
    private final String email;
    private final String phone;
    private final String address;
    private final boolean isActive;

    // Private constructor
    private User(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.email = builder.email;
        this.phone = builder.phone;
        this.address = builder.address;
        this.isActive = builder.isActive;
    }

    public void print() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Address: " + address);
        System.out.println("Is Active: " + isActive);
    }
    // Static inner Builder class
    public static class Builder {

        // Required
        private final String name;
        private final int age;

        // Optional (default values)
        private String email;
        private String phone;
        private String address;
        private boolean isActive = true;

        // Builder constructor for required fields
        public Builder(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder isActive(boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
