package ru.ddoorman.client.service;

import ru.ddoorman.client.model.Account;

import java.util.Optional;

public interface AccountDatastoreService {
    Optional<Account> findById(Long id);
}
