package com.sandip.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.sandip.model.ModelChapter;
import com.sandip.myword.R;
import com.sandip.util.OnItemClickListenerChapter;


/**
 * Created by Snehal Shirsat, Nextremer solutions India Pvt Ltd  on 6/27/2016.
 */
public class RecyclerViewHolderChapter extends RecyclerView.ViewHolder{

    TextView txtGameLevel;
    ImageView imgClick;

     public RecyclerViewHolderChapter(View itemView) {
        super(itemView);

         txtGameLevel=(TextView)itemView.findViewById(R.id.txt_game_level);
         imgClick=(ImageView) itemView.findViewById(R.id.img_play);

         //  txtMaxWords=(TextView)itemView.findViewById(R.id.txt_game_level_word_cnt);

        final Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
        animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
        animation.setRepeatCount(Animation.INFINITE); // Repeat animation infinitely
        animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in


        }
    public void bind(final ModelChapter item, final OnItemClickListenerChapter listener) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                listener.onItemClick(item);
            }
        });
    }

}
