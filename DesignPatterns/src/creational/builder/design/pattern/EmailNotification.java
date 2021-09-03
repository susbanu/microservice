package creational.builder.design.pattern;

public class EmailNotification implements Notification {

	@Override
	public String notifyUser() {
		return "Email Notification";
	}

}
