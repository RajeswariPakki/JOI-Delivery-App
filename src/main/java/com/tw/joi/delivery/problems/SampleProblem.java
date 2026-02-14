package com.tw.joi.delivery.problems;
import com.tw.joi.delivery.model.*;
import com.tw.joi.delivery.staticData.*;
import java.util.*;

public class SampleProblem {
    public static void main(String[] args) {
        SampleProblem sample = new SampleProblem();
        //double cost = sample.calculateCost(8.0); // Example distance
        //System.out.println("Delivery cost for 8 km: ₹" + cost);

        // 1. Create customer
        Customer cust = new Customer("C01", "kamakhya", "sree");
        String customerZone = "ZoneA";

        //2. Get Store details
        List<Store> stores = StaticData.getStores();
        Store store = stores.get(2);
        
        //Fetch Distance
        int distance = getDistanceBetweenZones(customerZone, store.getZone());

        // 3. Pick items from static data
        List<Item> items = StaticData.getItems();
        List<Item> orderedItems = new ArrayList<>();
        orderedItems.add(items.get(0)); // Notebook
        orderedItems.add(items.get(2));

        // 4. Create order
        Order order = new Order();
        order.setCustomer(cust);
        order.setStore(store);
        order.setItems(orderedItems);
        order.setOrderStatus(OrderStatus.CREATED);

        // 5. Calculate total amount
        double totalAmount = 0;
        for (Item item : order.getItems()) {
            totalAmount += item.getPrice();
        }
        order.setTotalAmount(totalAmount);

        // 6. Set order status
        order.setOrderStatus(OrderStatus.CREATED);

        // 7. Print order details
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Customer: " +
                cust.getFirstName() + " " +
                cust.getLastName());
        System.out.println("Store ID: " + store.getStoreId());
        System.out.println("Store Zone: " + store.getZone());
        System.out.println("Items Ordered:");

        for(Item i:order.getItems()) {
            System.out.println("- " + i.getName() + " : ₹" + i.getPrice());
        }

        System.out.println("Total Amount: ₹" + order.getTotalAmount());
        System.out.println("Order Status: " + order.getOrderStatus());
        System.out.println("Store Zone: " + store.getZone());
        System.out.println("Order Created At: " + order.getCreatedAt());
        System.out.println("Distance: " + distance + " km");

        double cost = sample.calculateCost(distance); // Example distance
        System.out.println("Delivery cost for " + distance +" km: ₹" + cost);

        //Low Stock Items method call
        getLowStockItems(StaticData.mystore);
    }

    // Write your methods here.

    public static void getLowStockItems(List<Store> stores) {
        int lowstockcnt=0;
        for(Store st:stores) {
            for(Item it: st.getItems()) {
                if(it.getRemaining() < 5) { //to get the count of low stock stores
                    lowstockcnt++;
                    break;
                }
            }
        }
        System.out.println("Low stock stores -> " + lowstockcnt + " stores");
        for(Store store: stores) {
            boolean printstore=false;  //to avoid same store name printing multiple times for every low stock item within the store
            for(Item i: store.getItems()) {
                int remaining=i.getRemaining();
                if(remaining<5) {
                    if(!printstore) {
                        System.out.println(store.getStoreName() + ":");
                        printstore = true;
                    }
                    if (remaining == 1) {
                        System.out.println(i.getName() + " -> Exactly 1 stock left");
                    } else {
                        System.out.println(i.getName() + " -> remaining=" + remaining);
                    }
                }
            }
        }

    }

    public static int getDistanceBetweenZones(String fromzone, String tozone) {
        List<DistanceMap> dmlist = StaticData.getDistanceMap();

        for(DistanceMap dm: dmlist) {
            if (dm.getZoneFrom().equals(fromzone) && dm.getZoneTo().equals(tozone))
                return dm.getDistance();
            // reverse match (ZoneB -> ZoneA)
            if (dm.getZoneFrom().equals(tozone) && dm.getZoneTo().equals(fromzone)) {
                return dm.getDistance();
            }
        }

        throw new RuntimeException();
    }

    public static Double calculateCost(double distanceKm) {
        if (distanceKm <= 0) {
            throw new IllegalArgumentException("Distance must be positive");
        }

        double baseCost = 50.0;
        if (distanceKm <= 5) {
            return baseCost;
        } else {
            double extraDistance = distanceKm - 5;
            return baseCost + (extraDistance * 10);
        }
    }
}
