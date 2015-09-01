package test.myapplication;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by Administrator on 2015/8/26.
 */
public class PageIndicatorAdapter {

    private int mCount;
    private String[] datanames;

    public PageIndicatorAdapter(String[] datanames) {
        this.datanames = datanames;
        mCount=datanames.length;
    }


    public int getCount() {
        return mCount;
    }

    public String getItem(int position) {
        return datanames[position];
    }

}
