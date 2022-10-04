package ru.ddoorman.client.service;

import ru.ddoorman.client.model.dto.AccountDto;

public interface AccountMessagingService {

    void send(AccountDto account, String sessionId);

}
