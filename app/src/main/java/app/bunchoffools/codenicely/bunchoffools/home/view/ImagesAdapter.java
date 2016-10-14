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
import app.bunchoffools.codenicely.bunchoffools.home.model.data.HomeSliderData;

/**
 * Created by meghal on 11/10/16.
 */

public class ImagesAdapter extends PagerAdapter {

    private static final String TAG ="ImagesAdapter" ;
    private List<HomeSliderData> homeSliderDataList=new ArrayList<>();
    private Context context;
    private ImageLoader imageLoader;
    ImagesAdapter(Context context)

    {
        this.context=context;
        imageLoader=new GlideImageLoader(context);

    }


    public void setImageList(List<HomeSliderData> homeSliderDataList){
        this.homeSliderDataList=homeSliderDataList;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);

            View view = layoutInflater.inflate(R.layout.item_image, container, false);
        container.addView(view);
        ImageView imageView=(ImageView) view.findViewById(R.id.imageView);
        ProgressBar imageProgressBar=(ProgressBar)view.findViewById(R.id.imageProgressBar);
        imageLoader.loadImage(homeSliderDataList.get(position).getImage_url(),imageView,imageProgressBar);
        return view;
    }

    @Override
    public int getCount() {
        Log.d(TAG, "getCount: "+homeSliderDataList.size());
        return homeSliderDataList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}
