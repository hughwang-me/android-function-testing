package com.uwjx.function.deviceid;

import android.content.Context;
import android.telephony.TelephonyManager;

public class DeviceInfoUtil {

    /**
     * 获取DeviceId
     *
     * @return
     */
    public static String getDeviceId(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = tm.getDeviceId();
        if (deviceId == null) {
            return null;
        } else {
            return deviceId;
        }
    }
}
