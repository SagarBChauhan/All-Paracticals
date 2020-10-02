package com.allpracticals;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

public class PermissionFragment extends Fragment {
    public static final String TAG = "PermissionFragment";
    private static final int REQUEST_LOCATION = 0;
    private static final int REQUEST_STORAGE = 1;
    private static final int REQUEST_CAMERA = 2;
    private static final int REQUEST_ALL = 3;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "checkSelfPermission: ALL");
            requestAllPermissions();
        }
        Log.i(TAG, "->: ACCESS_FINE_LOCATION");
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
        Log.i(TAG, "->: READ_EXTERNAL_STORAGE/WRITE_EXTERNAL_STORAGE");
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
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
        Log.i(TAG, "->: CAMERA");
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_permission, container, false);
    }

    private void requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {
            Log.i(TAG, "shouldShowRequestPermissionRationale: ACCESS_FINE_LOCATION");
            Snackbar.make(requireActivity().findViewById(R.id.permission_fragment), "Location permission", Snackbar.LENGTH_INDEFINITE)
                    .setAction(getString(R.string.positive_button), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
                        }
                    })
                    .show();
        } else {
            Log.i(TAG, "else shouldShowRequestPermissionRationale: ACCESS_FINE_LOCATION");
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        }
    }

    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) || ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Log.i(TAG, "shouldShowRequestPermissionRationale: READ_EXTERNAL_STORAGE/WRITE_EXTERNAL_STORAGE");
            Snackbar.make(requireActivity().findViewById(R.id.permission_fragment), "Storage permission required", Snackbar.LENGTH_INDEFINITE)
                    .setAction(getString(R.string.positive_button), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_STORAGE);
                        }
                    }).show();
        } else {
            Log.i(TAG, "else shouldShowRequestPermissionRationale: READ_EXTERNAL_STORAGE/WRITE_EXTERNAL_STORAGE");
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_STORAGE);
        }
    }

    private void requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA)) {
            Log.i(TAG, "shouldShowRequestPermissionRationale: CAMERA");
            Snackbar.make(requireActivity().findViewById(R.id.permission_fragment), "CAMERA permission required", Snackbar.LENGTH_INDEFINITE)
                    .setAction(getString(R.string.positive_button), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
                        }
                    }).show();
        } else {
            Log.i(TAG, "else shouldShowRequestPermissionRationale: CAMERA");
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);
        }
    }

    private void requestAllPermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA)
                || ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) || ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                || ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)) {
            Log.i(TAG, "shouldShowRequestPermissionRationale: ALL");
            Snackbar.make(requireActivity().findViewById(R.id.permission_fragment), "CAMERA, STORAGE, LOCATION permission required", Snackbar.LENGTH_INDEFINITE)
                    .setAction(getString(R.string.positive_button), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_ALL);
                        }
                    }).show();
        } else {
            Log.i(TAG, "else shouldShowRequestPermissionRationale: ALL");
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_ALL);
        }
    }

}