package com.videostar.api.userrepo;

import com.videostar.api.userrepo.UserRepoDTO;

public interface UserRepoCache {
    UserRepoDTO findById(String id);

    UserRepoDTO findByCode(String code);

    void updateUserRepo(UserRepoDTO userRepoDto);

    void removeUserRepo(UserRepoDTO userRepoDto);
}
