package bwie.imagecachestudy.bean;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by la on 2017/11/8.
 */

public class ImageBitmap {
    private Bitmap bitmap;
    private ImageView imageView;

    public ImageBitmap(Bitmap bitmap, ImageView imageView) {
        this.bitmap = bitmap;
        this.imageView = imageView;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
