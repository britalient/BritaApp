package com.sweetiu.britaappsdk.images;


import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.imageaware.ViewAware;
import com.nostra13.universalimageloader.utils.L;

import java.lang.reflect.Field;
/**
 * Created by britalient on 2017/4/9.
 */

public class BackgroundViewAware extends ViewAware{
        private ViewScaleType mViewScaleType;

        public BackgroundViewAware(View view, ViewScaleType siewScaleType) {
            super(view);
            mViewScaleType = siewScaleType;
        }


        public BackgroundViewAware(View view, ViewScaleType siewScaleType, boolean checkActualViewSize) {
            super(view, checkActualViewSize);
            mViewScaleType = siewScaleType;
        }

        /**
         * {@inheritDoc}
         * <br />
         * 3) Get <b>maxWidth</b>.
         */
        @Override
        public int getWidth() {
            int width = super.getWidth();
            if (width <= 0) {
                View view =  viewRef.get();
                if (view != null) {
                    width = getViewFieldValue(view, "mMaxWidth"); // Check maxWidth parameter
                }
            }
            return width;
        }

        /**
         * {@inheritDoc}
         * <br />
         * 3) Get <b>maxHeight</b>
         */
        @Override
        public int getHeight() {
            int height = super.getHeight();
            if (height <= 0) {
                View view =  viewRef.get();
                if (view != null) {
                    height = getViewFieldValue(view, "mMaxHeight"); // Check maxHeight parameter
                }
            }
            return height;
        }

        @Override
        public ViewScaleType getScaleType() {
            if (mViewScaleType != null) {
                return mViewScaleType;
            }
            return super.getScaleType();
        }

        @Override
        public View getWrappedView() {
            return super.getWrappedView();
        }

        @Override
        protected void setImageDrawableInto(Drawable drawable, View view) {
            (view).setBackground(drawable);
            if (drawable instanceof AnimationDrawable) {
                ((AnimationDrawable)drawable).start();
            }
        }

        @Override
        protected void setImageBitmapInto(Bitmap bitmap, View view) {
            (view).setBackground(new BitmapDrawable(view.getResources(), bitmap));
        }

        private static int getViewFieldValue(Object object, String fieldName) {
            int value = 0;
            try {
                Field field = View.class.getDeclaredField(fieldName);
                field.setAccessible(true);
                int fieldValue = (Integer) field.get(object);
                if (fieldValue > 0 && fieldValue < Integer.MAX_VALUE) {
                    value = fieldValue;
                }
            } catch (Exception e) {
                L.e(e);
            }
            return value;
        }
    }