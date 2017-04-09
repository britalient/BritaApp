package com.sweetiu.britaappsdk.resource;

import android.graphics.Color;
import android.text.TextUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.sweetiu.britaappsdk.images.BackgroundViewAware;

/**
 * Created by britalient on 2017/4/9.
 */

public class Texture {

    private int color;
    private String uri;

    public Texture(int color) {
        this.color = color;
    }

    public Texture(String uri) {
        this.uri = uri;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setColor(String color) {
        this.color = Color.parseColor(color);
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void applyViewBackground(android.view.View view) {

        if (!TextUtils.isEmpty(uri)) {
            ImageLoader.getInstance().displayImage(uri, new BackgroundViewAware(view, ViewScaleType.FIT_INSIDE));
            return;
        }

        view.setBackgroundColor(color);

    }

}
