package com.sandip.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sandip.model.ModelSetting;
import com.sandip.myword.R;
import com.sandip.util.OnItemClickListenerSetting;
import com.sandip.util.UtilPref;

import java.util.ArrayList;


public class RecyclerAdapterSetting extends RecyclerView.Adapter<RecyclerViewHolderSettings> {

    Context context;
    LayoutInflater inflater;
    RecyclerViewHolderSettings vholder;
    OnItemClickListenerSetting itemClickListenerLevel;
    ArrayList<ModelSetting> listOfModelSetting = null;

    public RecyclerAdapterSetting(Context context, ArrayList<ModelSetting> listOfModelSetting, OnItemClickListenerSetting itemClickListenerLevel) {
        this.context = context;
        this.listOfModelSetting = listOfModelSetting;
        this.itemClickListenerLevel = itemClickListenerLevel;
        inflater = LayoutInflater.from(context);
    }

    public void addItem(ArrayList<ModelSetting> listOfModelSetting) {
        //  this.listOfStories.clear();
        this.listOfModelSetting.addAll(listOfModelSetting);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerViewHolderSettings onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.layout_list_setting, parent, false);
        vholder = new RecyclerViewHolderSettings(v);
        return vholder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolderSettings holder, int position) {
        String s= UtilPref.getFromPrefs(context, "langauge", "en");
        Log.e("MyWord-->",s);
        if (s.equalsIgnoreCase("en")) {
            holder.txtGameLevel.setText((listOfModelSetting.get(position).getSettingName()));
        } else {
            holder.txtGameLevel.setText((listOfModelSetting.get(position).getGameSettingTitle()));
        }
        holder.bind(listOfModelSetting.get(position), itemClickListenerLevel);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            vholder = (RecyclerViewHolderSettings) v.getTag();
            int position = vholder.getPosition();
            Toast.makeText(context, "This is position " + position, Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public int getItemCount() {

        return listOfModelSetting.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        if (position == 0) {
            notifyItemRemoved(position);
        } else {
            notifyDataSetChanged();
        }
        return super.getItemId(position);
    }
}
