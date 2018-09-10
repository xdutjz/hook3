package com.nds.hook3;



import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.sql.Date;
import java.sql.Time;
import java.text.*;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class XModule implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable{
        if (loadPackageParam.packageName.equals("com.nds.baiduapitest1")){
            final Class<?> clazz = XposedHelpers.findClass("com.nds.baiduapitest1.MainActivity",loadPackageParam.classLoader);
            XposedBridge.log("Load App" + loadPackageParam.packageName);
            XposedHelpers.findAndHookMethod(clazz, "onCreate", Bundle.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date curDate = new Date(System.currentTimeMillis());
                    String str = formatter.format(curDate);
                    XposedBridge.log("App no.2 activated at " + str + ".");
                    super.beforeHookedMethod(param);
                }
            });
        }
    }
}
