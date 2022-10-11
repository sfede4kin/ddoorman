package ru.ddoorman.client.repository;

import org.springframework.data.repository.CrudRepository;
import ru.ddoorman.client.model.Account;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {

    Optional<Account> findById(Long id);

}
