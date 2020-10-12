package com.allpracticals;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import com.allpracticals.mojo.MultipleResource;
import com.allpracticals.mojo.User;
import com.allpracticals.mojo.UserList;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitActivity extends AppCompatActivity {

    private static final String TAG = "RetrofitActivity";
    @BindView(R.id.responseText)
    AppCompatTextView responseText;
    APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        init();
        checkPermissionsUsingTed();
        getListResources();
        createNewUser();
        getListUser();
        postNameAndJobUrl();
    }

    private void init() {
        ButterKnife.bind(this);
        apiInterface = APIClient.getClient().create(APIInterface.class);
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
                .setPermissions(Manifest.permission.INTERNET)
                .check();
    }

    private void getListResources() {
        Call<MultipleResource> call = apiInterface.doLGetListResources();
        call.enqueue(new Callback<MultipleResource>() {
            @Override
            public void onResponse(@NotNull Call<MultipleResource> call, @NotNull Response<MultipleResource> response) {
                Log.d("TAG", response.code() + "");
                StringBuilder displayResponse = new StringBuilder();

                MultipleResource resource = response.body();
                assert resource != null;
                Integer text = resource.page;
                Integer total = resource.total;
                Integer totalPages = resource.totalPages;
                List<MultipleResource.Datum> datumList = resource.data;

                displayResponse.append(text).append(" Page\n").append(total).append(" Total\n").append(totalPages).append(" Total Pages\n");

                for (MultipleResource.Datum datum : datumList) {
                    displayResponse.append(datum.id).append(" ").append(datum.name).append(" ").append(datum.pantoneValue).append(datum.year).append("\n");
                }
                responseText.setText(displayResponse.toString());
            }

            @Override
            public void onFailure(@NotNull Call<MultipleResource> call, @NotNull Throwable t) {
                call.cancel();
            }
        });
    }

    private void createNewUser() {
        com.allpracticals.mojo.User user = new User("morpheus", "leader");
        Call<com.allpracticals.mojo.User> call = apiInterface.createUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NotNull Call<User> call, @NotNull Response<User> response) {
                User user1 = response.body();
                assert user1 != null;
                Toast.makeText(RetrofitActivity.this, user1.name + " " + user1.job + " " + user1.id + " " + user1.createdAt, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NotNull Call<User> call, @NotNull Throwable t) {
                call.cancel();
            }
        });
    }

    private void getListUser() {
        Call<UserList> userListCall = apiInterface.doGetUserList("2");
        userListCall.enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(@NotNull Call<UserList> call, @NotNull Response<UserList> response) {
                UserList userList = response.body();
                assert userList != null;
                Integer text = userList.page;
                Integer total = userList.total;
                Integer totalPages = userList.totalPages;
                List<UserList.Datum> datumList = userList.data;

                Toast.makeText(RetrofitActivity.this, text + " page\n" + total + " total\n" + totalPages + " totalPages\n", Toast.LENGTH_SHORT).show();

                for (UserList.Datum datum : datumList) {
                    Toast.makeText(RetrofitActivity.this, "id: " + datum.id + " first name:" + datum.first_name + " last name: " + datum.last_name + " avatar: " + datum.avatar, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<UserList> call, @NotNull Throwable t) {

            }
        });
    }

    private void postNameAndJobUrl() {
        Call<UserList> userListCall = apiInterface.doCreateUserWithField("morpheus", "leader");
        userListCall.enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(@NotNull Call<UserList> call, @NotNull Response<UserList> response) {
                UserList userList = response.body();
                assert userList != null;
                Integer text =  userList.page;
                Integer total = userList.total;
                Integer totalPages = userList.totalPages;
                List<UserList.Datum> datumList = userList.data;
                Toast.makeText(RetrofitActivity.this, text + " page\n" + total + " total\n" + totalPages + " totalPages\n", Toast.LENGTH_SHORT).show();

                for (UserList.Datum datum : datumList) {
                    Toast.makeText(RetrofitActivity.this, "id : " + datum.id + " name: " + datum.first_name + " " + datum.last_name + " avatar: " + datum.avatar, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<UserList> call, @NotNull Throwable t) {
                call.cancel();
            }
        });
    }
}