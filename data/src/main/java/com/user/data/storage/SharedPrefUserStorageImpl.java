package com.user.data.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.user.data.storage.models.UserAvatarModel;

public class SharedPrefUserStorageImpl implements UserStorage {

    private final String KEY_AVATAR = "user_avatar";
    private final SharedPreferences pref;


    public SharedPrefUserStorageImpl(Context context) {
        String SHARED_PREF_USER = "shared_pref_user";
        pref = context.getSharedPreferences(SHARED_PREF_USER, Context.MODE_PRIVATE);
    }

    @Override
    public UserAvatarModel getUserAvatarModel() {
        return new UserAvatarModel(pref.getString(KEY_AVATAR, ""));
    }

    @Override
    public void saveUserAvatarModel(UserAvatarModel avatar) {
        pref.edit().putString(KEY_AVATAR, avatar.getPath()).apply();
    }
}
