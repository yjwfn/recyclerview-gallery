package yjwfn.cn.gallery;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity implements ScalableCardHelper.OnPageChangeListener {

    @DrawableRes
    private  final int[] PHTOTS = {
            R.mipmap.photo_1,
            R.mipmap.photo_2,
            R.mipmap.photo_3,
            R.mipmap.photo_4,
            R.mipmap.photo_5
    };

    Bitmap[] BLURED_BITMAP = new Bitmap[PHTOTS.length];


    private ImageSwitcher backgroundView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        backgroundView = (ImageSwitcher) findViewById(R.id.image_switcher);
        Animation fadeIn = new AlphaAnimation(0.5f, 1);
        fadeIn.setDuration(500);
        backgroundView.setInAnimation(
                fadeIn
        );
        Animation fadeOut = new AlphaAnimation(0.5f, 0);
        fadeOut.setDuration(500);
        backgroundView.setOutAnimation(fadeOut);
        backgroundView.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                return imageView;
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_photo_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(new PhotoAdapter(PHTOTS));
        ScalableCardHelper cardHelper = new ScalableCardHelper(this);
        cardHelper.attachToRecyclerView(recyclerView);


    }

    @Override
    public void onPageSelected(int position) {

        Bitmap bitmap = BLURED_BITMAP[position];
        if(bitmap == null){
            bitmap = BitmapFactory.decodeResource(getResources(), PHTOTS[position]);
            bitmap = BitmapUtils.blurBitmap(bitmap,25 , getApplicationContext());
            BLURED_BITMAP[position] = bitmap;
        }


        backgroundView.setImageDrawable(new BitmapDrawable(getResources(),bitmap));
    }
}
