package app.bunchoffools.codenicely.bunchoffools.home.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.bunchoffools.codenicely.bunchoffools.R;
import app.bunchoffools.codenicely.bunchoffools.helper.image_loader.GlideImageLoader;
import app.bunchoffools.codenicely.bunchoffools.helper.image_loader.ImageLoader;
import app.bunchoffools.codenicely.bunchoffools.home.model.data.FbDetails;

/**
 * Created by meghal on 15/10/16.
 */

public class FacebookAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<FbDetails> fbDetailsList =new ArrayList<>();
    private LayoutInflater layoutInflater;
    private static final int VIEW_TYPE_ALBUM =11;
    private static final int VIEW_TYPE_OTHER=100;
    private ImageLoader imageLoader;

    FacebookAdapter(Context context){
        this.context=context;
        layoutInflater= LayoutInflater.from(context);
        imageLoader=new GlideImageLoader(context);
    }


    void setData(List<FbDetails> fbDetailsList){
        this.fbDetailsList =fbDetailsList;

    }

    @Override
    public int getItemViewType(int position) {
    //    return super.getItemViewType(position);

        if(fbDetailsList.get(position).getAttachments().getData().get(0).getType().equals("album")){
            return VIEW_TYPE_ALBUM;
        }else{
            return VIEW_TYPE_OTHER;
        }


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType== VIEW_TYPE_ALBUM){
            View view=layoutInflater.inflate(R.layout.home_item_album,parent,false);
            return new AlbumViewHolder(view);
        }else{
            View view=layoutInflater.inflate(R.layout.home_item_other,parent,false);
            return new OtherViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(fbDetailsList.get(position).getAttachments().getData().get(0).getType().equals("album")){
            AlbumViewHolder albumViewHolder =(AlbumViewHolder)holder;
        //    albumViewHolder.viewPager.getLayoutParams().height=fbDetailsList.get(position).getAttachments().getData().get(0).getSubattachments().getData().get(0).getMedia().getImage().getHeight();
        //    albumViewHolder.viewPager.getLayoutParams().width=fbDetailsList.get(position).getAttachments().getData().get(0).getSubattachments().getData().get(0).getMedia().getImage().getWidth();

            albumViewHolder.fbImagesAdapter.setData(fbDetailsList.get(position).getAttachments().getData()
                    .get(0).getSubattachments().getData());
            albumViewHolder.fbImagesAdapter.notifyDataSetChanged();
            albumViewHolder.title.setText(fbDetailsList.get(position).getAttachments().getData().get(0).getTitle());
            albumViewHolder.description.setText(fbDetailsList.get(position).getAttachments().getData().get(0).getDescription());

        }else{
            OtherViewHolder otherViewHolder=(OtherViewHolder)holder;

            otherViewHolder.title.setText(fbDetailsList.get(position).getAttachments().getData().get(0).getTitle());
            otherViewHolder.description.setText(fbDetailsList.get(position).getAttachments().getData().get(0).getDescription());
        //    otherViewHolder.imageView.getLayoutParams().height = fbDetailsList.get(position).getAttachments().getData().get(0).getMedia().getImage().getHeight();
        //    otherViewHolder.imageView.getLayoutParams().width = fbDetailsList.get(position).getAttachments().getData().get(0).getMedia().getImage().getWidth();
            imageLoader.loadImage(fbDetailsList.get(position).getAttachments().getData().get(0).getMedia().getImage().getSrc(),otherViewHolder.imageView,otherViewHolder.imageProgressBar);

        }



    }

    @Override
    public int getItemCount() {
        return fbDetailsList.size();
    }

    public class AlbumViewHolder extends RecyclerView.ViewHolder{


        private TextView title;
        private ViewPager viewPager;
        private TextView description;
        private FbImagesAdapter fbImagesAdapter;
        public AlbumViewHolder(View itemView) {
            super(itemView);
            fbImagesAdapter=new FbImagesAdapter(context);
            title=(TextView)itemView.findViewById(R.id.title);
            description=(TextView)itemView.findViewById(R.id.description);
            viewPager=(ViewPager)itemView.findViewById(R.id.viewPager);
            viewPager.setAdapter(fbImagesAdapter);
        }

    }

    public class OtherViewHolder extends RecyclerView.ViewHolder{

        private TextView title;
        private ImageView imageView;
        private ProgressBar imageProgressBar;
        private TextView description;

        public OtherViewHolder(View itemView) {
            super(itemView);

            title=(TextView)itemView.findViewById(R.id.title);
            imageView=(ImageView)itemView.findViewById(R.id.imageView);
            imageProgressBar=(ProgressBar)itemView.findViewById(R.id.imageProgressBar);
            description=(TextView)itemView.findViewById(R.id.description);

        }
    }
}
