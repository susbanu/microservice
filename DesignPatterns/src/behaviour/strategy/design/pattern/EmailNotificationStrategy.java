package behaviour.strategy.design.pattern;

public class EmailNotificationStrategy implements NotificationStrategy {

	private String email;
	
	public EmailNotificationStrategy(String email) {
		this.email = email;
	}
	
	@Override
	public void notifyUser() {
		System.out.println("Notify user through Email : "+email);;
	}

}
