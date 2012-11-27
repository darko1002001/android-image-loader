package com.seavus.android.imageloader.sample.imageloadersample;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.seavus.android.imageloader.ImageLoader;
import com.seavus.android.imageloader.ImageLoaderListener;
import com.seavus.android.imageloader.sample.R;

public class HelloAndroidActivity extends Activity {

    private static String TAG = "ImageLoaderDemo";

    /**
     * Called when the activity is first created.
     * 
     * @param savedInstanceState If the activity is being re-initialized after previously being shut
     *        down then this Bundle contains the data it most recently supplied in
     *        onSaveInstanceState(Bundle). <b>Note: Otherwise it is null.</b>
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.main);

        Button buttonStart = (Button) findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(new OnStartClickListener());

        GridView grid = (GridView) findViewById(R.id.gridView);
        grid.setAdapter(new DemoArrayAdapter(this, mBitmapIds));
    }

    private final class OnStartClickListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            final ImageView imageView = (ImageView) findViewById(R.id.imageViewSingle);
            ImageLoader.getLoader(HelloAndroidActivity.this)
            .fetchBitmapAsync("http://images.nationalgeographic.com/wpf/media-live/photos/000/320/cache/japanese-macaques-nagano_32026_990x742.jpg",
                    new ImageLoaderListenerImplementation(imageView));
        }
    }

    private final class ImageLoaderListenerImplementation implements ImageLoaderListener {

        private final ImageView imageView;

        private ImageLoaderListenerImplementation(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        public void onImageLoadingError() {
            // TODO Auto-generated method stub
        }

        @Override
        public void onImageLoadingComplete(String url, Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);
        }
    }

    private class DemoArrayAdapter extends BaseAdapter {

        private final ImageLoader loader;
        private final String[] objects;
        private final LayoutInflater inflater;

        public DemoArrayAdapter(Context context, String[] objects) {
            this.objects = objects;
            loader = ImageLoader.getLoader(context);
            inflater = LayoutInflater.from(context);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = inflater.inflate(R.layout.image, null);
            loader.displayImage(getItem(position), (ImageView) view.findViewById(R.id.imageView));
            return view;
        }

        @Override
        public int getCount() {
            return objects.length;
        }

        @Override
        public String getItem(int position) {
            return objects[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }
    }

    public String[] mBitmapIds = {"http://www.kathiyavad.com/wallpaper/admin/upload/Hitman%20Face_1_wallpaper_1485.jpg",
            "http://automobiles.honda.com/images/2011/fit/downloads/cell-phone/fit_mobile_wallpaper1.jpg",
            "http://www.ecardmedia.eu/data/media/17/Grim_Reaper_Skeleton_Skull_320X480_iPhone_Mobile_Wallpaper.jpg",
            "http://www.pulsarmedia.eu/data/media/17/Mini_Cooper_Logo_320X480_iPhone_Mobile_Wallpaper.jpg",
            "http://www.realwallpapers.eu/data/media/17/Pink_Floyd_The_Wall_Prism_320X480_iPhone_Mobile_Wallpaper.jpg",
            "http://www4.images.coolspotters.com/wallpapers/108635/daft-punk-mobile-wallpaper.jpg",
            "http://www.pulsarmedia.eu/data/media/17/Lamborghini_Tach_320X480_iPhone_Mobile_Wallpaper.jpg",
            "http://fc07.deviantart.net/fs71/f/2011/207/7/e/noskill1343_dev_id_by_noskill1343-d3n6ngt.jpg",
            "http://media.ign.com/ign/image/gman_ftw.jpg",
            "http://media.comicvine.com/uploads/6/65639/1213160-mgs_solid_snake_super.jpg",
            "http://i.mobavatar.com/wallpaper//anime/android-400x800/android-samurai-android-400x800.jpg",
            "http://i.mobavatar.com/wallpaper//music/android-400x800/viva-la-vida-android-400x800.jpg",
            "http://i.mobavatar.com/wallpaper//anime/android-400x800/jehuty-android-400x800.jpg",
            "http://t0.gstatic.com/images?q=tbn:ANd9GcRkWHAtf5hF7X2qm0Ry9y_mOAswgaeMas9_uOqL2uDwfVVbh0rJ19jKJLLu",
            "http://www.journalgraphic.com/files/2011/01/Mike_Henry_34.jpg",
            "http://imgs.abduzeedo.com/files/articles/awesomeness-ninjas/2063566821_e6705614d7_o.jpg",
            "http://i.mobavatar.com/wallpaper//movie/android-400x800/the-darkknight-rises-android-400x800.jpg",
            "http://www.mppicture.com/uploads/allimg/2011/08/02/mpwall63824201108021005061.jpg",
            "http://i.mobavatar.com/wallpaper//anime/android-400x800/purple-meca-android-400x800.jpg",
            "http://i.mobavatar.com/wallpaper//android-400x800/sad-dog-android-400x800.jpg",
            "http://i.mobavatar.com/wallpaper//nature/android-400x800/red-panda-android-400x800.jpg",
            "http://fc06.deviantart.net/fs14/i/2007/026/e/4/The_Outside_Wallpaper_Pack_by_Baro.jpg",
            "http://images.wikia.com/aoc/images//9/95/Render_Guardian.jpg",
            "http://t3.gstatic.com/images?q=tbn:ANd9GcTUKyzdSPMHiPH6FoQo_1R276iszCe7dc2EBgjuuTwgAUY5kiYMyyvM0p6udA",
            "http://www.jpdigital.com/img/wallez/eiffel-tower-vertical.jpg",
            "http://chachatelier.fr/programmation/images/mozodojo-original-image.jpg",
            "http://www.htcwalls.com/wp-content/uploads/HTC-Abstract-Wallpaper-Set-6-11.jpg",
            "http://cdn5.droidmill.com/media/market-media/live.xrayhead.wallpaper_1.png",
            "http://cdn.androidtapp.com/wp-content/uploads/2011/12/Glow-Rotating-Live-Wallpaper.jpg",
            "http://www.lifewaresolutions.com/res/img/wallpaper/Deluxe_moon_wallpaper3_480_800.png",
            "http://cdn4.droidmill.com/media/market-media/com.tundraapps.SupermanLiveWallpaper_0.png",
            "http://www.clintonfitch.com/wp-content/uploads/2011/12/Lotus-Badge-Wallpaper.jpg",
            "http://www.1800pocketpc.com/blog/wp-content/uploads/2011/07/Superman-Wallpaper-WP7-9.jpg",
            "http://cdn4.droidmill.com/media/market-media/live.wallpaper.mobi.americanflag_1.png",
            "http://www.1800pocketpc.com/blog/wp-content/uploads/2011/11/Colorful-Wallpaper-05.jpg",
            "http://t0.gstatic.com/images?q=tbn:ANd9GcRg6Dis20Kk0aKhQZ0uGpA8xtavdkEftF26PiC3SxP2Rf0l7Ki9MUSyZFZ4-Q",
            "http://cdn.androidcentral.com/sites/androidcentral.com/files/articleimage/9274/2010/12/Nexus_S_live-wallpaper.png",
            "http://t1.gstatic.com/images?q=tbn:ANd9GcRKI_HAaNh8bk5-mef0HGmou-aRYZKJZDQ6ayR2VBJh5KeCCvmlb6LgbPJPYg",
            "http://www.junkinside.com/wp-content/uploads/2010/03/beautiful-wallpaper-for-samsung-i9000-galaxy-s-mobile.jpg",
            "http://www.graphiics.com/wp-content/uploads/2011/05/windows_7_wallpaper___phone_by_tempest790-d38a463.jpg",
            "http://www.bestandroiddownloads.com/img/WaterizeLiveWallpaper.png",
            "http://saga-lbc-mod.de/wp-content/uploads/2011/07/android-wallpaper-05.jpg",
            "http://dizorb.com/wp-content/uploads/2011/01/480x800-Dizorb-Scifi-Valley-HD-Wallpaper.jpg",
            "http://www.talkandroid.com/wp-content/uploads/2010/11/snap20101127_131030.png?3995d3",
            "http://getandroidstuff.com/wp-content/uploads/2010/12/Christmas-Magic-Live-Wallpaper-Android.jpg",
            "http://getandroidstuff.com/wp-content/uploads/2011/01/Luna-Stream-Live-Wallpaper-Android.jpg",
            "http://dizorb.com/wp-content/uploads/2010/08/480x800-Dizorb-TV-HD-Wallpaper.jpg",
            "http://dizorb.com/wp-content/uploads/2010/09/480x800-Dizorb-Morning-Rise-HD-Wallpaper.jpg",
            "http://dizorb.com/wp-content/uploads/2010/09/480x800_Dizorb_Heliograph_HD_Wallpaper3.jpg",
            "http://www.worstgeek.com/wp-content/uploads/2011/11/Samsung-Galaxy-Wallpaper4.jpg",
            "http://www.freexap.com/wp-content/uploads/2010/07/Wallpaper_16.jpg",
    "http://www.whitegadget.com/attachments/pc-wallpapers/25222d1235656623-nature-photos-wallpapers-images-beautiful-pictures-nature-mountains-photo.jpg"};
}
