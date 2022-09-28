package ru.ddoorman.door.component;

import org.springframework.stereotype.Component;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DoorSessionComponentImpl implements DoorSessionComponent{

    private final ConcurrentHashMap<String, Long> doorSessionMap = new ConcurrentHashMap<>();

    public void put(String sessionId, Long doorId){
        doorSessionMap.put(sessionId, doorId);
    }

    public void remove(String sessionId){
        doorSessionMap.remove(sessionId);
    }

    public boolean containsValue(Long doorId){
        return doorSessionMap.containsValue(doorId);
    }

    public String toString(){
        return doorSessionMap.toString();
    }

}
