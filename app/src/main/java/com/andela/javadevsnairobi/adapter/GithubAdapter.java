package com.andela.javadevsnairobi.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andela.javadevsnairobi.R;
import com.andela.javadevsnairobi.model.GithubUser;
import com.andela.javadevsnairobi.views.DevDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;


public class GithubAdapter extends RecyclerView.Adapter<GithubAdapter.DevViewHolder> {

    List<GithubUser> devsList;

    public GithubAdapter(List<GithubUser> devsList) {
        this.devsList = devsList;
    }

    class DevViewHolder extends RecyclerView.ViewHolder {
        ImageView listItemImage;
        TextView listItemUsername;
        ConstraintLayout listItemLayout;

        public DevViewHolder(@NonNull View itemView) {
            super(itemView);
            listItemImage = itemView.findViewById(R.id.dev_list_image);
            listItemUsername = itemView.findViewById(R.id.dev_list_username);
            listItemLayout = itemView.findViewById(R.id.dev_list_item_layout);

        }
    }

    @NonNull
    @Override
    public DevViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(viewType, viewGroup, false);
        return new DevViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull DevViewHolder holder, int position) {
        final GithubUser dev = devsList.get(position);
        holder.listItemUsername.setText(dev.getUsername());
        Picasso.get().load(dev.getAvatarUrl()).transform(new CropCircleTransformation()).into(holder.listItemImage);

        holder.listItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DevDetailActivity.class);
                intent.putExtra("username", dev.getUsername());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.devs_list_item;
    }

    @Override
    public int getItemCount() {
        return devsList.size();
    }


}


