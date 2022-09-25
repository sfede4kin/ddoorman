package ru.ddoorman.client.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import ru.ddoorman.client.model.dto.AccountDto;
import ru.ddoorman.client.model.dto.DtoUtil;
import ru.ddoorman.client.model.dto.EventDto;
import ru.ddoorman.client.service.AccountService;

@Controller
public class WebsocketController {
    private static final Logger log = LoggerFactory.getLogger(WebsocketController.class);
    public final AccountService accountService;
    public WebsocketController(AccountService accountService){
        this.accountService = accountService;
    }

    @MessageMapping("/account.{accountId}")
    @SendTo("/topic/profile.{accountId}")
    public AccountDto getAccount(@DestinationVariable String accountId) {
        log.info("account id:{}", accountId);
        var account = accountService.findById(Long.valueOf(accountId));
        log.debug(account.get().toString());
        return account.map(DtoUtil::cloneAccountToDto).get();
    }

    @MessageMapping("/event.{accountId}")
    public void sendEvent(@DestinationVariable String accountId, EventDto event){
        log.debug(event.toString());
    }
}
