package lh.cn.edu.henu.upto.recycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import lh.cn.edu.henu.upto.R;

public class SlideLayoutAdapter extends RecyclerView.Adapter<SlideLayoutAdapter.ViewHolder> implements View.OnClickListener {

    private Context context;
    ArrayList<String> arrayList = new ArrayList<>();
    private int position = 0;

    private SlideLayoutClickListener slideLayoutClickListener;
    SlideLayout slideLayout;

    public SlideLayoutAdapter(Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.slide_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        holder.item_name.setOnClickListener(this);
        holder.item_message.setText(arrayList.get(position));
        holder.item_message.setOnClickListener(this);
        holder.menu_delete.setOnClickListener(this);
        holder.menu_top.setOnClickListener(this);
        this.position = position;
        holder.menu_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                String po = arrayList.get(position);
                arrayList.remove(position);
                arrayList.add(0, po);
                notifyDataSetChanged();
                slideLayout = (SlideLayout) holder.getView();
                slideLayout.resetMenu();
                slideLayoutClickListener.onRefresh(position, slideLayout,1);

            }
        });
        holder.menu_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                arrayList.remove(position);
                notifyItemRemoved(position);
                notifyDataSetChanged();
                slideLayout = (SlideLayout) holder.getView();
                slideLayout.resetMenu();
                //slideLayoutClickListener.onRefresh(position, slideLayout,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.item_name:
                Toast.makeText(context, "item_name", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_message:
                Toast.makeText(context, "item_message", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_top:
                Toast.makeText(context, "menu_top", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView item_name, item_message, menu_top, menu_delete;

        private View view;
        public ViewHolder(View view) {
            super(view);
            item_name = (TextView) view.findViewById(R.id.item_name);
            item_message = (TextView) view.findViewById(R.id.item_message);
            menu_top = (TextView) view.findViewById(R.id.menu_top);
            menu_delete = (TextView) view.findViewById(R.id.menu_delete);
            this.view = view;
        }

        public View getView(){
            return view;
        }
    }

    public void setSlideLayoutClickListener(SlideLayoutClickListener slideLayoutClickListener){
        this.slideLayoutClickListener = slideLayoutClickListener;
    }

    public interface SlideLayoutClickListener{

        void onRefresh(int position, SlideLayout view, int type);
    }

}
