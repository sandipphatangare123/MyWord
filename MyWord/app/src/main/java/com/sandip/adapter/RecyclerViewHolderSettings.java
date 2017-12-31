package com.sandip.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import com.sandip.model.ModelSetting;
import com.sandip.myword.R;
import com.sandip.util.OnItemClickListenerSetting;


public class RecyclerViewHolderSettings extends RecyclerView.ViewHolder{

    TextView txtGameLevel;

     public RecyclerViewHolderSettings(View itemView) {
        super(itemView);

         txtGameLevel=(TextView)itemView.findViewById(R.id.txt_game_level);
       //  txtMaxWords=(TextView)itemView.findViewById(R.id.txt_game_level_word_cnt);

        final Animation animation = new AlphaAnimation(1, 0); // Change alpha from fully visible to invisible
        animation.setInterpolator(new LinearInterpolator()); // do not alter animation rate
        animation.setRepeatCount(Animation.INFINITE); // Repeat animation infinitely
        animation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end so the button will fade back in


        }
    public void bind(final ModelSetting item, final OnItemClickListenerSetting listener) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                txtGameLevel.setBackground(v.getResources().getDrawable(R.drawable.selection));
                listener.onItemClick(item);
            }
        });
    }

}
