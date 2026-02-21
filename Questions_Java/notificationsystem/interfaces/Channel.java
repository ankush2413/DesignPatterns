package notificationsystem.interfaces;

public enum Channel {
        EMAIL("email"),
        SMS("sms"),
        PUSH("push");

        private final String name;
        Channel(String name)
        {   
            this.name = name;
        }

        public String getName()
        {
            return this.name;
        }

}