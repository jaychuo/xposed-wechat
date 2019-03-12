/*
 * Copyright (c) 2018 The sky Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sky.xposed.wechat.ui.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.sky.xposed.common.util.Alog;
import com.sky.xposed.common.util.ToastUtil;


/**
 * Created by sky on 18-3-12.
 */

public class ActivityUtil {

    public static boolean startActivity(Context context, Intent intent) {

        try {
            // 获取目标包名
            String packageName = intent.getPackage();

            // 设置启动参数
            if (!TextUtils.isEmpty(packageName)
                    && !TextUtils.equals(packageName, context.getPackageName())) {
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }

            // 启动Activity
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            Alog.e("启动Activity异常", e);
        }
        return false;
    }

    public static boolean openUrl(Context context, String url) {

        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));

            // 调用系统浏览器打开
            context.startActivity(intent);
            return true;
        } catch (Throwable tr) {
            ToastUtil.show("打开浏览器异常");
        }
        return false;
    }
}
