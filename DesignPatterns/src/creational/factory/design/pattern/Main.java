package creational.factory.design.pattern;

import java.util.Optional;

public class Main {

	/*
	 * The factory design pattern is used when we have a superclass with multiple
	 * sub-classes and based on input, we need to return one of the sub-class.
	 * 
	 * This pattern takes out the responsibility of the instantiation of a class
	 * from the client program to the factory class.
	 * 
	 * Factory design pattern provides approach to code for interface rather than
	 * implementation. 
	 * 
	 * Factory pattern removes the instantiation of actual
	 * implementation classes from client code. Factory pattern makes our code more
	 * robust, less coupled and easy to extend. For example, we can easily change EmailNotification
	 * class implementation because client program is unaware of this. 
	 * 
	 * Factory pattern provides abstraction between implementation and client classes
	 * through inheritance.
	 * 
	 * Ex. java.util.Calendar, ResourceBundle and NumberFormat getInstance() methods uses Factory pattern.
	 */
	public static void main(String[] args) {
		NotificationFactory factory = new NotificationFactory();
		Optional<Notification> optionalNotification = factory.createNotification("SMS");
		Notification notification = optionalNotification.orElseGet(() -> new PushNotification()); // Default Push
																									// Notification
		System.out.println(notification.notifyUser());

	}
}