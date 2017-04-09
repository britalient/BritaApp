package com.sweetiu.britaappsdk.view;

import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.sweetiu.britaappsdk.btml.BtmlDefine;
import com.sweetiu.britaappsdk.resource.Texture;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

/**
 * Created by britalient on 2017/4/9.
 */

public class View {

    //direction
    public static final int DIRECTION_ROW = 0;
    public static final int DIRECTION_COLUMN = 1;

    //wrap
    public static final int WRAP_NO_WRAP = 0;   //超出
    public static final int WRAP_WRAP = 1;      //不超出

    private ArrayList<View> subViews;

    //layout
    private int direction = DIRECTION_COLUMN;
    private int wrap = WRAP_NO_WRAP;

    //style
    private Texture background;

    public void render(android.view.ViewGroup parent) {

        android.view.View view = renderSelf(parent);
        if (view==null) {
            return;
        }

        renderContent(view);

        renderStyle(view);

        parent.addView(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    protected android.view.View renderSelf(android.view.ViewGroup parent) {
        LinearLayout view = new LinearLayout(parent.getContext());
        view.setOrientation(direction==DIRECTION_ROW?LinearLayout.HORIZONTAL:LinearLayout.VERTICAL);
        return view;
    }

    protected void renderStyle(android.view.View view) {
        if (background!=null) {
            background.applyViewBackground(view);
        }
    }

    protected void renderContent(android.view.View view) {
        if (subViews!=null && view instanceof ViewGroup) {
            for (View subView : subViews) {
                subView.render((ViewGroup)view);
            }
        }
    }


    public void addView(View view) {
        if (subViews == null) {
            subViews = new ArrayList<>();
        }
        subViews.add(view);
    }


    public void setBackgroundColor(int color) {
        background = new Texture(color);
    }

    public void setBackgroundImage(String uri) {
        background = new Texture(uri);
    }

    public void parseBtml(XmlPullParser parser) {

        for(int i = 0; i < parser.getAttributeCount() ; i ++) {
            String name = parser.getAttributeName(i);
            if(name.equals(BtmlDefine.PROPERTY_BACKGROUND)){
                String backgroundUri = parser.getAttributeValue(i);
                if (this.background == null) {
                    this.background = new Texture(backgroundUri);
                }else {
                    this.background.setUri(backgroundUri);
                }
            }else if(name.equals(BtmlDefine.PROPERTY_BGCOLOR)){
                String bgcolor = parser.getAttributeValue(i);
                if (this.background == null) {
                    this.background = new Texture(Color.parseColor(bgcolor));
                }else {
                    this.background.setColor(bgcolor);
                }
            }else if(name.equals(BtmlDefine.PROPERTY_DIRECTION)){
                String direction = parser.getAttributeValue(i);
                if (direction!=null) {
                    direction = direction.toLowerCase();
                    if (direction.equals(BtmlDefine.VALUE_DIRECTION_ROW)) {
                        this.direction = DIRECTION_ROW;
                    }else {
                        this.direction = DIRECTION_COLUMN;
                    }
                }
            }
        }

    }

}
