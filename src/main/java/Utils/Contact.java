package Utils;

import java.util.Properties;

public class Contact {
    private final String phone;
    private final String emailAddress;

    public Contact(Properties properties) {
        phone = properties.getProperty("contact.phone");
        emailAddress = properties.getProperty("contact.emailAddress");
    }

    public String getPhone() {
        return phone;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
