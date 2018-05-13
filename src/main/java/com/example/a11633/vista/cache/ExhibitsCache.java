package com.example.a11633.vista.cache;

import com.example.a11633.vista.R;
import com.example.a11633.vista.fragment.main.dummy.DummyContent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 11633 on 2018/4/18.
 */

public class ExhibitsCache {
    public static class EId{
        public int mId;
        public int rId;

        public EId(int mId, int rId) {
            this.mId = mId;
            this.rId = rId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            EId eId = (EId) o;

            if (mId != eId.mId) return false;
            return rId == eId.rId;
        }

        @Override
        public int hashCode() {
            int result = mId;
            result = 31 * result + rId;
            return result;
        }
    }
    public static final Map<Integer,List<DummyContent.DummyItem>> map = new LinkedHashMap<>();
    public static final Map<EId,String> introduction = new LinkedHashMap<>();
    protected static String  tempMsg;
    static {
        StringBuffer stringBuffer = new StringBuffer(3000);
        for (int i = 0; i < 1000; i++) {
            stringBuffer.append("123");
        }
        tempMsg = stringBuffer.toString();
        int[] resId0 = {R.mipmap.r0,R.mipmap.r1,R.mipmap.r2};
        String[] names0  = {"圣马格达兰","基督降生","现实主义"};
        map.put(3,makelist(3,resId0,names0));

        int[] resId1 = {R.mipmap.f0,R.mipmap.f1,R.mipmap.f2,R.mipmap.f3,R.mipmap.f4};
        String[] names1  = {"人首牛身带翼的神兽","斯芬克斯","汉谟拉比法典","男性躯干","萨莫特拉斯的胜利女神"};
        map.put(1,makelist(1,resId1,names1));

        int[] resId2 = {R.mipmap.b0,R.mipmap.b1,R.mipmap.b2};
        String[] names2  = {"大洪水记录板","奥杜威砍砸器","往后竖琴","沙伯提"};
        map.put(0,makelist(0,resId2,names2));

        int[] resId3 = {R.mipmap.c0,R.mipmap.c1,R.mipmap.c2,R.mipmap.c3};
        String[] names3  = {"大玉龙","缂丝八仙图轴","青釉四系鸟纽盖缸","青釉堆塑谷仓罐"};
        map.put(4,makelist(4,resId3,names3));

        int[] resId4 = {R.mipmap.a0,R.mipmap.a1,R.mipmap.a2};
        String[] names4  = {"中世纪铠甲","中世纪铠甲","舍杜与拉马苏"};
        map.put(2,makelist(2,resId4,names4));
    }

    private static List<DummyContent.DummyItem> makelist(int mId,int[] resIds,String[] names){
        ArrayList<DummyContent.DummyItem> ret = new ArrayList<>(resIds.length);
        for (int i = 0; i < resIds.length; i++) {
            DummyContent.DummyItem item = new DummyContent.DummyItem(i, resIds[i], names[i], i, resIds.length - i-1, mId);
            ret.add(item);
            //放入对应信息
            introduction.put(new EId(mId,i),tempMsg);
        }
        return ret;
    }

    public static void addItem(int mId,List<DummyContent.DummyItem> dummyItems){
        map.put(mId,dummyItems);
    }

    public static void sortByFav(int mId){
        List list = map.get(mId);
        if (list != null) {
            Collections.sort(list, DummyContent.FAVORABLE_COMPARATOR);
        }
    }

    public static void sortByHeat(int mId){
        List list = map.get(mId);
        if (list != null) {
            Collections.sort(list,DummyContent.HEAT_COMPARATOR);
        }
    }


}
