package ru.ddoorman.client.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import ru.ddoorman.client.model.dto.AccountDto;
import ru.ddoorman.client.model.dto.DtoUtil;
import ru.ddoorman.client.service.AccountService;

@Controller
public class AccountWebsocketController {
    private static final Logger log = LoggerFactory.getLogger(AccountWebsocketController.class);
    private final AccountService accountService;
    private final SimpMessagingTemplate messagingTemplate;

    private final static String DEST_RESP_PROFILE = "/queue/profile.";
    public AccountWebsocketController(AccountService accountService, SimpMessagingTemplate messagingTemplate){
        this.accountService = accountService;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/account.{accountId}")
    public void getAccount(@DestinationVariable String accountId, @Header("simpSessionId") String sessionId) {
        log.info("account id:{}", accountId);
        var account = accountService.findById(Long.valueOf(accountId));
        log.debug(account.get().toString());
        messagingTemplate.convertAndSend(DEST_RESP_PROFILE + accountId + '-' + sessionId, DtoUtil.cloneAccountToDto(account.get()));
    }
}
