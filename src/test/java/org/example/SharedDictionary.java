package org.example;

import java.util.HashMap;
import java.util.Map;

public class SharedDictionary {
    private final Map<String, Object> sharedMap = new HashMap<String, Object>();

    public void addToDictionary(String key, Object value){
        sharedMap.put(key,value);
    }

    public Object readDictionary(String key){
        return sharedMap.get(key);
    }

    public boolean containsKey(String key){
        return sharedMap.containsKey(key);
    }
}
