package info.mizoguche.tiqavviewer;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by mizoguche on 2014/08/01.
 */
public class TiqavListItemView extends RelativeLayout {

    private TiqavImage tiqavImage;
    private RequestQueue queue;

    @InjectView(R.id.view_item_image)
    NetworkImageView imageView;

    public TiqavListItemView(Context context, TiqavImage image, RequestQueue queue){
        super(context);

        tiqavImage = image;
        this.queue = queue;

        View layout = LayoutInflater.from(context).inflate(R.layout.view_item, this);
        ButterKnife.inject(this, layout);

        imageView.setImageUrl(tiqavImage.getUrl(), new ImageLoader(queue, new LruCacheSample()));
        Log.d("test", "loading " + tiqavImage.getUrl());
    }


    public void setImage(TiqavImage image) {
        this.tiqavImage = image;
        imageView.setImageUrl(tiqavImage.getUrl(), new ImageLoader(queue, new LruCacheSample()));
    }
}
