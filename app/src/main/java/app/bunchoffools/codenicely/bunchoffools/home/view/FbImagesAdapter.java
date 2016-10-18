package app.bunchoffools.codenicely.bunchoffools.home.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import app.bunchoffools.codenicely.bunchoffools.R;
import app.bunchoffools.codenicely.bunchoffools.helper.image_loader.GlideImageLoader;
import app.bunchoffools.codenicely.bunchoffools.helper.image_loader.ImageLoader;
import app.bunchoffools.codenicely.bunchoffools.home.model.data.FbData;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * Created by meghal on 15/10/16.
 */

public class FbImagesAdapter extends PagerAdapter{


    private Context context;
    private ImageLoader imageLoader;
    private List<FbData> fbDataList=new ArrayList<>();
    FbImagesAdapter(Context context){
        this.context=context;
        imageLoader=new GlideImageLoader(context);
    }

    public void setData(List<FbData> fbDataList){
        this.fbDataList=fbDataList;
        Log.i(TAG,"Size is func"+fbDataList.size());

    }

    @Override
    public int getCount() {
        Log.i(TAG,"Size is "+fbDataList.size());
        return fbDataList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.image_item_album, container, false);
        container.addView(view);
        ImageView imageView=(ImageView) view.findViewById(R.id.imageView);
       // imageView.getLayoutParams().height=fbDataList.get(position).getMedia().getImage().getHeight();
       // imageView.getLayoutParams().height=fbDataList.get(position).getMedia().getImage().getWidth();
        ProgressBar imageProgressBar=(ProgressBar)view.findViewById(R.id.imageProgressBar);
        imageLoader.loadImage(fbDataList.get(position).getMedia().getImage().getSrc(),imageView,imageProgressBar);
        return view;

    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }

    @Override
    public float getPageWidth(int position) {
        super.getPageWidth(position);
        return 0.4f;
    }
}
