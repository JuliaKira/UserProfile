package com.user.data.storage;

import com.user.data.storage.models.UserAvatarModel;

public interface UserStorage {

   UserAvatarModel getUserAvatarModel();
   void saveUserAvatarModel(UserAvatarModel avatar);
}
