package com.github.tvbox.osc.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.github.tvbox.osc.bean.UpdateInfo;
import com.github.tvbox.osc.ui.dialog.UpdateDialog;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.hawk.Hawk;

public class UpdateUtils {
    private static final String UPDATE_URL = "https://gitee.com/happy_ape/box-update/raw/master/config.json";

    public static void checkUpdate(Activity activity) {
        OkGo.<String>get(UPDATE_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                try {
                    UpdateInfo updateInfo = new Gson().fromJson(response.body(), UpdateInfo.class);
                    if (updateInfo != null) {
                        if (shouldUpdate(activity, updateInfo)) {
                            showUpdateDialog(activity, updateInfo);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private static boolean shouldUpdate(Context context, UpdateInfo updateInfo) {
        try {
            int currentVersionCode = getAppVersionCode(context);
            String[] parts = updateInfo.getVersion().split("\\+");
            int remoteVersionCode = 0;
            if (parts.length > 1) {
                remoteVersionCode = Integer.parseInt(parts[1]);
            } else {
                // 如果没有+，尝试直接解析
                remoteVersionCode = Integer.parseInt(updateInfo.getVersion().replaceAll("[^0-9]", ""));
            }

            if (remoteVersionCode <= currentVersionCode) {
                return false;
            }

            if (updateInfo.isMustUpdate()) {
                return true;
            }

            long lastTime = Hawk.get(HawkConfig.UPDATE_LAST_TIME, 0L);
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastTime > 24 * 60 * 60 * 1000) {
                Hawk.put(HawkConfig.UPDATE_LAST_TIME, currentTime);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private static void showUpdateDialog(Activity activity, UpdateInfo updateInfo) {
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            return;
        }
        activity.runOnUiThread(() -> {
            UpdateDialog dialog = new UpdateDialog(activity, updateInfo);
            dialog.show();
        });
    }

    private static int getAppVersionCode(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return (int) packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
