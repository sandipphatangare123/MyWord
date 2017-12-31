package com.sandip.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sandip.model.ModelChapter;
import com.sandip.myword.R;
import com.sandip.util.OnItemClickListenerChapter;

import java.util.ArrayList;


public class RecyclerAdapterChapter extends  RecyclerView.Adapter<RecyclerViewHolderChapter> {

    Context context;
    LayoutInflater inflater;
    RecyclerViewHolderChapter vholder;
    OnItemClickListenerChapter itemClickListenerLevel;
    ArrayList<ModelChapter> listOfModelChapters=null;

    public RecyclerAdapterChapter(Context context, ArrayList<ModelChapter> listOfModelChapters, OnItemClickListenerChapter itemClickListenerLevel){
        this.context=context;
        this.listOfModelChapters=listOfModelChapters;
        this.itemClickListenerLevel=itemClickListenerLevel;
        inflater=LayoutInflater.from(context);
    }

    public void addItem(ArrayList<ModelChapter> listOfModelChapters) {
        //  this.listOfStories.clear();
        this.listOfModelChapters.addAll(listOfModelChapters);
        notifyDataSetChanged();
    }
    @Override
    public RecyclerViewHolderChapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=inflater.inflate(R.layout.layout_list_chapter, parent, false);
        vholder=new RecyclerViewHolderChapter(v);
        return vholder;
    }
    @Override
    public void onBindViewHolder(RecyclerViewHolderChapter holder, int position) {
        holder.txtGameLevel.setText((listOfModelChapters.get(position).getChapterName()));
        if(listOfModelChapters.get(position).isClickable()) {
            holder.imgClick.setBackgroundResource(R.drawable.play_icon);
        }else {
            holder.imgClick.setBackgroundResource(R.drawable.lock_icon);
        }
        holder.bind(listOfModelChapters.get(position), itemClickListenerLevel);
        }

    @Override
    public int getItemCount() {

        return listOfModelChapters.size();
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
