package com.sjapps.about;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sjapps.jsonlist.databinding.AboutListItemBinding;

import java.util.ArrayList;

public class AboutListAdapter extends RecyclerView.Adapter<AboutListAdapter.ViewHolder> {

    ArrayList<AboutListItem> Items;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        AboutListItemBinding binding;
        public ViewHolder(AboutListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public TextView getNameTxt(){
            return binding.NameTxt;
        }
        public TextView getValueTxt(){
            return binding.ValueTxt;
        }
        public LinearLayout getLayout(){
            return binding.layoutItem;
        }
        public View getView(){
            return itemView;
        }

    }

    public AboutListAdapter(ArrayList<AboutListItem> items) {
        Items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AboutListItemBinding binding = AboutListItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getNameTxt().setText(Items.get(position).getItemName());
        holder.getValueTxt().setText(Items.get(position).getItemValue());
        View.OnClickListener onClickListener = Items.get(position).getOnClickListener();
        if (onClickListener != null){
            holder.getLayout().setOnClickListener(onClickListener);
        }

    }

    @Override
    public int getItemCount() {
        return Items.size();
    }


}
