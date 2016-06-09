package lifeworks.com.demo.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import lifeworks.com.demo.R;

public class HeaderItemViewHolder extends RecyclerView.ViewHolder {
    public final View mView;
    public final TextView header_title;

    public HeaderItemViewHolder(View view) {
        super(view);
        mView = view;
        header_title = (TextView) view.findViewById(R.id.header_title);
    }

}