package com.videostar.api.userrepo;

import java.util.List;

import com.videostar.api.userrepo.UserRepoDTO;

public interface UserRepoConnector {
    UserRepoDTO findById(String id);

    UserRepoDTO findByCode(String code);

    List<UserRepoDTO> findAll();
}
