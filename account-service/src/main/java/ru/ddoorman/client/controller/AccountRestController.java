package ru.ddoorman.client.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.ddoorman.client.model.dto.AccountDto;
import ru.ddoorman.client.model.dto.DtoUtil;
import ru.ddoorman.client.service.AccountDatastoreService;

import java.util.NoSuchElementException;

@RestController
public class AccountRestController {

    private static final Logger log = LoggerFactory.getLogger(AccountRestController.class);
    public final AccountDatastoreService accountService;

    public AccountRestController(AccountDatastoreService accountService){
        this.accountService = accountService;
    }

    @GetMapping("/api/account/{id}")
    public AccountDto getAccountById(@PathVariable(name = "id") Long id){
        try {
            var account = accountService.findById(id);
            log.debug(account.get().toString());
            return account.map(DtoUtil::cloneAccountToDto).get();
        }catch (NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account Not Found", e);
        }
    }

}
