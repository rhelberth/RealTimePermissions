package com.example.realtimepermission.java;

import android.app.Activity;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Sistemas de permissÃ£o do Android 6.0
 * <p/>
 * http://developer.android.com/preview/features/runtime-permissions.html
 */
public class PermissionUtils {

    /**
     * Solicita as permissÃµes
     */
    public static boolean validate(Activity activity, int requestCode, String... permissions) {
        List<String> list = new ArrayList<String>();
        for (String permission : permissions) {
            // Valida permissÃ£o
            boolean ok = ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED;
            if (! ok ) {
                list.add(permission);
            }
        }
        if (list.isEmpty()) {
            // Tudo ok, retorna true
            return true;
        }

        // Lista de permissÃµes que falta acesso.
        String[] newPermissions = new String[list.size()];
        list.toArray(newPermissions);

        // Solicita permissÃ£o
        ActivityCompat.requestPermissions(activity, newPermissions, 1);

        return false;
    }
}