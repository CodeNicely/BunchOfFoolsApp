package app.bunchofools.codenicely.project.gallery.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.ArrayList;

import app.bunchofools.codenicely.project.R;
import app.bunchofools.codenicely.project.helper.image_loader.GlideImageLoader;
import app.bunchofools.codenicely.project.helper.image_loader.ImageLoader;
import app.bunchofools.codenicely.project.home.view.HomeActivity;

/**
 * Created by meghal on 13/10/16.
 */

public class GalleryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<String> imageUrlList=new ArrayList<>();
    private ImageLoader imageLoader;

    public GalleryAdapter(Context context) {
        this.context = context;
        layoutInflater= LayoutInflater.from(context);
        imageLoader=new GlideImageLoader(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view=layoutInflater.inflate(R.layout.gallery_item,parent,false);

        return new ImageViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        ImageViewHolder imageViewHolder=(ImageViewHolder)holder;

        imageLoader.loadImage(imageUrlList.get(position),imageViewHolder.imageView,imageViewHolder.progressBar);
        imageViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(context instanceof HomeActivity){
                    ((HomeActivity) context).openImageViewer(imageUrlList,position);

                }
            }
        });

    }
    @Override
    public int getItemCount() {
        return imageUrlList.size();
    }


    public void setImageUrlList(ArrayList<String> imageUrlList){
        this.imageUrlList=imageUrlList;
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{

//        @BindView(R.id.imageView)
        ImageView imageView;
        ProgressBar progressBar;

        public ImageViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.imageView);
            progressBar=(ProgressBar)itemView.findViewById(R.id.imageProgressBar);
        }
    }

}
