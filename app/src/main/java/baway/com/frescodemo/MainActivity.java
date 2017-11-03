package baway.com.frescodemo;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * 贾秀坤
 * Fresco的强大在于3级缓存渐进式程序画面。
 */

public class MainActivity extends AppCompatActivity {

    private SimpleDraweeView draweeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        //创建simpleDraweeview的实例
        draweeView = (SimpleDraweeView) findViewById(R.id.main_adv);
        //创建要下载的url
      //  Uri uri = Uri.parse("http://www.ld12.com/upimg358/20160630/123600347932935.jpg");//错误的rul地址
         Uri uri = Uri.parse("http://www.ld12.com/upimg358/allimg/20160630/123600347932935.jpg");//正确的地址
        //开始下载
        draweeView.setImageURI(uri);

        //创建draweeController
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                //加载的图片URI地址
                .setUri(uri)
                //设置点击重试是否开启   需要注意的是重复加载4次如果还是加载不出来的话显示失败图
                .setTapToRetryEnabled(true)
                //设置旧的Controller
                .setOldController(draweeView.getController())
                //构建
                .build();
                //设置DraweeController
                draweeView.setController(controller);

    }
}
