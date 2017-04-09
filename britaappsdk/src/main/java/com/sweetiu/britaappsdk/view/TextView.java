package com.sweetiu.britaappsdk.view;

import android.graphics.Color;
import android.view.ViewGroup;

import com.sweetiu.britaappsdk.btml.BtmlDefine;
import com.sweetiu.britaappsdk.btml.BtmlParser;
import com.sweetiu.britaappsdk.resource.Texture;

import org.xmlpull.v1.XmlPullParser;

/**
 * Created by britalient on 2017/4/9.
 */

public class TextView extends View{

    private String text;

    private int color = Color.TRANSPARENT;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    @Override
    public void render(ViewGroup parent) {
        super.render(parent);
    }

    @Override
    public android.view.View renderSelf(ViewGroup parent) {
        android.widget.TextView textView = new android.widget.TextView(parent.getContext());
        return textView;
    }

    @Override
    public void renderContent(android.view.View view) {
        super.renderContent(view);

        if (text!=null) {
            android.widget.TextView textView = (android.widget.TextView)view;
            textView.setText(text);
            textView.setTextColor(color);
        }

    }

    @Override
    public void parseBtml(XmlPullParser parser) {
        super.parseBtml(parser);

        for(int i = 0; i < parser.getAttributeCount() ; i ++) {
            String name = parser.getAttributeName(i);
            if(name.equals(BtmlDefine.PROPERTY_COLOR)){
                String color = parser.getAttributeValue(i);
                this.color = Color.parseColor(color);
            }
        }


        try {
            int eventType = parser.next();
            if (eventType == XmlPullParser.TEXT) {
                String text = parser.getText();
                this.text = text;
            }
        }catch (Exception e) {

        }

    }
}
