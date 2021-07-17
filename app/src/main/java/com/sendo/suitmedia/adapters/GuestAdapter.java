package com.sendo.suitmedia.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sendo.suitmedia.R;
import com.sendo.suitmedia.activities.ChooseActivity;
import com.sendo.suitmedia.activities.GuestActivity;
import com.sendo.suitmedia.models.Guest;
import com.sendo.suitmedia.utilities.Constant;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class GuestAdapter extends RecyclerView.Adapter<GuestAdapter.ViewHolder> {
    Context context;
    List<Guest> listGuest;
    SharedPreferences sharedPreferences;

    public GuestAdapter(Context context) {
        this.context = context;
    }

    public void setListGuestData(List<Guest> listGuest) {
        this.listGuest = listGuest;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_guest, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GuestAdapter.ViewHolder holder, int position) {
        Guest guest = listGuest.get(position);
        holder.ivItemImg.setImageResource(guest.getGuestImg());
//        Picasso.get()
//                .load(guest.getGuestImg())
//                .into(holder.ivItemImg);
        holder.tvItemName.setText(guest.getGuestName());
    }

    @Override
    public int getItemCount() {
        return listGuest.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivItemImg;
        TextView tvItemName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            initialize();
            itemView.setOnClickListener(this);
        }

        private void initialize() {
            ivItemImg = itemView.findViewById(R.id.iv_item_guest_img);
            tvItemName = itemView.findViewById(R.id.tv_item_guest_name);
        }

        @Override
        public void onClick(View view) {
            Guest guest = listGuest.get(getAdapterPosition());
            Log.v("DATE", guest.getGuestName());
            checkGuestBirthdate(guest);
            saveGuestName(guest);
            context.startActivity(new Intent(context, ChooseActivity.class));
            ((GuestActivity) context).finish();
        }

        private void checkGuestBirthdate(Guest guest) {
            String date = guest.getGuestBirthdate();
            String[] trimDateValues = date.split("-");
            int day = Integer.parseInt(trimDateValues[2]);
            if (day % 2 == 0 && day % 3 == 0) Toast.makeText(context, "iOS", Toast.LENGTH_SHORT).show();
            else if (day % 2 == 0) Toast.makeText(context, "Blackberry", Toast.LENGTH_SHORT).show();
            else if (day % 3 == 0) Toast.makeText(context, "Android", Toast.LENGTH_SHORT).show();
            else Toast.makeText(context, "Featured Phone", Toast.LENGTH_SHORT).show();
        }

        private void saveGuestName(Guest guest) {
            sharedPreferences = context.getSharedPreferences(Constant.SHARED_GUEST_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(Constant.GUEST_NAME, guest.getGuestName());
            editor.apply();
        }
    }
}
