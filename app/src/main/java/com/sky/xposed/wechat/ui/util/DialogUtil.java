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

package com.sky.xposed.wechat.ui.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sky.xposed.common.ui.util.LayoutUtil;
import com.sky.xposed.common.ui.util.ViewUtil;
import com.sky.xposed.common.util.Alog;
import com.sky.xposed.common.util.DisplayUtil;
import com.sky.xposed.wechat.BuildConfig;

/**
 * Created by sky on 2018/12/21.
 */
public class DialogUtil {

    private DialogUtil() {

    }

    public static void showMessage(Context context, String message) {
        showMessage(context, "提示", message, "确定", null, true);
    }

    public static void showMessage(Context context, String title,
                                   String message, String positiveText, DialogInterface.OnClickListener listener, boolean cancel) {

        if (context == null
                || TextUtils.isEmpty(message)
                || TextUtils.isEmpty(positiveText)) {
            // 不进行处理
            return;
        }

        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(cancel)
                .setPositiveButton(positiveText, listener)
                .create();

        // 显示提示
        dialog.show();
    }

    public static void showDialog(Context context, String title, String message, DialogInterface.OnClickListener positiveLister) {
        showDialog(context, title, message, "确定", positiveLister, "取消", null, true);
    }

    public static void showDialog(Context context, String title, String message,
                                  String positive, DialogInterface.OnClickListener positiveLister,
                                  String negative, DialogInterface.OnClickListener negativeLister, boolean cancel) {

        if (context == null
                || TextUtils.isEmpty(message)
                || TextUtils.isEmpty(positive)
                || TextUtils.isEmpty(negative)) {
            // 不进行处理
            return;
        }

        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(cancel)
                .setPositiveButton(positive, positiveLister)
                .setNegativeButton(negative, negativeLister)
                .create();

        // 显示提示
        dialog.show();
    }

    /**
     * 显示编辑的Dialog提示框
     */
    public static void showEditDialog(Context context, String title,
                                      String content, String contentHint, final OnEditListener listener) {

        int top = DisplayUtil.dip2px(context, 20f);
        int left = DisplayUtil.dip2px(context, 26f);

        FrameLayout frameLayout = new FrameLayout(context);
        frameLayout.setLayoutParams(LayoutUtil.newFrameLayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        frameLayout.setPadding(left, top, left, 0);

        int padding = DisplayUtil.dip2px(context, 5);
        final EditText editText = new EditText(context);
        editText.setPadding(padding, padding, padding, padding);
        editText.setTextSize(15);
        editText.setText(content);
        editText.setLayoutParams(LayoutUtil.newViewGroupParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        editText.setSingleLine(true);
        editText.setFilters(new InputFilter[]{ new InputFilter.LengthFilter(10)});
        editText.setHint(contentHint);
        ViewUtil.setInputType(editText, com.sky.xposed.common.Constant.InputType.TEXT);
        frameLayout.addView(editText);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setView(frameLayout);
        builder.setPositiveButton("确定", (dialog, which) -> {
            // 返回文本的内容
            listener.onTextChange(editText, editText.getText().toString());
        });
        builder.setNegativeButton("取消", null);
        builder.show();
    }

    public static void showAboutDialog(Context context) {

        try {
            int left = DisplayUtil.dip2px(context, 25f);
            int top = DisplayUtil.dip2px(context, 10f);

            LinearLayout.LayoutParams contentParams = LayoutUtil.newMatchLinearLayoutParams();

            LinearLayout content = new LinearLayout(context);
            content.setLayoutParams(contentParams);
            content.setOrientation(LinearLayout.VERTICAL);
            content.setBackgroundColor(Color.WHITE);
            content.setPadding(left, top, left, 0);

            TextView tvHead = new TextView(context);
            tvHead.setTextColor(Color.BLACK);
            tvHead.setTextSize(14f);
            tvHead.setText("\n当前版本：v" + BuildConfig.VERSION_NAME);

//            ImageView ivCommunity = new ImageView(context);
//            ivCommunity.setLayoutParams(LayoutUtil.newWrapLinearLayoutParams());
//            Picasso.get().load(resourceIdToUri(R.drawable.community)).into(ivCommunity);

            TextView tvTail = new TextView(context);
            tvTail.setTextColor(Color.BLACK);
            tvTail.setTextSize(14f);
//            tvTail.setText("官方QQ群：\n794327446(已满)\n824933593\n");

            content.addView(tvHead);
//            content.addView(ivCommunity);
            content.addView(tvTail);

            // 显示关于
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("关于");
            builder.setView(content);
            builder.setPositiveButton("确定", (dialog, which) -> dialog.dismiss());
            builder.show();
        } catch (Throwable tr) {
            Alog.e("异常了", tr);
        }
    }

    public interface OnEditListener {

        void onTextChange(View view, String value);
    }
}
