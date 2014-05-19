package com.videostar.bridge.userrepo;

import java.util.Collections;
import java.util.List;

import com.videostar.api.userrepo.UserRepoConnector;
import com.videostar.api.userrepo.UserRepoDTO;

public class MemoryUserRepoConnector implements UserRepoConnector {
    private UserRepoDTO userRepoDto = new UserRepoDTO();

    public MemoryUserRepoConnector() {
        userRepoDto.setId("1");
        userRepoDto.setCode("default");
    }

    public UserRepoDTO findById(String id) {
        return userRepoDto;
    }

    public UserRepoDTO findByCode(String code) {
        return userRepoDto;
    }

    public List<UserRepoDTO> findAll() {
        return Collections.singletonList(userRepoDto);
    }
}
