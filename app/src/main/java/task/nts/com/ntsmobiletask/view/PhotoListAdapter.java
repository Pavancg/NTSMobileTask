package task.nts.com.ntsmobiletask.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import task.nts.com.ntsmobiletask.R;
import task.nts.com.ntsmobiletask.pojo.PhotosModel;

public class PhotoListAdapter extends RecyclerView.Adapter<PhotoListAdapter.ViewHolder> {
    private List<PhotosModel> photosModels;
    private Context context;
    public interface OnItemClickListener {
        void onItemClick(String imgUrl,int position);
    }
    OnItemClickListener listener;

    public PhotoListAdapter(Context context,OnItemClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    public PhotoListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.content_photos_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder,final int i) {

        viewHolder.tv_title.setText(photosModels.get(i).getTitle());
        String imgUrl = photosModels.get(i).getThumbUrl();
        Glide.with(context).load(imgUrl)
                .thumbnail(0.5f)
                .into(viewHolder.img_thubUrl);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.onItemClick(photosModels.get(i).getUrl(),i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return photosModels == null ? 0 : photosModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_title;
        ImageView img_thubUrl;
        public ViewHolder(View view) {
            super(view);
            tv_title = (TextView)view.findViewById(R.id.tv_title);
            img_thubUrl = (ImageView)view.findViewById(R.id.img_thumb);
        }
    }


    public void setPhotos(List<PhotosModel> photosModels) {
        this.photosModels = photosModels;
        notifyDataSetChanged();
    }
}
