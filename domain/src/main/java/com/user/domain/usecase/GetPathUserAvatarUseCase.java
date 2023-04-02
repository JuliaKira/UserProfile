package com.user.domain.usecase;


import com.user.domain.models.UserAvatar;
import com.user.domain.repository.UserRepository;

public class GetPathUserAvatarUseCase {
    private final UserRepository userRepository;

    public GetPathUserAvatarUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public UserAvatar execute(){
        return userRepository.getUserAvatar();
    }
}
