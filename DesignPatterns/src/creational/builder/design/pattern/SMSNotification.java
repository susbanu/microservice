package creational.builder.design.pattern;

public class SMSNotification implements Notification {

	private String firstName;
	private String lastName;
	private String mobile;
	
	
	@Override
	public String notifyUser() {
		return "SMS Notification";
	}

}
