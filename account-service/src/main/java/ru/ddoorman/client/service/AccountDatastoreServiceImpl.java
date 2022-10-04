package ru.ddoorman.client.service;

import org.springframework.stereotype.Service;
import ru.ddoorman.client.model.Account;
import ru.ddoorman.client.repository.AccountRepository;
import java.util.Optional;

@Service
public class AccountDatastoreServiceImpl implements AccountDatastoreService {

    private final AccountRepository accountRepository;

    public AccountDatastoreServiceImpl(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Override
    public Optional<Account> findById(Long id){
        return accountRepository.findById(id);
    }
}
