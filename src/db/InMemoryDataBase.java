package db;

import models.Device;

import java.util.HashMap;
import java.util.Map;

public class InMemoryDataBase {

    Map< String, Device> db;

    private static final InMemoryDataBase Instance = new InMemoryDataBase();

    private InMemoryDataBase(){
        db =  new HashMap<>();
    }

    public static InMemoryDataBase getInstance(){
        return Instance;
    }

    public Map<String, Device> getDb(){
        return db;
    }
}