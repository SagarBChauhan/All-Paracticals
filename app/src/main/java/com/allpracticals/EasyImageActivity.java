package com.allpracticals;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;
import pl.aprilapps.easyphotopicker.MediaFile;
import pl.aprilapps.easyphotopicker.MediaSource;

public class EasyImageActivity extends AppCompatActivity {
    private static final String TAG = "EasyImageActivity";
    @BindView(R.id.iv_avatar)
    AppCompatImageView iv_avatar;
    @BindView(R.id.btn_upload)
    AppCompatButton btn_upload;
    EasyImage easyImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_image);
        init();
    }

    private void init() {
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_upload)
    public void onClickUpload(View view) {
        checkPermissionsUsingTed();
        selectImageUsingEasyImage();
    }

    private void checkPermissionsUsingTed() {
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Log.i(TAG, getString(R.string.msg_permission_granted));
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Log.i(TAG, getString(R.string.msg_permission_denied) + deniedPermissions.toString());
            }
        };
        TedPermission.with(this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage(R.string.msg_permission_denied_msg)
                .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .check();
    }

    private void selectImageUsingEasyImage() {
        CharSequence[] options = {getString(R.string.option_capture_image), getString(R.string.option_choose_image), getString(R.string.option_open_chooser)};

        easyImage = new EasyImage.Builder(this)
                .setCopyImagesToPublicGalleryFolder(false)
                .setFolderName("EasyImage sample")
                .allowMultiple(true)
                .build();

        new AlertDialog.Builder(this).setTitle(getString(R.string.option_choose_image))
                .setItems(options, (dialog, which) -> {
                    if (options[which].equals(getString(R.string.option_capture_image))) {
                        easyImage.openCameraForImage(this);
                    } else if (options[which].equals( getString(R.string.option_choose_image))) {
                        easyImage.openGallery(this);
                    } else if (options[which].equals(getString(R.string.option_open_chooser))) {
                        easyImage.openChooser(this);
                    }
                }).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        easyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
            @Override
            public void onMediaFilesPicked(@NotNull MediaFile[] imageFiles, @NotNull MediaSource source) {
                iv_avatar.setImageURI(Uri.fromFile(imageFiles[0].getFile()));
            }

            @Override
            public void onImagePickerError(@NonNull Throwable error, @NonNull MediaSource source) {
                Toast.makeText(EasyImageActivity.this, getString(R.string.msg_error_occurred)+ error.getMessage(), Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }

            @Override
            public void onCanceled(@NonNull MediaSource source) {
                Toast.makeText(EasyImageActivity.this, R.string.msg_canceled, Toast.LENGTH_SHORT).show();
            }
        });
    }
}