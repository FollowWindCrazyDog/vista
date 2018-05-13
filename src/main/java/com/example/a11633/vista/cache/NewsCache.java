package com.example.a11633.vista.cache;

import com.example.a11633.vista.R;
import com.example.a11633.vista.fragment.main.dummy.DummyContent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by 11633 on 2018/4/18.
 */

public class NewsCache {
    public static final List<DummyContent.DummyItem> news = new ArrayList<>();

    static {
        int[] resId = {R.mipmap.news0,R.mipmap.news1,R.mipmap.news2,R.mipmap.news3,R.mipmap.news4,};
        String[] names = {"张伯驹先生诞辰120周年纪念展","卡塔尔阿勒萨尼收藏展","哥窟瓷器展","艺术和金融同住我的胸膛"};
        String[] vals = {"http://www.dpm.org.cn/show/246487.html","http://www.dpm.org.cn/subject_althani/preface.html","http://www.artronpano.com/scene/Mq6qAsdmiMJ9MrgMWkjGMtes4pTTHGGl/gggy//tour.html","http://news.sina.com.cn/o/2018-04-18/doc-ifzfkmth5889337.shtml"};
        for (int i = 0; i < names.length; i++) {
            DummyContent.DummyItem item = new DummyContent.DummyItem(i, resId[i], names[i],i,names.length-i-1,vals[i]);
            addItem(item);
        }
    }



    public static void addItem(DummyContent.DummyItem dummyItem){
        news.add(dummyItem);
    }

    public static void sortByFav(){
        Collections.sort(news, DummyContent.FAVORABLE_COMPARATOR);
    }

    public static void sortByHeat(){
        Collections.sort(news,DummyContent.HEAT_COMPARATOR);
    }
}
