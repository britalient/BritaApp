package com.sweetiu.britaappsdk;

import android.content.Context;

import com.sweetiu.britaappsdk.utils.ImageLoaderUtil;

/**
 * Created by britalient on 2017/4/9.
 */

public class SDK {

    public static void init(Context context) {
        ImageLoaderUtil.initImageLoader(context);
    }

}
