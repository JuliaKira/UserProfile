package com.user.userprofile;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.user.data.repository.UserRepositoryImpl;
import com.user.data.storage.SharedPrefUserStorageImpl;
import com.user.domain.models.UserAvatar;
import com.user.domain.usecase.GetPathUserAvatarUseCase;
import com.user.domain.usecase.SavePathUserAvatarUseCase;

public class MainActivity extends AppCompatActivity {


    private ImageView userAvatar;
    private Uri image_uri;

    @SuppressLint("SetTextI18n")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView user = findViewById(R.id.user_name);
        Button buttonAvatar = findViewById(R.id.buttonAvatar);
        userAvatar = findViewById(R.id.avatar);

        GetPathUserAvatarUseCase userAvatarPath = new GetPathUserAvatarUseCase(new UserRepositoryImpl(
                new SharedPrefUserStorageImpl(this)));
        Picasso.get()
                .load(Uri.parse(userAvatarPath.execute().getPath()))
                .resize(120, 120)
                .into(userAvatar);
        user.setText(userAvatarPath.execute().getPath());
        ActivityResultLauncher<Intent> launcher = getUserAvatar();

        buttonAvatar.setOnClickListener(v -> {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            launcher.launch(galleryIntent);
        });

    }


    public String getPath(Uri uri) {
        if (uri == null) {
            return null;
        }
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = this.getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(column_index);
            cursor.close();
            return path;
        }
        return uri.getPath();
    }


    private ActivityResultLauncher<Intent> getUserAvatar() {
        final ActivityResultLauncher<Intent> launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK
                            && result.getData() != null) {
                        image_uri = result.getData().getData();
                        String pathString = getPath(image_uri);
                        Picasso.get()
                                .load(image_uri)
                                .resize(120, 120)
                                .into(userAvatar);
                        SavePathUserAvatarUseCase path = new SavePathUserAvatarUseCase(new UserRepositoryImpl(
                                new SharedPrefUserStorageImpl(this)));
                        path.execute(new UserAvatar(pathString));
                    }
                });
        return launcher;
    }

}
