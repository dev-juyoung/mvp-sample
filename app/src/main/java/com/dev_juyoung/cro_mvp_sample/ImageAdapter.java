package com.dev_juyoung.cro_mvp_sample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dev_juyoung.cro_mvp_sample.base.BaseViewHolder;
import com.dev_juyoung.cro_mvp_sample.utils.ImageUtils;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by juyounglee on 2017. 11. 3..
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Integer> data;

    public ImageAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void addItems(ArrayList<Integer> items) {
        if (data == null) {
            data = new ArrayList<>();
        }

        data.addAll(items);
        notifyDataSetChanged();
    }

    public void updateItems(ArrayList<Integer> items) {
        data = items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends BaseViewHolder {
        @BindView(R.id.imageView)
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImageUtils.load(mContext, data.get(position), holder.imageView);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }
}
