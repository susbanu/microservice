package behaviour.strategy.design.pattern;

public class PushNotificationStrategy implements NotificationStrategy {

	@Override
	public void notifyUser() {
		System.out.println("Notify user through Push");
	}

}
