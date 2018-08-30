package lh.cn.edu.henu.upto.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import lh.cn.edu.henu.upto.R;
import lh.cn.edu.henu.upto.bean.Fruit;

public class RecyclerTestAdapter extends RecyclerView.Adapter<RecyclerTestAdapter.ViewHolder>{


    ArrayList<Fruit> fruits = new ArrayList<>();

    public RecyclerTestAdapter(ArrayList<Fruit> fruits){
        this.fruits = fruits;
    }

    @Override
    public int getItemViewType(int position) {
        return fruits.get(position).getType();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder viewHolder;
        switch (viewType){
            case 0:
                viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test_1, parent, false));
                break;
            case 1:
                viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test_2, parent, false));
                break;
            case 2:
                viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test_3, parent, false));
                break;
            case 3:
                viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test_4, parent, false));
                break;
            default:
                viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_test_1, parent, false));
                break;

        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return fruits.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }

    }




}
