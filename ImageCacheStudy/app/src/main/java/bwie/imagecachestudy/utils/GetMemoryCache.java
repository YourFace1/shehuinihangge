package bwie.imagecachestudy.utils;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * 从内存中获取
 * Created by la on 2017/11/8.
 */

public class GetMemoryCache {

    private LruCache<String,Bitmap> map;

    public GetMemoryCache() {
        long maxsize = Runtime.getRuntime().maxMemory()/8;
        map=new LruCache<String,Bitmap>((int) maxsize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                int byteCount = value.getByteCount();
                return byteCount;
            }
        };
    }

    public Bitmap getMemoryBitmap(String url){
        return map.get(url);
    }
    //存入内存的方法
    public void addMemoryBitmap(String url,Bitmap bitmap){
        map.put(url,bitmap);
    }


}
