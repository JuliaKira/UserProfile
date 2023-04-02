package com.user.data.repository;


import com.user.data.storage.UserStorage;
import com.user.data.storage.models.UserAvatarModel;
import com.user.domain.models.UserAvatar;
import com.user.domain.repository.UserRepository;

public class UserRepositoryImpl implements UserRepository {

    private final UserStorage userStorage;

    public UserRepositoryImpl(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    @Override
    public UserAvatar getUserAvatar() {
        return new UserAvatar(userStorage.getUserAvatarModel().getPath());
    }

    @Override
    public void saveUserAvatar(UserAvatar avatar) {
    userStorage.saveUserAvatarModel(new UserAvatarModel(avatar.getPath()));
    }
}
