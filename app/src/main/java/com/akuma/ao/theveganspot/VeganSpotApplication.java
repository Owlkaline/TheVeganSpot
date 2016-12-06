package com.akuma.ao.theveganspot;

import android.app.Application;
import android.os.UserManager;
import android.widget.Toast;

import io.realm.ObjectServerError;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.SyncConfiguration;
import io.realm.SyncCredentials;
import io.realm.SyncUser;

/**
 * Created by akuma on 6/12/16.
 */

public class VeganSpotApplication extends Application {
    public static String AUTH_URL = "http://" + BuildConfig.OBJECT_SERVER_IP + ":9080/auth";
    public static String REALM_URL = "realm://" + BuildConfig.OBJECT_SERVER_IP + ":9080/~/realmVegan";

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
      /*  SyncCredentials creds = SyncCredentials.usernamePassword("FoodList", "default", false);
        SyncUser user = SyncUser.login(creds, VeganSpotApplication.AUTH_URL);
        SyncConfiguration defaultConfig = new SyncConfiguration.Builder(user, VeganSpotApplication.REALM_URL).build();
        Realm.setDefaultConfiguration(defaultConfig);*/

        SyncUser.loginAsync(SyncCredentials.usernamePassword("FoodList", "default", true), AUTH_URL, new SyncUser.Callback() {
            @Override
            public void onSuccess(SyncUser user) {
                SyncConfiguration sC = new SyncConfiguration.Builder(user, REALM_URL).build();
                Realm.setDefaultConfiguration(sC);
            }

            @Override
            public void onError(ObjectServerError error) {
                String errorMsg;
                switch (error.getErrorCode()) {
                    case EXISTING_ACCOUNT: errorMsg = "Account already exists"; break;
                    default:
                        errorMsg = error.toString();
                }
            }
        });
    }
}
