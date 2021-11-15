package com.test.mvcnytimessample.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.test.mvcnytimessample.R;
import com.test.mvcnytimessample.model.Article;
import com.bumptech.glide.Glide;

import java.util.List;

public class MainRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Article> articles;
    private Context mContext;
    private onItemClick onitemclick;

    public MainRVAdapter(Context mContext, List<Article> articles) {
        this.mContext = mContext;
        this.articles = articles;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final ViewHolder holder1 = (ViewHolder) holder;
        if (articles.get(position).getMedia() != null && !articles.get(position).getMedia().isEmpty() &&
                articles.get(position).getMedia().get(0).getMediaMetaDatas() != null &&
                !articles.get(position).getMedia().get(0).getMediaMetaDatas().isEmpty()) {
            Glide.with(mContext).load(articles.get(position).getMedia().get(0).getMediaMetaDatas().get(0).getUrl()).into(holder1.itemImage);
        }
        holder1.itemTitle.setText(articles.get(position).getTitle());
        holder1.itemDes.setText(articles.get(position).getByLine());

        String formattedDate = DateFormat.format("yyyy-MM-dd", articles.get(position).getPublishedDate()).toString();
        holder1.itemDate.setText(formattedDate);

        ((ViewHolder) holder).mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    if (onitemclick != null) {
                        if (articles.size() != 0) {
                            onitemclick.OnItemClick(((ViewHolder) holder).mView, pos, articles);
                        }
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public void setMainOnClickListener(onItemClick onitemclick) {
        this.onitemclick = onitemclick;
    }

    public interface onItemClick {
        void OnItemClick(View view, int pos, List<Article> articles);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout mView;
        public ImageView itemImage;
        public TextView itemTitle;
        public TextView itemDes;
        public TextView itemDate;

        public ViewHolder(final View view) {
            super(view);
            mView = (LinearLayout) view.findViewById(R.id.mainLay);
            itemImage = (ImageView) view.findViewById(R.id.item_image);
            itemTitle = (TextView) view.findViewById(R.id.item_title);
            itemDes = (TextView) view.findViewById(R.id.item_des);
            itemDate = (TextView) view.findViewById(R.id.item_date);

        }
    }
}
