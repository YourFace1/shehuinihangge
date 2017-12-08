package bwie.imagecachestudy.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;

import bwie.imagecachestudy.bean.ImageBitmap;

/**
 * Created by la on 2017/11/8.
 */

public class CacheUtils {

    GetMemoryCache getMemoryCache;
    NetUtils netUtils;

    public CacheUtils() {
        getMemoryCache=new GetMemoryCache();
        netUtils=new NetUtils(getMemoryCache,handler);
    }

    //通过三级缓存加载图片
    public void getBitmap(String url, ImageView image){

        //第一级 从内存中获取
        Bitmap bitmap = getMemoryCache.getMemoryBitmap(url);
        if(bitmap!=null){
            Log.e("SSSS","内存中获取");
            image.setImageBitmap(bitmap);
            return;
        }else{
            //从本地获取
            bitmap = getFileBitmap(url);
            if(bitmap!=null){
                Log.e("SSSS","本地中获取");
                image.setImageBitmap(bitmap);
                return;
            }else{
                Log.e("SSSS","网络获取");
                netUtils.getHttpUtils(url,image);
            }
        }

    }
    //从本地获取
    public Bitmap getFileBitmap(String url){
        String name = url.substring(8,10);
        Log.e("SSSS",name+"文件名字");
        File file=new File(Environment.getExternalStorageDirectory().getPath(),"/a"+name+".jpg");
        if(file.exists()){
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            //存到内存中
            getMemoryCache.addMemoryBitmap(url,bitmap);
            return bitmap;
        }
       return null;
    }

    //handler用来接收 网络请求之后的图片  并且加载他
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
                ImageBitmap obj = (ImageBitmap) msg.obj;
                obj.getImageView().setImageBitmap(obj.getBitmap());
        }
    };
}
