package info.mizoguche.tiqavviewer;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by mizoguche on 2014/08/01.
 */
public class TiqavAdapter extends BaseAdapter{

    private Context context;
    private List<TiqavImage> list;
    private RequestQueue queue;

    public TiqavAdapter(Context context, TiqavImage[] list, RequestQueue queue){
        this.context = context;
        this.list = Arrays.asList(list);
        this.queue = queue;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TiqavImage image = list.get(position);
        TiqavListItemView view;
        if(convertView == null){
            view = new TiqavListItemView(context, image, queue);
        }else{
            view = (TiqavListItemView) convertView;
            view.setImage(image);
        }
        Log.d("test", image.toString());
        return view;
    }

    public List<TiqavImage> getList() {

        return list;
    }
}
