package com.force.codes.project.covid19.controller.views.adapter;

/*
 * Created by Force Porquillo on 6/2/20 12:50 PM
 * Copyright (c) 2020.  All rights reserved.
 * Last modified 6/2/20 6:28 AM
 *
 */

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.force.codes.project.covid19.R;
import com.force.codes.project.covid19.data.resources.web.model.CountryDetails;
import com.force.codes.project.covid19.controller.custom.utils.StringUtils;
import com.force.codes.project.covid19.controller.custom.interfaces.FragmentCallback;
import com.force.codes.project.covid19.controller.support.CustomBindingAdapter;
import com.like.LikeButton;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>{
    private List<CountryDetails> list;
    private FragmentCallback fragmentCallback;
    private StringUtils stringUtils = new StringUtils();

    public CountryListAdapter(FragmentCallback fragmentCallback){
        this.fragmentCallback = fragmentCallback;
    }

    public void setList(List<CountryDetails> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView){
        super.onDetachedFromRecyclerView(recyclerView);
    }

    @NotNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View view = inflater.inflate(R.layout.country_rows, parent, false);
        return new CountryViewHolder(view, fragmentCallback);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position){
        CountryDetails countryDetails = list.get(position);

        holder.countryName.setText(countryDetails.getCountry());
        holder.continent.setText(countryDetails.getContinent());
        holder.lastUpdate.setText(stringUtils.getDate(countryDetails.getUpdated()));
        holder.cases.setText(stringUtils.formatNumber(String.valueOf(countryDetails.getCases())));
        holder.deaths.setText(stringUtils.formatNumber(String.valueOf(countryDetails.getDeaths())));
        holder.recovered.setText(stringUtils.formatNumber(String.valueOf(countryDetails.getRecovered())));
        //setImage(holder.flag, countryDetails.getFlag());
        new CustomBindingAdapter().setStar(position, holder.starButton, countryDetails.getCountry());
    }

    private void setImage(CircleImageView circleImageView, final String imageUrl){
        Picasso.get()
                .load(imageUrl)
                .error(R.drawable.default_flag)
                .fit().into(circleImageView);
    }

    @Override
    public int getItemCount(){
        return list != null ? list.size() : 0;
    }

    public static class CountryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        final FragmentCallback fragmentCallback;
        @BindView(R.id.favorite_icon)
        LikeButton starButton;
        @BindView(R.id.countryName)
        TextView countryName;
        @BindView(R.id.last_updated)
        TextView lastUpdate;
        @BindView(R.id.continent)
        TextView continent;
        @BindView(R.id.cases_number)
        TextView cases;
        @BindView(R.id.deaths_number)
        TextView deaths;
        @BindView(R.id.recovered_number)
        TextView recovered;
        @BindView(R.id.worldwide_flag)
        CircleImageView flag;

        CountryViewHolder(@NonNull View itemView, FragmentCallback fragmentCallback){
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
            this.fragmentCallback = fragmentCallback;
        }

        @Override
        public void onClick(View v){
            fragmentCallback.CustomCardViewListener(getAdapterPosition());
        }
    }

}