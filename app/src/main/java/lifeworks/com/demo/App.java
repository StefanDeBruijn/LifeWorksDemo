package lifeworks.com.demo;

import android.app.Application;
import android.graphics.Bitmap.CompressFormat;

import lifeworks.com.demo.business.sync.RequestManager;
import lifeworks.com.demo.business.sync.volley.cache.ImageCacheManager;

public class App extends Application {

    // Quality is ignored but must be provided
    private static CompressFormat DISK_IMAGECACHE_COMPRESS_FORMAT = CompressFormat.PNG;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    /**
     * Initiate global systems
     */
    private void init() {
        RequestManager.init(this);
        createImageCache();
    }

    /**
     * Create the image cache. Uses Memory Cache by default. Change to Disk for
     * a Disk based LRU implementation.
     */
    private void createImageCache() {
        int DISK_IMAGECACHE_SIZE = 1024 * 1024 * 1000;
        int DISK_IMAGECACHE_QUALITY = 100;
        ImageCacheManager.getInstance().init(this, this.getPackageCodePath(), DISK_IMAGECACHE_SIZE, DISK_IMAGECACHE_COMPRESS_FORMAT,
                DISK_IMAGECACHE_QUALITY, ImageCacheManager.CacheType.MEMORY);
    }

}
