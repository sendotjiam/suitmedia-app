package com.sendo.suitmedia.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sendo.suitmedia.R;
import com.sendo.suitmedia.activities.ChooseActivity;
import com.sendo.suitmedia.activities.EventActivity;
import com.sendo.suitmedia.models.Event;
import com.sendo.suitmedia.utilities.Constant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    Context context;
    ArrayList<Event> listEvent;
    SharedPreferences sharedPreferences;

    public EventAdapter(Context context) {
        this.context = context;
    }

    public void setListEventData(ArrayList<Event> listEvent) {
        this.listEvent = listEvent;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_event, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventAdapter.ViewHolder holder, int position) {
        Event event = listEvent.get(position);
//        holder.ivItemImg.setImageResource(event.getEventImg());
        Picasso.get()
                .load(event.getEventImg())
                .into(holder.ivItemImg);
        holder.tvItemDate.setText(event.getEventDate());
        holder.tvItemName.setText(event.getEventName());
    }

    @Override
    public int getItemCount() {
        return listEvent.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivItemImg;
        TextView tvItemName, tvItemDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            initialize();
            itemView.setOnClickListener(this);
        }

        private void initialize() {
            ivItemImg = itemView.findViewById(R.id.iv_item_event_img);
            tvItemName = itemView.findViewById(R.id.tv_item_event_name);
            tvItemDate = itemView.findViewById(R.id.tv_item_event_date);
        }

        @Override
        public void onClick(View view) {
            saveEventName();
            context.startActivity(new Intent(context, ChooseActivity.class));
            ((EventActivity) context).finish();
        }

        private void saveEventName() {
            sharedPreferences = context.getSharedPreferences(Constant.SHARED_EVENT_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(Constant.EVENT_NAME, listEvent.get(getAdapterPosition()).getEventName());
            editor.apply();
        }
    }
}
