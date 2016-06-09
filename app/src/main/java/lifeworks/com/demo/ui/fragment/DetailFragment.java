package lifeworks.com.demo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import lifeworks.com.demo.R;
import lifeworks.com.demo.business.model.RandomItem;
import lifeworks.com.demo.ui.activity.DetailActivity;


public class DetailFragment extends Fragment {

    private RandomItem mRandomItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Fetch the relevant RandomItem
        mRandomItem = getActivity().getIntent().getExtras().getParcelable(DetailActivity.ARG_RANDOM_ITEM);

        // If the relevant RandomItem != null, show its data
        if (mRandomItem != null) {
            TextView detail_full = (TextView) view.findViewById(R.id.detail_full);
            if (detail_full != null) {
                detail_full.setText(mRandomItem.getFullText());
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Set up the action/tool bar
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            if (mRandomItem != null) {
                actionBar.setTitle(mRandomItem.getTitle());
            }
        }
    }
}
