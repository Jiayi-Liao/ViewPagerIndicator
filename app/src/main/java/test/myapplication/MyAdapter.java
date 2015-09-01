package test.myapplication;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/22.
 */
public class MyAdapter extends PagerAdapter {

    private ArrayList<View> views=new ArrayList<View>();


    public void addviews(View v){
        views.add(v);
    }
    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)   {
        container.removeView(views.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container,int position){
        container.addView(views.get(position));
        return views.get(position);
    }


}
