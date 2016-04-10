package ua.company.testtask.adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

import ua.company.testtask.R;
import ua.company.testtask.ViewUtil;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ItemViewHolder> {
    private ArrayList<String> mImagesUri;
    private Context mContext;
    private int mItemImageWidth;
    private String mTestImagesFolderPath;

    public ImageAdapter(Context context, ArrayList<String> imagesUri) {
        mContext = context;
        mImagesUri = imagesUri;
        mItemImageWidth = ViewUtil.getDeviceWidth(context) / 2;
        mTestImagesFolderPath = ViewUtil.getTestImagesFolderPath();
    }

    @Override
    public ImageAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ImageAdapter.ItemViewHolder holder, int position) {
        File currentImageFile = new File(mTestImagesFolderPath, mImagesUri.get(position));
        int itemImageHeight = getImageNewHeight(currentImageFile.getAbsolutePath());
        holder.mItemImage.getLayoutParams().height = itemImageHeight;

        Picasso.with(mContext).load(currentImageFile).resize(mItemImageWidth, itemImageHeight).
                into(holder.mItemImage);
    }

    public int getImageNewHeight(String path) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        int realImageWith = options.outWidth;
        int realImageHeight = options.outHeight;
        float scale = (float) mItemImageWidth / (float) realImageWith;

        return (int) (realImageHeight * scale);
    }

    @Override
    public int getItemCount() {
        return mImagesUri.size();
    }

    protected static class ItemViewHolder extends RecyclerView.ViewHolder {
        protected ImageView mItemImage;
        public ItemViewHolder(View itemView) {
            super(itemView);
            mItemImage = (ImageView)itemView.findViewById(R.id.itemImage);
        }
    }
}
