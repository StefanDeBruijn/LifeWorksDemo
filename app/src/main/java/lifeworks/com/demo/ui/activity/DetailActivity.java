package lifeworks.com.demo.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;

import com.android.volley.toolbox.NetworkImageView;

import lifeworks.com.demo.R;
import lifeworks.com.demo.business.model.RandomItem;
import lifeworks.com.demo.business.sync.volley.cache.ImageCacheManager;

public class DetailActivity extends AppCompatActivity {

    public static final String ARG_RANDOM_ITEM = "extra://arg_random_item";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            ActionBar supportActionbar = getSupportActionBar();
            if (supportActionbar != null) {
                // Title in the toolbar
                supportActionbar.setDisplayShowTitleEnabled(true);
                // Show the CompetitionHQ logo
                supportActionbar.setDisplayShowHomeEnabled(true);
                //  supportActionbar.setDisplayUseLogoEnabled(true);
                supportActionbar.setHomeButtonEnabled(true);
                // supportActionbar.setLogo(R.drawable.toolbar_logo);
                supportActionbar.setDisplayHomeAsUpEnabled(true);
            }
        }

        RandomItem mRandomItem = getIntent().getExtras().getParcelable(DetailActivity.ARG_RANDOM_ITEM);
        if (mRandomItem != null) {

            NetworkImageView detail_image = (NetworkImageView) findViewById(R.id.detail_image);
            if (!TextUtils.isEmpty(mRandomItem.getImage()) && detail_image != null) {
                detail_image.setImageUrl(mRandomItem.getImage(), ImageCacheManager.getInstance().getImageLoader());
            }
        }
    }
}
