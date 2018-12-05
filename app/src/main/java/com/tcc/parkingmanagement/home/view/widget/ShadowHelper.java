package com.tcc.parkingmanagement.home.view.widget;

import android.support.v4.view.ViewCompat;
import android.view.View;

/**
 * Created by admin on 2018/8/18 12:21
 * 邮箱：yaosongcai@ujifu.com
 */

public class ShadowHelper {

    public static void setShadowBgForView(View view, ShadowConfig.Builder config) {
        //关闭硬件加速
        view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        ViewCompat.setBackground(view, config.builder());
    }
}
