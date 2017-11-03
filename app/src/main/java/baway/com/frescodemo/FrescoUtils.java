package baway.com.frescodemo;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

/**
 * Created by 贾秀坤 on 2017/11/3.
 * Fresco的简单封装
 * Universal Image Loader：一个强大的图片加载库，包含各种各样的配置，最老牌，使用也最广泛。
 * Picasso: Square出品，必属精品。和OkHttp搭配起来更配呦！
 * Volley ImageLoader：Google官方出品，可惜不能加载本地图片~
 * Fresco：Facebook出的，天生骄傲！不是一般的强大。
 * Glide：Google推荐的图片加载库，专注于流畅的滚动。
 * BitmapRegionDecoder  加载巨图，不压缩的  比如清明上河图等大图(需要配合收拾查看全图)
 */


/**
 * 普通显示(基本)
 */
public class FrescoUtils {
    public static void showFescoBitmap(SimpleDraweeView drawee, String url) {
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url))
                .setAutoRotateEnabled(true) //自动旋转
                .setProgressiveRenderingEnabled(false)  //是否支持渐进式加载
                .build();

        DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(drawee.getController())//可节省不必要的内存
                .setAutoPlayAnimations(true)
                .build();

        drawee.setController(draweeController);
    }

    /**
     * 普通显示  功能增加 看注释！
     *
     * @param context
     * @param drawees
     * @param url
     * @param drawId
     */
    public static void showFescoBitmap2(Context context, SimpleDraweeView drawees, String url, int drawId) {
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url))
                .setAutoRotateEnabled(true) //自动旋转
                .setProgressiveRenderingEnabled(false)  //是否支持渐进式加载
                .build();
        DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                .setTapToRetryEnabled(true)//加载失败时，会重试4次  本身自带的
                .setImageRequest(request)
                .setAutoPlayAnimations(true)
                .build();

        GenericDraweeHierarchyBuilder builder =//用来设置加载失败，加载中，加载前图片等效果
                new GenericDraweeHierarchyBuilder(context.getResources());
        GenericDraweeHierarchy hierarchy = builder
                .setFadeDuration(300)//加载中
                .setPlaceholderImage(context.getResources().getDrawable(drawId))  //加载前
                .setFailureImage(context.getResources().getDrawable(drawId)) //加载失败
                //      .setOverlays(overlaysList)
                .build();
        drawees.setHierarchy(hierarchy);
        drawees.setController(draweeController);
    }

    /**
     * 显示圆角
     *
     * @param draweeView
     * @param url
     */
    public static void showFescoRoundBitmap(SimpleDraweeView draweeView, String url) {

        DraweeController draweeController2 = Fresco.newDraweeControllerBuilder().setUri(Uri.parse(url)).setAutoPlayAnimations(true).build();
        draweeView.setController(draweeController2);
        RoundingParams roundingParams = RoundingParams.fromCornersRadius(7f);
        roundingParams.setRoundAsCircle(true);//圆圈 - 设置roundAsCircle为true
        roundingParams.setBorder(Color.parseColor("#ffffff"), 3);//显示边框的颜色
        //mRoundParams2.setOverlayColor(Color.parseColor("#ffffff"));//用来绘制非圆角颜色
        draweeView.getHierarchy().setRoundingParams(roundingParams);

    }

    /**
     * 解决图片压缩
     *
     * @param draweeView
     * @param url
     * @param width
     * @param height
     */
    public static void showFescoScrolBitmap(SimpleDraweeView draweeView, String url, int width, int height) {

        //压缩的宽高
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(url))
                .setResizeOptions(new ResizeOptions(width, height))
                .build();

        //实例控制类
        PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                .setOldController(draweeView.getController())
                .setImageRequest(request)
                .build();
        draweeView.setController(controller);
    }
}
