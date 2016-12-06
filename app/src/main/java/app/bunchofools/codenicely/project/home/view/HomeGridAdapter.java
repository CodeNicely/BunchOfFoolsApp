package app.bunchofools.codenicely.project.home.view;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.bunchofools.codenicely.project.R;
import app.bunchofools.codenicely.project.home.model.data.HomeGridData;

/**
 * Created by meghal on 31/10/16.
 */

public class HomeGridAdapter extends BaseAdapter {

    private Context context;
    private List<HomeGridData> homeGridDataList=new ArrayList<>();
    HomeGridAdapter(Context context){
    this.context=context;
    }

    public void setData(List<HomeGridData> homeGridDataList){
    this.homeGridDataList=homeGridDataList;

    }

    @Override
    public int getCount() {
        return homeGridDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            grid = inflater.inflate(R.layout.home_grid_view_item, null);
            
            TextView textView = (TextView) grid.findViewById(R.id.title);
            ImageView imageView = (ImageView)grid.findViewById(R.id.imageView);
            textView.setText(homeGridDataList.get(position).getTitle());
            imageView.setImageDrawable(ContextCompat.getDrawable(context,homeGridDataList.get(position).getImage()));
        } else {
            grid = (View) convertView;
        }

        return grid;

    }
}
