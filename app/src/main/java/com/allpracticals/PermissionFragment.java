package com.allpracticals;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

public class PermissionFragment extends Fragment {
    public static final String TAG = "PermissionFragment";
    private static final int REQUEST_LOCATION = 0;
    private static final int REQUEST_STORAGE = 1;
    private static final int REQUEST_CAMERA = 2;
    private static final int REQUEST_ALL = 3;
    private static String[] permissions;
    @BindView(R.id.iv_avatar)
    AppCompatImageView iv_avatar;
    @BindView(R.id.btn_upload)
    AppCompatButton btn_upload;
    private int CAPTURE_IMAGE_REQUEST = 0;
    private int PICK_IMAGE_REQUEST = 1;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    @OnClick(R.id.btn_upload)
    public void onClickUpload(View view) {
        checkAllPermission();
        selectImage();
    }

    private void selectImage() {
        CharSequence[] options = {"Capture Image", "Choose Image"};
        new AlertDialog.Builder(getContext()).setTitle("Choose picture")
                .setItems(options, (dialog, which) -> {
                    if (options[which].equals("Capture Image")) {
                        captureImage();
                    } else if (options[which].equals("Choose Image")) {
                        chooseImage();
                    }
                }).show();
    }

    private void captureImage() {
        Intent intent = new Intent();
//        intent.setType("image/*");
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(Intent.createChooser(intent, "Select picture"), CAPTURE_IMAGE_REQUEST);
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_ALL:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getActivity(), "Permissions granted", Toast.LENGTH_SHORT).show();
                } else {
                    Snackbar.make(requireActivity().findViewById(R.id.permission_fragment), "Permissions required to application work!", Snackbar.LENGTH_INDEFINITE)
                            .setAction(getString(R.string.positive_button), v -> ActivityCompat.requestPermissions(Objects.requireNonNull(getActivity()), new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_ALL)).show();
                }
                break;
            case REQUEST_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getActivity(), "Permissions granted", Toast.LENGTH_SHORT).show();
                } else {
                    Snackbar.make(requireActivity().findViewById(R.id.permission_fragment), "Permissions required to application work!", Snackbar.LENGTH_INDEFINITE)
                            .setAction(getString(R.string.positive_button), v -> ActivityCompat.requestPermissions(Objects.requireNonNull(getActivity()), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_ALL)).show();
                }
                break;
            case REQUEST_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getActivity(), "Permissions granted", Toast.LENGTH_SHORT).show();
                } else {
                    Snackbar.make(requireActivity().findViewById(R.id.permission_fragment), "Permissions required to application work!", Snackbar.LENGTH_INDEFINITE)
                            .setAction(getString(R.string.positive_button), v -> ActivityCompat.requestPermissions(Objects.requireNonNull(getActivity()), new String[]{Manifest.permission.CAMERA}, REQUEST_ALL)).show();
                }
                break;
            case REQUEST_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getActivity(), "Permissions granted", Toast.LENGTH_SHORT).show();
                } else {
                    Snackbar.make(requireActivity().findViewById(R.id.permission_fragment), "Permissions required to application work!", Snackbar.LENGTH_INDEFINITE)
                            .setAction(getString(R.string.positive_button), v -> ActivityCompat.requestPermissions(Objects.requireNonNull(getActivity()), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_ALL)).show();
                }
                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 0:
                if (requestCode == CAPTURE_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
                    Bitmap bitmap = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
                    iv_avatar.setImageBitmap(bitmap);
                }
                break;
            case 1:
                if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
                    Uri uri = data.getData();

                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(Objects.requireNonNull(getActivity()).getContentResolver(), uri);
                        iv_avatar.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    private void init(View view) {
        ButterKnife.bind(this, view);

        permissions = new String[]{
                Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION};
    }

    private void checkAllPermission() {
        if (ActivityCompat.checkSelfPermission(Objects.requireNonNull(getContext()), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "checkSelfPermission: ALL");
            requestAllPermissions();
        } else {
            Log.i(TAG, "else checkSelfPermission: ALL");
        }
    }

    private void requestAllPermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(Objects.requireNonNull(getActivity()), Manifest.permission.CAMERA)
                || ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) || ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                || ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {
            Log.i(TAG, "shouldShowRequestPermissionRationale: ALL");
            Snackbar.make(requireActivity().findViewById(R.id.permission_fragment), "Required some permission to run application properly.", Snackbar.LENGTH_INDEFINITE)
                    .setAction(getString(R.string.positive_button), v -> ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_ALL)).show();
        } else {
            Log.i(TAG, "else shouldShowRequestPermissionRationale: ALL");
            ActivityCompat.requestPermissions(Objects.requireNonNull(getActivity()), permissions, REQUEST_ALL);
        }
    }

    private void checkLocationPermission() {
        Log.i(TAG, "->: ACCESS_FINE_LOCATION");
        if (ActivityCompat.checkSelfPermission(Objects.requireNonNull(getContext()), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "checkSelfPermission: ACCESS_FINE_LOCATION");
//            AlertDialog.Builder builderLocation = new AlertDialog.Builder(getContext())
//                    .setTitle("Required Location permission")
//                    .setMessage("Do you want to provide permission?")
//                    .setIcon(R.drawable.ic_baseline_verified_user_24)
//                    .setPositiveButton(getString(R.string.positive_button), new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            requestLocationPermission();
//                        }
//                    })
//                    .setNegativeButton(getString(R.string.negative_button), new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            Toast.makeText(getContext(), "Permission not provided", Toast.LENGTH_SHORT).show();
//                        }
//                    })
//                    .setNeutralButton(getString(R.string.neutral_button), new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            Toast.makeText(getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//            builderLocation.show();

        } else {
            Log.i(TAG, "else checkSelfPermission: ACCESS_FINE_LOCATION");
        }
    }

    private void checkStoragePermission() {
        Log.i(TAG, "->: READ_EXTERNAL_STORAGE/WRITE_EXTERNAL_STORAGE");
        if (ActivityCompat.checkSelfPermission(Objects.requireNonNull(getContext()), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "checkSelfPermission: READ_EXTERNAL_STORAGE/WRITE_EXTERNAL_STORAGE");
//            AlertDialog.Builder builderStorage = new AlertDialog.Builder(getContext())
//                    .setTitle("Required Internal/External Storage permissions")
//                    .setMessage("Do you want to provide permission?")
//                    .setIcon(R.drawable.ic_baseline_verified_user_24)
//                    .setPositiveButton(getString(R.string.positive_button), new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            requestStoragePermission();
//                        }
//                    })
//                    .setNegativeButton(getString(R.string.negative_button), new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            Toast.makeText(getContext(), "Permission not provided", Toast.LENGTH_SHORT).show();
//                        }
//                    })
//                    .setNeutralButton(getString(R.string.neutral_button), new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            Toast.makeText(getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//
//            builderStorage.show();
        } else {
            Log.i(TAG, "else checkSelfPermission: READ_EXTERNAL_STORAGE/WRITE_EXTERNAL_STORAGE");
        }
    }

    private void checkCameraPermission() {
        Log.i(TAG, "->: CAMERA");
        if (ActivityCompat.checkSelfPermission(Objects.requireNonNull(getContext()), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "checkSelfPermission: CAMERA");
//            AlertDialog.Builder builderCamera = new AlertDialog.Builder(getContext())
//                    .setTitle("Required Camera permission")
//                    .setMessage("Do you want to provide permission?")
//                    .setIcon(R.drawable.ic_baseline_verified_user_24)
//                    .setPositiveButton(getString(R.string.positive_button), new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            requestCameraPermission();
//                        }
//                    })
//                    .setNegativeButton(getString(R.string.negative_button), new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            Toast.makeText(getContext(), "Permission not provided", Toast.LENGTH_SHORT).show();
//                        }
//                    })
//                    .setNeutralButton(getString(R.string.neutral_button), new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            Toast.makeText(getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//            builderCamera.show();

        } else {
            Log.i(TAG, "else checkSelfPermission: CAMERA");
        }
    }

    private void requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(Objects.requireNonNull(getActivity()), Manifest.permission.ACCESS_FINE_LOCATION)) {
            Log.i(TAG, "shouldShowRequestPermissionRationale: ACCESS_FINE_LOCATION");
            Snackbar.make(requireActivity().findViewById(R.id.permission_fragment), "Location permission", Snackbar.LENGTH_INDEFINITE)
                    .setAction(getString(R.string.positive_button), v -> ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION))
                    .show();
        } else {
            Log.i(TAG, "else shouldShowRequestPermissionRationale: ACCESS_FINE_LOCATION");
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }
    }

    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(Objects.requireNonNull(getActivity()), Manifest.permission.READ_EXTERNAL_STORAGE) || ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Log.i(TAG, "shouldShowRequestPermissionRationale: READ_EXTERNAL_STORAGE/WRITE_EXTERNAL_STORAGE");
            Snackbar.make(requireActivity().findViewById(R.id.permission_fragment), "Storage permission required", Snackbar.LENGTH_INDEFINITE)
                    .setAction(getString(R.string.positive_button), v -> ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_STORAGE)).show();
        } else {
            Log.i(TAG, "else shouldShowRequestPermissionRationale: READ_EXTERNAL_STORAGE/WRITE_EXTERNAL_STORAGE");
            ActivityCompat.requestPermissions(Objects.requireNonNull(getActivity()), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_STORAGE);
        }
    }

    private void requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(Objects.requireNonNull(getActivity()), Manifest.permission.CAMERA)) {
            Log.i(TAG, "shouldShowRequestPermissionRationale: CAMERA");
            Snackbar.make(requireActivity().findViewById(R.id.permission_fragment), "CAMERA permission required", Snackbar.LENGTH_INDEFINITE)
                    .setAction(getString(R.string.positive_button), v -> ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA)).show();
        } else {
            Log.i(TAG, "else shouldShowRequestPermissionRationale: CAMERA");
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_permission, container, false);
    }
}
