package observerPattern;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

// Subject (Publisher) representing the restaurant
public class Restaurant implements OrderPublisher {
    private List<OrderObserver> chefs = new ArrayList<>();
    private List<OrderObserver> deliveryPersons = new ArrayList<>();
    private String lastOrder;

    @Override
    public void addObserver(OrderObserver observer) {
        if (observer instanceof Chef) {
            chefs.add(observer);
        } else if (observer instanceof DeliveryPerson) {
            deliveryPersons.add(observer);
        }
    }

    @Override
    public void notifyObservers(String order) {
        for (OrderObserver chef : chefs) {
            chef.update(order);
        }
        for (OrderObserver deliveryPerson : deliveryPersons) {
            deliveryPerson.update(order);
        }
    }

    public void handleOrder(String order) {
        // Notify a random chef and a random delivery person
        if (!chefs.isEmpty()) {
            Chef chef = getRandomChef();
            chef.handle(order);
        }
        if (!deliveryPersons.isEmpty()) {
            DeliveryPerson deliveryPerson = getRandomDeliveryPerson();
            deliveryPerson.handle(order);
        }
    }

    public void receiveOrder(String order) {
        System.out.println("New order received: " + order);
        notifyObservers(order);
        System.out.println();
        handleOrder(order);
        System.out.println();
        this.lastOrder = order;
    }

    public String getState() {
        return lastOrder; // Return the current state of the restaurant
    }

    // Helper method to get a random chef and delivery person
    private Chef getRandomChef() {
        Random random = new Random();
        int index = random.nextInt(chefs.size());
        return (Chef) chefs.get(index);
    }

    private DeliveryPerson getRandomDeliveryPerson() {
        Random random = new Random();
        int index = random.nextInt(deliveryPersons.size());
        return (DeliveryPerson) deliveryPersons.get(index);
    }
}