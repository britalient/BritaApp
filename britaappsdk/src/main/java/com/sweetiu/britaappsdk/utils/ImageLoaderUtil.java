package com.sweetiu.britaappsdk.utils;

import android.content.Context;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by britalient on 2017/4/9.
 */

public class ImageLoaderUtil {

    public static void initImageLoader(Context context) {

        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                //.showImageOnLoading(R.color.w)
                //.showImageOnFail(R.drawable.error_icon)
                //.showImageForEmptyUri(R.drawable.error_icon)
                .build();

        int width = context.getResources().getDisplayMetrics().widthPixels *2;
        int height = context.getResources().getDisplayMetrics().heightPixels *2;

        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(
                context.getApplicationContext()).threadPoolSize(10)
                .defaultDisplayImageOptions(defaultOptions)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .memoryCacheExtraOptions(width, height)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheExtraOptions(width, height, null)
                .diskCacheFileCount(200).diskCacheSize(200 * 1024 * 1024)
                //.memoryCacheSize(3 * 1024 * 1024)
                .memoryCache(new WeakMemoryCache()).build();
        ImageLoader.getInstance().init(configuration);
    }

}
