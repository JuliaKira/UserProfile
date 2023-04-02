package com.user.domain.repository;

import com.user.domain.models.UserAvatar;

public interface UserRepository {

   UserAvatar getUserAvatar();
   void saveUserAvatar(UserAvatar avatar);
}
