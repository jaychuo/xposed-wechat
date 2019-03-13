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

package com.sky.xposed.wechat;

/**
 * Created by sky on 18-3-10.
 */

public interface Constant {

    interface WeChat {

        String PACKAGE_NAME = "com.tencent.mm";
    }

    interface Event {

        int CLICK = 0x01;
    }

    interface Color {

        int BLUE = 0xFF393A3F;

        int TOOLBAR = 0xff303030;

        int TITLE = 0xff004198;

        int DESC = 0xff303030;
    }

    interface GroupId {

        int GROUP = 999;
    }

    interface Name {

        String TITLE = "WeCat";

        String WE_CHAT = "wechat";
    }

    interface XFlag {

        int MAIN_MENU = 0x000001;

        int AUTO_LOGIN = 0x000002;
    }

    interface Preference {

        String MAIN_MENU = "main.menu";

        String AUTO_LOGIN = "other.autoLogin";

        String ACTIVITY_CYCLE = "develop.activityCycle";

        String ACTIVITY_START = "develop.activityStart";

        String ACTIVITY_RESULT = "develop.activityResult";

        String WECHAT_LOG = "develop.wechatLog";
    }

    interface Flag {

        int MAIN = 0xFF000000;
    }

    interface Plugin {

        int MAIN_SETTINGS = 0x00000000;

        int DEBUG = 0x01000000;

        int COMMON = 0x02000000;

        int LUCKY_MONEY = 0x03000000;

        int REMITTANCE = 0x04000000;
    }

    interface ItemId {

        int MAIN_SETTINGS = 60001;
    }

    interface Key {

        String DATA = "data";
    }
}
