package com.sweetiu.britaappsdk.btml;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;

import com.sweetiu.britaappsdk.view.TextView;
import com.sweetiu.britaappsdk.view.View;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by britalient on 2017/4/9.
 */

public class BtmlParser {

    private static final String TAG = BtmlParser.class.getSimpleName();

    public static List<View> parseFromAssets(Context context, String file) throws XmlPullParserException, IOException{
        InputStream is = context.getAssets().open(file);
        return parse(is);
    }

    public static List<View>  parse(InputStream is) throws XmlPullParserException, IOException{


        XmlPullParser parser = Xml.newPullParser(); //由android.util.Xml创建一个XmlPullParser实例
        parser.setInput(is, "UTF-8");               //设置输入流 并指明编码方式

        return parseDocument(parser);
    }

    public static List<View>  parseDocument(XmlPullParser parser) throws XmlPullParserException, IOException {

        List<View> views = null;

        int eventType = parser.getEventType();
        while (true) {
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    views = new ArrayList<>();
                    break;
                case XmlPullParser.START_TAG:
                    View view = parseTag(parser);
                    if (view != null) {
                        views.add(view);
                    }
                    break;
                case XmlPullParser.END_DOCUMENT:
                    return views;
            }
            eventType = parser.next();
        }
    }

    public static View parseTag(XmlPullParser parser) throws XmlPullParserException, IOException{
        String tagName = parser.getName();
        if (TextUtils.isEmpty(tagName)) {
            return null;
        }
        tagName = tagName.toLowerCase();

        View view;

        if (tagName.equals(BtmlDefine.TAG_VIEW)) {
            view = new View();
        }else if (tagName.equals(BtmlDefine.TAG_TEXT)) {
            view = new TextView();
        }else {
            view = new View();
        }

        view.parseBtml(parser);

        int eventType = parser.next();
        while (eventType != XmlPullParser.END_TAG) {
            if (eventType == XmlPullParser.START_TAG) {
                View cView = parseTag(parser);
                if (cView != null) {
                    view.addView(cView);
                }
            }
            eventType = parser.next();
        }

        return view;
    }

}
