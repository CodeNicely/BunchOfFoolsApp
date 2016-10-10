package app.bunchoffools.codenicely.bunchoffools.home.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import app.bunchoffools.codenicely.bunchoffools.R;

/**
 * Created by meghal on 11/10/16.
 */

public class ImagesAdapter extends PagerAdapter {

    private static final String TAG ="ImagesAdapter" ;
    private List<String> imageUrlList=new ArrayList<>();
    private Context context;
    ImagesAdapter(Context context){
        this.context=context;
    }


    public void setImageList(List<String> imageUrlList){
        this.imageUrlList=imageUrlList;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);

            View view = layoutInflater.inflate(R.layout.item_image, container, false);
        container.addView(view);
        ImageView imageView=(ImageView) view.findViewById(R.id.imageView);
        Picasso.with(context).load(imageUrlList.get(position)).into(imageView);
        return view;
    }

    @Override
    public int getCount() {
        Log.d(TAG, "getCount: "+imageUrlList.size());
        return imageUrlList.size();
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
