package behaviour.strategy.design.pattern;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

	List<Item> items;
	
	public ShoppingCart() {
		items = new ArrayList<>();
	}
	
	public void addItem(Item item) {
		items.add(item);
	}
	
	public void removeItem(Item item) {
		items.remove(item);
	}
	
	// Notify customer before making payment
	public void notifyCustomer(NotificationStrategy notificationStrategy) {
		notificationStrategy.notifyUser();
	}

	public void pay() {
		System.out.println("Payment of items that you are going to buy....");
	}
}
