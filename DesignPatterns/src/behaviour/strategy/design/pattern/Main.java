package behaviour.strategy.design.pattern;

public class Main {

	/*
	 * Strategy pattern is also known as Policy Pattern. We define multiple
	 * algorithms and let client application pass the algorithm to be used as a
	 * parameter.
	 * 
	 * One of the best example of strategy pattern is Collections.sort() method that
	 * takes Comparator parameter. Based on the different implementations of
	 * Comparator interfaces, the Objects are getting sorted in different ways.
	 * 
	 * allows us to change the behavior of an algorithm at runtime.
	 * 
	 */
	public static void main(String[] args) {
		
		Item mobile = new Item("Mobile", 10000);
		Item computer = new Item("Dell PC", 50000);
		
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.addItem(mobile);
		shoppingCart.addItem(computer);
		
		shoppingCart.notifyCustomer(new SMSNotificationStrategy("1234567890"));
		shoppingCart.notifyCustomer(new EmailNotificationStrategy("sushant@gmail.com"));
		shoppingCart.pay();
	}
}
