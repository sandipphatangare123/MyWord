package com.sandip.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sandip.model.ModelLevel;
import com.sandip.myword.R;
import com.sandip.util.OnItemClickListenerLevel;

import java.util.ArrayList;


public class RecyclerAdapterLevel extends  RecyclerView.Adapter<RecyclerViewHolderLevel> {

    Context context;
    LayoutInflater inflater;
    RecyclerViewHolderLevel vholder;
    OnItemClickListenerLevel itemClickListenerLevel;
    ArrayList<ModelLevel> listOfLevel=null;

    public RecyclerAdapterLevel(Context context, ArrayList<ModelLevel> listOfLevel, OnItemClickListenerLevel itemClickListenerLevel){
        this.context=context;
        this.listOfLevel=listOfLevel;
        this.itemClickListenerLevel=itemClickListenerLevel;
        inflater=LayoutInflater.from(context);
    }

    public void addItem(ArrayList<ModelLevel> listOfLevel) {
        //  this.listOfStories.clear();
        this.listOfLevel.addAll(listOfLevel);
        notifyDataSetChanged();
    }
    @Override
    public RecyclerViewHolderLevel onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=inflater.inflate(R.layout.layout_list_level, parent, false);
        vholder=new RecyclerViewHolderLevel(v);
        return vholder;
    }
    @Override
    public void onBindViewHolder(RecyclerViewHolderLevel holder, int position) {
       // holder.txtGameLevel.setText(("Level "+ (position+1)).toUpperCase());
        holder.txtGameLevel.setText("LEVEL "+(listOfLevel.get(position).getGameLevel()));
        holder.txtMaxWords.setText((listOfLevel.get(position).getLetterSize())+" Max Letters");
        if(listOfLevel.get(position).isClickable()) {
            holder.imgClick.setBackgroundResource(R.drawable.play_icon);
        }else {
            holder.imgClick.setBackgroundResource(R.drawable.lock_icon);
        }
        holder.bind(listOfLevel.get(position), itemClickListenerLevel,listOfLevel.get(position).getLetterSize(),listOfLevel.get(position).getGameLevel()+"");
        }

    @Override
    public int getItemCount() {

        return listOfLevel.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        if(position==0) {
            notifyItemRemoved(position);
        }
        else {
            notifyDataSetChanged();
        }
        return super.getItemId(position);
    }
}
