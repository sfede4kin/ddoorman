package ru.ddoorman.client.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import ru.ddoorman.client.model.dto.AccountDto;

@Service
public class AccountMessagingServiceImpl implements AccountMessagingService{

    private final SimpMessagingTemplate messagingTemplate;

    private final static String DEST_RESP_PROFILE = "/queue/profile.";

    public AccountMessagingServiceImpl(SimpMessagingTemplate messagingTemplate){
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void send(AccountDto account, String sessionId) {
        messagingTemplate.convertAndSend(DEST_RESP_PROFILE + String.valueOf(account.getId()) + '-' + sessionId, account);
    }
}
