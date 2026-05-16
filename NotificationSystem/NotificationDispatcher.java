package NotificationSystem;

import NotificationSystem.enums.Urgency;
import NotificationSystem.enums.Type;
import NotificationSystem.interfaces.NotificationStrategy;

public class NotificationDispatcher {
    public void dispatch(User user, String content, Urgency urgency) {
        System.out.println("--- Dispatching Notification for " + user.getName() + " ---");
        
        // 1. Figure out HOW the user wants to receive this specific urgency
        Type targetType = user.getPreferredType(urgency);
        
        // 2. Get the correct contact detail (e.g., if SMS, get phone number)
        String contactInfo = user.getContactInfoForType(targetType);
        
        // 3. Instantiate the correct sending strategy via the Factory
        NotificationStrategy strategy = NotificationFactory.getStrategy(targetType);
        
        // 4. Execute the send
        strategy.send(contactInfo, content, urgency);
        System.out.println();
    }
}
