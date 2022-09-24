package ru.ddoorman.client.service;

import org.springframework.stereotype.Service;
import ru.ddoorman.client.model.Account;
import ru.ddoorman.client.repository.AccountRepository;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Override
    public Optional<Account> findById(Long id){
        return accountRepository.findById(id);
    }
}
