package lifeworks.com.demo.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;

import lifeworks.com.demo.R;
import lifeworks.com.demo.business.model.RandomItem;

public class RandomItemViewHolder extends RecyclerView.ViewHolder {
    public final View mView;
    public final NetworkImageView item_random_image;
    public final TextView item_random_title;
    public RandomItem mItem;

    public RandomItemViewHolder(View view) {
        super(view);
        mView = view;
        item_random_image = (NetworkImageView) view.findViewById(R.id.item_random_image);
        item_random_title = (TextView) view.findViewById(R.id.item_random_title);
    }

}