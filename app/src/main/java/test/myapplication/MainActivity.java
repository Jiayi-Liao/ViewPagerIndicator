package test.myapplication;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    private ViewPager viewpager=null;
    private MyAdapter adapter=new MyAdapter();
    private PageIndicatorAdapter Pageadapter;
    private PageIndicator pageIndicator;
    private String[] pagenames={"haha","heihei","huhu"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewpager=(ViewPager)findViewById(R.id.viewpager);
        pageIndicator=(PageIndicator)findViewById(R.id.indicator);
        LayoutInflater lf=getLayoutInflater();
        adapter.addviews(lf.inflate(R.layout.page1,null));
        adapter.addviews(lf.inflate(R.layout.page2, null));
        adapter.addviews(lf.inflate(R.layout.page3, null));
        viewpager.setAdapter(adapter);
        Pageadapter=new PageIndicatorAdapter(pagenames);
        pageIndicator.setAdapter(Pageadapter);
        pageIndicator.setViewPager(viewpager);






    }


}
