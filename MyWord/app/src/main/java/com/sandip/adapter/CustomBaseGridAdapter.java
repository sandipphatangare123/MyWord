package com.sandip.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sandip.myword.R;
import com.sandip.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 06-02-2017.
 */

public class CustomBaseGridAdapter extends BaseAdapter {


        private Context mContext;
        private List<String> listOfChar;
        private ArrayList<Integer> listOfInteger;
        public CustomBaseGridAdapter(Context c, List<String> listOfChar, ArrayList<Integer> listOfInt) {
            mContext = c;
            this.listOfChar = listOfChar;
            this.listOfInteger = listOfInt;

        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return listOfInteger.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return listOfInteger.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            View grid;
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (convertView == null) {

                grid = inflater.inflate(R.layout.layout_grid_single, null);

                final Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
                animation.setDuration(300); // duration - half a second
                animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
                animation.setRepeatCount(Animation.INFINITE); // Repeat animation infinitely
                animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in
                //grid.startAnimation(animation);
                RelativeLayout relItem = (RelativeLayout) grid.findViewById(R.id.rel_item);
//                relItem.startAnimation(animation);
  //              relItem.setBackgroundColor(Color.parseColor("#E1BEE7"));
                TextView textView = (TextView) grid.findViewById(R.id.grid_text);
                if(listOfInteger.get(position)>=listOfChar.size()) {
                   // textView.setText("");
                   // textView.setBackgroundColor(Color.parseColor("#80FFFFFF"));
                    textView.setVisibility(View.GONE);
                }
                else
                {
                    textView.setVisibility(View.VISIBLE);
                    int view= Util.getViewOfChar(listOfChar.get(listOfInteger.get(position)),mContext);
                    textView.setBackgroundResource(view);;
                }
            } else {
                grid = (View) convertView;
            }

            return grid;
        }
    }
