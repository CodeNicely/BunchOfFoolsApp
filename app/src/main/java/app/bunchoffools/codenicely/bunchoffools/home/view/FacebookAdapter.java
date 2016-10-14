package app.bunchoffools.codenicely.bunchoffools.home.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import app.bunchoffools.codenicely.bunchoffools.R;
import app.bunchoffools.codenicely.bunchoffools.home.model.data.FbFeeds;

/**
 * Created by meghal on 15/10/16.
 */

public class FacebookAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<FbFeeds> fbFeedsList=new ArrayList<>();
    private LayoutInflater layoutInflater;

    FacebookAdapter(Context context){
        this.context=context;
        layoutInflater= LayoutInflater.from(context);
    }


    void setData(List<FbFeeds> fbFeedsList){
        this.fbFeedsList=fbFeedsList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view=layoutInflater.inflate(R.layout.gallery_item,parent,false);

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return fbFeedsList.size();
    }
}
