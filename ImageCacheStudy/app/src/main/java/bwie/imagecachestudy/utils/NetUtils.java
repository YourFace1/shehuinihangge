package bwie.imagecachestudy.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import bwie.imagecachestudy.bean.ImageBitmap;

/**
 * Created by la on 2017/11/8.
 */

public class NetUtils {
    public static final int SUCCESS=0;
    public static final int FAILED=1;
    private Handler handler;
    private GetMemoryCache getMemoryCache;
    private ExecutorService executorService;

    public NetUtils(GetMemoryCache getMemoryCache,Handler handler) {
        this.getMemoryCache = getMemoryCache;
        this.handler=handler;
        executorService= Executors.newFixedThreadPool(5);
    }
    //加载图片
    public void getHttpUtils(String url, ImageView image){
        executorService.execute(new MyRunnable(url,image));
    }

    class MyRunnable implements Runnable{
        String url;
        ImageView image;
        public MyRunnable(String url,ImageView image) {
            this.url = url;
            this.image=image;
        }

        @Override
        public void run() {
            try {
                URL url1=new URL(url);
                HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
                connection.setRequestMethod("GET");
                if(connection.getResponseCode()==200){

                    InputStream inputStream = connection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

                    ImageBitmap imageBitmap=new ImageBitmap(bitmap,image);
                    Message msg=new Message();
                    msg.what=0;
                    msg.obj=imageBitmap;
                    handler.sendMessage(msg);
                    //存入内存中
                    getMemoryCache.addMemoryBitmap(url,bitmap);

                    //存入本地文件中
                    String name = url.substring(8, 10);
                    File file=new File(Environment.getExternalStorageDirectory().getPath(),"/a"+name+".jpg");
                    BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(file));
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,bos);
                    bos.flush();
                    bos.close();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
