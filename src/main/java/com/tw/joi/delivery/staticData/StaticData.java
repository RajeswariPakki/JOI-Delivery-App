package com.tw.joi.delivery.staticData;

import com.tw.joi.delivery.model.DistanceMap;
import com.tw.joi.delivery.model.Item;
import com.tw.joi.delivery.model.Store;
import java.util.Arrays;
import java.util.List;

public class StaticData {
    private final static String ZONEA="ZoneA";
    private final static String ZONEB="ZoneB";
    private final static String ZONEC="ZoneC";

    static List<DistanceMap> distanceMap= Arrays.asList(new DistanceMap(ZONEA,ZONEA,0),
                                                  new DistanceMap(ZONEA,ZONEB,3),
                                                  new DistanceMap(ZONEA,ZONEC,6),
                                                  new DistanceMap(ZONEB,ZONEC,3),
                                                  new DistanceMap(ZONEB,ZONEB,0),
                                                  new DistanceMap(ZONEC,ZONEC,0));

    static List<Store> stores=Arrays.asList(new Store("1",ZONEA,Arrays.asList("Milk","Eggs",
                                                                              "Bread")),
                                            new Store("2",ZONEB,Arrays.asList("Bread","Milk")),
                                            new Store("3",ZONEC,Arrays.asList("Juice","Bread")));

    public static List<Store> mystore = Arrays.asList(
            new Store("Store A", Arrays.asList(
                    new Item("Milk", 20, 17),
                    new Item("Bread", 30, 26),
                    new Item("Jam", 45, 44))),
                    new Store("Store B", Arrays.asList(
                            new Item("Fruits", 50, 30),
                            new Item("Shampoo", 40, 36))
            ));

    static List<Item> items=Arrays.asList(new Item("1", "Notebook", "", 15),
                                          new Item("2","Keyboard", "",50),
                                          new Item("3","Mouse", "",25),
                                          new Item("4","Monitor", "",75));

    public static List<Store> getStores() {
        return stores;
    }

    public static List<Item> getItems() {
        return items;
    }

    public static List<DistanceMap> getDistanceMap() {
        return distanceMap;
    }



}
