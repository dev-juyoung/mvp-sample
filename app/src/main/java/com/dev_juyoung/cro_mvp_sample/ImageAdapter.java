package com.dev_juyoung.cro_mvp_sample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dev_juyoung.cro_mvp_sample.base.BaseViewHolder;
import com.dev_juyoung.cro_mvp_sample.utils.ImageUtils;
import com.dev_juyoung.cro_mvp_sample.utils.OnItemClickListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by juyounglee on 2017. 11. 3..
 */

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> implements ImageAdapterContract.View, ImageAdapterContract.Model {
    private static final String TAG = "ImageAdapter";

    private Context mContext;
    private ArrayList<Integer> data;
    private OnItemClickListener onItemClickListener;

    public ImageAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void addItems(ArrayList<Integer> items) {
        Log.i(TAG, "AdapterView: Presenter로 부터 신규 데이터 전달 받음.");

        // 신규 데이터 추가.
        if (data == null) {
            data = new ArrayList<>();
        }

        data.addAll(items);
    }

    @Override
    public void updateItems(ArrayList<Integer> items) {
        Log.i(TAG, "AdapterView: Presenter로 부터 갱신 데이터 전달 받음.");

        // 데이터 갱신.
        data = items;
    }

    @Override
    public void updateView() {
        Log.i(TAG, "AdapterView: Presenter로 부터 UI 갱신 요청 전달 받음.");

        notifyDataSetChanged();
    }

    @Override
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class ViewHolder extends BaseViewHolder {
        @BindView(R.id.imageView)
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        @OnClick(R.id.imageView)
        void imageViewTouchUp() {
            Log.i(TAG, "AdapterView: 사용자 클릭 이벤트 발생.");

            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(getAdapterPosition());
            }
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
