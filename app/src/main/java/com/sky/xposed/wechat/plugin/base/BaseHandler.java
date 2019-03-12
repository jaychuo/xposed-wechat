/*
 * Copyright (c) 2019 The sky Authors.
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

package com.sky.xposed.wechat.plugin.base;

import android.content.Context;
import android.os.Handler;
import android.view.View;

import com.sky.xposed.common.util.ResourceUtil;
import com.sky.xposed.javax.XposedUtil;
import com.sky.xposed.wechat.plugin.interfaces.XConfig;
import com.sky.xposed.wechat.plugin.interfaces.XHandler;
import com.sky.xposed.wechat.plugin.interfaces.XPluginManager;

import de.robv.android.xposed.XposedHelpers;

/**
 * Created by sky on 2018/9/24.
 */
public abstract class BaseHandler implements XHandler {

    private final XPluginManager mPluginManager;
    private final Context mContext;
    private final Handler mHandler;
    private final XConfig mXConfig;

    public BaseHandler(XPluginManager pluginManager) {
        mPluginManager = pluginManager;
        mContext = mPluginManager.getContext();
        mHandler = mPluginManager.getHandler();
        mXConfig = mPluginManager.getVersionManager().getSupportConfig();
    }

    public Context getContext() {
        return mContext;
    }

    public XPluginManager getPluginManager() {
        return mPluginManager;
    }

    public View findViewById(View view, String id) {

        if (view == null) return null;

        return view.findViewById(ResourceUtil.getId(mContext, id));
    }

    public void mainPerformClick(final View viewGroup, final String id) {

        mHandler.post(() -> performClick(viewGroup, id));
    }

    public void mainPerformClick(final View view) {

        mHandler.post(() -> performClick(view));
    }

    public void performClick(View viewGroup, String id) {

        if (viewGroup == null) return;

        Context context = viewGroup.getContext();
        performClick(viewGroup.findViewById(ResourceUtil.getId(context, id)));
    }

    public void performClick(View view) {

        if (view != null && view.isShown()) {
            // 点击
            view.performClick();
        }
    }

    public XConfig getXConfig() {
        return mXConfig;
    }

    public String getXString(int key) {
        return getXConfig().get(key);
    }

    public Object getObjectField(Object obj, String fieldName) {
        return XposedHelpers.getObjectField(obj, fieldName);
    }

    public Object getObjectField(Object obj, int fieldName) {
        return getObjectField(obj, getXString(fieldName));
    }

    public Class findClass(String className) {
        return XposedUtil.findClass(className);
    }

    public Class findClass(int className) {
        return findClass(getXString(className));
    }
}
