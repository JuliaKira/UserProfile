package com.user.domain.usecase;

import com.user.domain.models.UserAvatar;
import com.user.domain.repository.UserRepository;

public class SavePathUserAvatarUseCase {
    private final UserRepository userRepository;

    public SavePathUserAvatarUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(UserAvatar avatar){
        userRepository.saveUserAvatar(avatar);
    }
}
