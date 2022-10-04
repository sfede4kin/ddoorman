package ru.ddoorman.client.test;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.ddoorman.client.repository.AccountRepository;
import ru.ddoorman.client.service.AccountDatastoreService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Testcontainers
@ActiveProfiles("test-containers-flyway")
public class AccountRepositoryTestContainersFlyway {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountDatastoreService accountService;

    @Test
    public void shouldGetAccount(){
        final var account = accountService.findById(1L);
        assertTrue(account.isPresent());
        assertEquals(account.get().getKeyGroups().size(), 2);
    }

}
