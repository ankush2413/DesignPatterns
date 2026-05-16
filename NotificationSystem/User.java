package NotificationSystem;

import java.util.HashMap;
import java.util.Map;

import NotificationSystem.enums.Urgency;
import NotificationSystem.enums.Type;


public class User {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String deviceToken;
    
    // Maps an Urgency level to a preferred communication channel
    private Map<Urgency, Type> preferences;

    public User(String id, String name, String email, String phone, String deviceToken) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.deviceToken = deviceToken;
        this.preferences = new HashMap<>();
    }

    public String getName() { return name; }

    // Set what channel the user wants for a specific urgency
    public void setPreference(Urgency urgency, Type type) {
        preferences.put(urgency, type);
    }

    // Get the preferred channel, default to EMAIL if none is set
    public Type getPreferredType(Urgency urgency) {
        return preferences.getOrDefault(urgency, Type.EMAIL);
    }

    // Helper to extract the correct contact detail based on the channel type
    public String getContactInfoForType(Type type) {
        switch (type) {
            case SMS: return phone;
            case PUSH: return deviceToken;
            case EMAIL: return email;
            default: return email;
        }
    }
}
