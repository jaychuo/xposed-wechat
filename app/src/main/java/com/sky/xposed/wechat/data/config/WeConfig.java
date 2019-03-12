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

package com.sky.xposed.wechat.data.config;

import android.text.TextUtils;
import android.util.SparseArray;

import com.sky.xposed.wechat.plugin.interfaces.XConfig;

/**
 * Created by sky on 2019/1/14.
 */
public abstract class WeConfig implements XConfig {

    private SparseArray<Object> mConfig = new SparseArray<>();

    public WeConfig() {
        // 加载配置
        loadConfig();
    }

    /**
     * 加载配置
     */
    protected abstract void loadConfig();

    protected void add(int key, String value) {
        mConfig.append(key, value);
    }

    protected void add(int key, boolean value) {
        mConfig.append(key, value);
    }

    protected void add(int key, int value) {
        mConfig.append(key, value);
    }

    @Override
    public String get(int key) {
        return (String) mConfig.get(key);
    }

    @Override
    public boolean getBoolean(int key) {
        return (boolean) mConfig.get(key);
    }

    @Override
    public int getInt(int key) {
        return (int) mConfig.get(key);
    }

    @Override
    public boolean has(int key) {
        return !TextUtils.isEmpty(get(key));
    }
}
