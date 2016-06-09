package lifeworks.com.demo.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.sectionedrecyclerview.SectionedRecyclerViewAdapter;
import com.android.volley.toolbox.ImageLoader;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import lifeworks.com.demo.R;
import lifeworks.com.demo.business.listener.OnRecyclerViewInteractionListener;
import lifeworks.com.demo.business.model.RandomItem;
import lifeworks.com.demo.business.sync.volley.cache.ImageCacheManager;

/**
 * {@link RecyclerView.Adapter} that can display a {@link RandomItem} and makes a call to the
 * specified {@link OnRecyclerViewInteractionListener}.
 */
public class RandomItemRecyclerViewAdapter extends SectionedRecyclerViewAdapter<RecyclerView.ViewHolder> {

    private final Map<Integer, List<RandomItem>> mValues;
    private final OnRecyclerViewInteractionListener mListener;
    private ImageLoader mImageLoader;

    public RandomItemRecyclerViewAdapter(Map<Integer, List<RandomItem>> items, OnRecyclerViewInteractionListener listener) {
        mValues = items;
        mListener = listener;
        mImageLoader = ImageCacheManager.getInstance().getImageLoader();
    }

    @Override
    public int getSectionCount() {
        if (mValues == null) {
            return 0;
        }
        return mValues.keySet().size();
    }

    @Override
    public int getItemCount(int section) {
        if (mValues == null || mValues.get(section) == null) {
            return 0;
        }
        return mValues.get(section).size();
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int section) {
        HeaderItemViewHolder headerItemViewHolder = (HeaderItemViewHolder) holder;
        headerItemViewHolder.header_title.setText(String.format(Locale.getDefault(), "Section %d", (section + 1)));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int section, int relativePosition, int absolutePosition) {

        final RandomItemViewHolder randomItemViewHolder = (RandomItemViewHolder) holder;

        randomItemViewHolder.mItem = mValues.get(section).get(relativePosition);
        randomItemViewHolder.item_random_title.setText(randomItemViewHolder.mItem.getTitle());

        if (!TextUtils.isEmpty(randomItemViewHolder.mItem.getImage())) {
            randomItemViewHolder.item_random_image.setImageUrl(randomItemViewHolder.mItem.getImage(), mImageLoader);
        } else {
            randomItemViewHolder.item_random_image.setBackgroundResource(android.R.color.transparent);
        }

        randomItemViewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface
                    mListener.onSelectItem(randomItemViewHolder.mItem, randomItemViewHolder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemViewType(int section, int relativePosition, int absolutePosition) {
        if (section == 1) {
            return 0; // VIEW_TYPE_HEADER is -2, VIEW_TYPE_ITEM is -1. You can return 0 or greater.
        }
        return super.getItemViewType(section, relativePosition, absolutePosition);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout;
        switch (viewType) {
            case VIEW_TYPE_HEADER: {
                layout = R.layout.item_header;
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(layout, parent, false);
                return new HeaderItemViewHolder(view);
            }
            case VIEW_TYPE_ITEM:
            default: {
                layout = R.layout.item_random;
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(layout, parent, false);
                return new RandomItemViewHolder(view);
            }
        }
    }

    public void addItem(int section, RandomItem item) {
        if (mValues.get(section) != null) {
            mValues.get(section).add(0, item);
        }
        notifyItemInserted(0);
    }
}
