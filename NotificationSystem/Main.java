package NotificationSystem;

import NotificationSystem.enums.Urgency;
import NotificationSystem.enums.Type;

/*
Requirements:
1. The system must support sending notifications through multiple distinct channels.
2. The system must classify incoming notifications by different levels of urgency.
3. Users must be able to configure their preferred notification channel based on the severity/urgency of the alert.
4. If a user has not explicitly configured a preference for a specific urgency level, 
    the system must not fail; it must use a default communication channel.
    

*/

public class Main {
    
    public static void main(String [] args)
    {
        System.out.println("Hello");
        User alice = new User("U1", "Alice", "alice@example.com", "+1-555-0199", "device_token_xyz890");
        
        // 2. Set Alice's custom preferences
        // Alice wants SMS for HIGH urgency, PUSH for MEDIUM, and falls back to EMAIL for LOW.
        alice.setPreference(Urgency.HIGH, Type.SMS);
        alice.setPreference(Urgency.MEDIUM, Type.PUSH);
        // Notice we don't set a preference for LOW urgency to test the fallback default.

        // 3. Initialize the Dispatcher
        NotificationDispatcher dispatcher = new NotificationDispatcher();

        // 4. Test Scenario A: High Urgency Alert (Should trigger SMS)
        dispatcher.dispatch(alice, "FRAUD ALERT: Suspicious login attempt!", Urgency.HIGH);

        // 5. Test Scenario B: Medium Urgency Alert (Should trigger Push Notification)
        dispatcher.dispatch(alice, "Your package is out for delivery.", Urgency.MEDIUM);

        // 6. Test Scenario C: Low Urgency Alert (Should fall back to Email default)
        dispatcher.dispatch(alice, "Your weekly screen time report is ready.", Urgency.LOW);
    }
}
