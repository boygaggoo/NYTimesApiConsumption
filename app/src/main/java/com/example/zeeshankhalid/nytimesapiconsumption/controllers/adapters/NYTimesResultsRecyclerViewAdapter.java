package com.example.zeeshankhalid.nytimesapiconsumption.controllers.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.zeeshankhalid.nytimesapiconsumption.R;
import com.example.zeeshankhalid.nytimesapiconsumption.controllers.interfaces.OnResultClickListener;
import com.example.zeeshankhalid.nytimesapiconsumption.models.Result;

import java.util.List;

public class NYTimesResultsRecyclerViewAdapter extends RecyclerView.Adapter<NYTimesResultsRecyclerViewAdapter.NYTimesResultViewHolder> {

    private Context context;
    private List<Result> results;
    private OnResultClickListener onResultClickListener;

    public NYTimesResultsRecyclerViewAdapter(Context context, List<Result> results) {
        this.context = context;
        this.results = results;
    }

    public void setOnResultClickListener(OnResultClickListener onResultClickListener) {
        this.onResultClickListener = onResultClickListener;
    }

    @NonNull
    @Override
    public NYTimesResultViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_ny_times_result_item, viewGroup, false);
        return new NYTimesResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NYTimesResultViewHolder nyTimesResultViewHolder, int i) {
        nyTimesResultViewHolder.bindViews(i);
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class NYTimesResultViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate, tvSection, tvByLine, tvTitle;
        ImageView ivImage;

        public NYTimesResultViewHolder(@NonNull View itemView) {
            super(itemView);
            initViews();
        }

        private void initViews() {
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvByLine = itemView.findViewById(R.id.tvByLine);
            tvSection = itemView.findViewById(R.id.tvSection);
            tvDate = itemView.findViewById(R.id.tvDate);

            ivImage = itemView.findViewById(R.id.ivImage);
        }

        private void bindViews(final int position) {
            tvTitle.setText(results.get(position).getTitle());
            tvByLine.setText(results.get(position).getByline());
            tvSection.setText(results.get(position).getSection());
            tvDate.setText(results.get(position).getPublishedDate());

            Glide.with(context).load(results.get(position).getMedia().get(0).getMediaMetadata().get(0).getUrl()).centerCrop().listener(new RequestListener<String, GlideDrawable>()
            {
                @Override public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource)
                {
                    return false;
                }

                @Override public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource)
                {
                    return false;
                }
            }).error(R.drawable.place_holder).into(ivImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    onResultClickListener.onResultClicked(position);
                }
            });
        }
    }
}
