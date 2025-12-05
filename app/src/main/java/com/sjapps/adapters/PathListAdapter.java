package com.sjapps.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sjapps.jsonlist.MainActivity;
import com.sjapps.jsonlist.databinding.ListPathLayoutBinding;

public class PathListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    String[] list;
    Context context;
    MainActivity activity;
    String path;


    static class ViewHolder extends RecyclerView.ViewHolder{

        ListPathLayoutBinding binding;

        public ViewHolder(ListPathLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public TextView getValTxt(){
            return binding.itemName;
        }
        public View getBtn(){
            return binding.btn;
        }

        public View getView(){
            return itemView;
        }

    }

    public PathListAdapter(Context context, String path){
        this.context = context;
        this.activity = (MainActivity) context;
        this.path = path;
        this.list = path.split("///");
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListPathLayoutBinding binding = ListPathLayoutBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int pos) {

        String item = list[pos];

        int position = pos;

        ViewHolder currentHolder = (ViewHolder) holder;
        currentHolder.getValTxt().setText(item);
        currentHolder.getBtn().setOnClickListener(v -> {
            activity.goBack(getLast() - position);
        });

        if (position == getLast())
            currentHolder.binding.arrowImg.setVisibility(View.GONE);
        else currentHolder.binding.arrowImg.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    private int getLast(){
        return list.length -1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
