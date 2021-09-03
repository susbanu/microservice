package creational.factory.design.pattern;

public class SMSNotification implements Notification {

	@Override
	public String notifyUser() {
		return "SMS Notification";
	}

}
