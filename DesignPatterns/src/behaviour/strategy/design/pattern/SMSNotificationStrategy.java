package behaviour.strategy.design.pattern;

public class SMSNotificationStrategy implements NotificationStrategy {

	private String mobileNumber;
	
	public SMSNotificationStrategy(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	@Override
	public void notifyUser() {
		System.out.println("Notify user through SMS : "+mobileNumber);
	}

}
