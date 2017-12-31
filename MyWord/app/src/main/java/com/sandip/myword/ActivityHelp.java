package com.sandip.myword;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityHelp extends AppCompatActivity {
 int layouts[];
  int [] hint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help2);
        // layouts of all welcome sliders
        // add few more layouts if you want
        layouts = new int[]{
                R.drawable.welcome,
                R.drawable.chapter,
                R.drawable.level,
                R.drawable.home};
        hint = new int[]{
                R.string.txt_start_game,
                R.string.txt_chapter,
                R.string.txt_level,
                R.string.txt_board,
        };
        ViewPager viewPager=(ViewPager)findViewById(R.id.pager);
        MyViewPagerAdapter myViewPagerAdapter=new MyViewPagerAdapter(layouts,hint);
        viewPager.setAdapter(myViewPagerAdapter);

    }
    /**
     * View pager adapter
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;
        int [] layouts1;
        int [] hint1;


        public MyViewPagerAdapter(int [] layouts,int []hint) {
            layouts1=layouts;
            hint1=hint;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(R.layout.single_layout, container, false);
            ImageView imageView=(ImageView)view.findViewById(R.id.img_gallary);
            imageView.setBackgroundResource(layouts1[position]);
            TextView txtHint=(TextView) view.findViewById(R.id.txt_info);
            txtHint.setText(getResources().getString(hint1[position]));
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return layouts1.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}
