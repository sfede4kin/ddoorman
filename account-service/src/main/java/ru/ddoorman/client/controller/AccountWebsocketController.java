package ru.ddoorman.client.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import ru.ddoorman.client.model.dto.DtoUtil;
import ru.ddoorman.client.service.AccountDatastoreService;
import ru.ddoorman.client.service.AccountMessagingService;

@Controller
public class AccountWebsocketController {
    private static final Logger log = LoggerFactory.getLogger(AccountWebsocketController.class);
    private final AccountDatastoreService accountDatastoreService;
    private final AccountMessagingService accountMessagingService;

    public AccountWebsocketController(AccountDatastoreService accountService, AccountMessagingService accountMessagingService){
        this.accountDatastoreService = accountService;
        this.accountMessagingService = accountMessagingService;
    }

    @MessageMapping("/account.{accountId}")
    public void getAccount(@DestinationVariable String accountId, @Header("simpSessionId") String sessionId) {
        log.info("account id:{}", accountId);
        var account = accountDatastoreService.findById(Long.valueOf(accountId));
        log.debug(account.get().toString());
        accountMessagingService.send(DtoUtil.cloneAccountToDto(account.get()), sessionId);
    }
}
