package lifeworks.com.demo.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lifeworks.com.demo.R;
import lifeworks.com.demo.business.listener.OnRecyclerViewInteractionListener;
import lifeworks.com.demo.business.model.RandomItem;
import lifeworks.com.demo.business.model.RandomItemFactory;
import lifeworks.com.demo.ui.activity.DetailActivity;
import lifeworks.com.demo.ui.adapter.RandomItemRecyclerViewAdapter;

public class MainActivityFragment extends Fragment implements OnRecyclerViewInteractionListener, SwipeRefreshLayout.OnRefreshListener {

    private RandomItemRecyclerViewAdapter mAdapter;
    private SwipeRefreshLayout mPullToRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set up the action bar
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.screen_demo);
        }

        // Set up the RecyclerView with random items and sections
        Map<Integer, List<RandomItem>> randomItems = new HashMap<>();
        randomItems.put(0, RandomItemFactory.createRandomItems(4));
        randomItems.put(1, RandomItemFactory.createRandomItems(3));
        randomItems.put(2, RandomItemFactory.createRandomItems(2));
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
        mAdapter = new RandomItemRecyclerViewAdapter(randomItems, this);
        recyclerView.setAdapter(mAdapter);
        // If we're in portrait orientation, we'll show a normal list of 1 row as width
        if (getResources().getBoolean(R.bool.orientation_is_portrait)) {
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        } else {
            // If we're in landscape orientation, we can show multiple items in a row, within a Grid
            GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(), 2);
            recyclerView.setLayoutManager(layoutManager);
            // We need to tell this library's adapter specifically about the usage of GridLayoutManager
            mAdapter.setLayoutManager(layoutManager);
        }

        // Set up the pull to refresh layout
        mPullToRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.ptr_layout);
        if (mPullToRefreshLayout != null) {
            mPullToRefreshLayout.setOnRefreshListener(this);
        }
    }

    private void startRefreshingAnimation() {
        mPullToRefreshLayout.setRefreshing(true);
    }

    private void stopRefreshAnimation() {
        mPullToRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onSelectItem(Object object, int objectPosition) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(DetailActivity.ARG_RANDOM_ITEM, (RandomItem) object);
        startActivity(intent);
    }

    @Override
    public void onRefresh() {
        startRefreshingAnimation();
        // Add a new item to the first section
        mAdapter.addItem(0, RandomItemFactory.createRandomItems(1).get(0));
        stopRefreshAnimation();
    }
}
