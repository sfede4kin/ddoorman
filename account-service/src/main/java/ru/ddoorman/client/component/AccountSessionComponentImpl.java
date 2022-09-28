package ru.ddoorman.client.component;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
public class AccountSessionComponentImpl implements AccountSessionComponent {

    private final ConcurrentHashMap<String, Long> accountSessionMap = new ConcurrentHashMap<>();

    public void put(String sessionId, Long accountId){
        accountSessionMap.put(sessionId, accountId);
    }

    public void remove(String sessionId){
        accountSessionMap.remove(sessionId);
    }

    public boolean containsValue(Long accountId){
        return accountSessionMap.containsValue(accountId);
    }

    public String toString(){
        return accountSessionMap.toString();
    }

}
