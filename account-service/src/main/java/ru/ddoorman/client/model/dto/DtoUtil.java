package ru.ddoorman.client.model.dto;

import ru.ddoorman.client.model.Account;
import ru.ddoorman.client.model.Key;
import ru.ddoorman.client.model.KeyGroup;

import java.util.HashSet;
import java.util.Set;

public class DtoUtil {
    public static AccountDto cloneAccount(Account account){
        return new AccountDto(account.getId(), account.getName(), account.getAddress(), account.getPhone(),
                account.getKeyGroupId(), cloneKeyDtoFromKeyGroups(account.getKeyGroups()));
    }

    public static KeyDto cloneKey(Key key){
        return new KeyDto(key.getId(), key.getType());
    }

    public static Set<KeyDto> cloneKeyDtoFromKeyGroups(Set<KeyGroup> keyGroups){
        var keysDto = new HashSet<KeyDto>();
        keyGroups.forEach( k -> keysDto.add(cloneKey(k.getKey())));
        return keysDto;
    }
}
