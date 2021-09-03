package creational.builder.design.pattern;

import java.util.Optional;

public class NotificationFactory {

	public Optional<Notification> createNotification(String notificationType) {
		
		Optional<Notification> emptyNotification = Optional.empty();
		if (notificationType == null || notificationType.isEmpty())
            return emptyNotification;
		
		if ("SMS".equals(notificationType)) {
			return Optional.ofNullable(new SMSNotification());
		} else if ("Email".equals(notificationType)) {
			return Optional.ofNullable(new EmailNotification());
		} else if ("Push".equals(notificationType)){
			return Optional.ofNullable(new PushNotification());
		} else {
			return emptyNotification;
		}
	}
}
