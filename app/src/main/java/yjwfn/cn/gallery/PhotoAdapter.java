package yjwfn.cn.gallery;

import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by yjwfn on 17-9-23.
 */

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {


    private int[] photos;

    public PhotoAdapter(int[] photos) {
        this.photos = photos;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new PhotoViewHolder(inflater.inflate(R.layout.item_photo, parent, false));
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        holder.photoView.setImageResource(photos[position]);
    }

    @Override
    public int getItemCount() {
        return photos.length;
    }

    static final class PhotoViewHolder extends RecyclerView.ViewHolder{

        ImageView photoView;

        public PhotoViewHolder(View itemView) {
            super(itemView);

            photoView = itemView.findViewById(R.id.iv_photo);
        }
    }
}
