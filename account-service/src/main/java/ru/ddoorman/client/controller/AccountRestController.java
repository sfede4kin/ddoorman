package ru.ddoorman.client.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.ddoorman.client.model.dto.AccountDto;
import ru.ddoorman.client.model.dto.DtoUtil;
import ru.ddoorman.client.service.AccountService;

@RestController
public class AccountRestController {

    private static final Logger log = LoggerFactory.getLogger(AccountRestController.class);
    public final AccountService accountService;

    public AccountRestController(AccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping("/api/account/{id}")
    public AccountDto getAccountById(@PathVariable(name = "id") Long id){
        var account = accountService.findById(id);

        log.debug(account.get().toString());

        return account.map(DtoUtil::cloneAccount).orElse(null);
    }

}
