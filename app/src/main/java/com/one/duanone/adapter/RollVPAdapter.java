package com.one.duanone.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * 1、添加 删除视图和添加视图的两个方法 2、添加一个构造方法
 */
public class RollVPAdapter extends PagerAdapter {

    List<ImageView> list;
    Context context;
    /*
     * 拿到fragment中的一个处理消息的handler对象
     *
     * 我们在fragment中通过new的方式创建了一个Fragment对象，
     * 那么该对象就存在我们内存中，
     * 然后我们通过传值的方式把这个handler对象传到该adapter
     * 类中，虽然对象在不同类中使用，但是在内存中还是那个
     * 对象
     */

    public RollVPAdapter(List<ImageView> list, Context context) {
        super();
        this.list = list;
        this.context = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(list.get(position));
        return list.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        // TODO Auto-generated method stub
        return arg0 == arg1;
    }

}
