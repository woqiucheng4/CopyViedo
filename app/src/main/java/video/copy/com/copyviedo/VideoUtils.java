package video.copy.com.copyviedo;

/**
 * <ul>
 * <li>功能职责：</li>
 * </ul>
 *
 * @author chengqiu
 * @date 2017-09-07
 */

import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.util.Log;

import java.io.File;
import java.io.FileFilter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 获取本地视频的工具类
 */
public class VideoUtils {

    /**
     * 获取视频的缩略图
     * 先通过ThumbnailUtils来创建一个视频的缩略图，然后再利用ThumbnailUtils来生成指定大小的缩略图。
     * 如果想要的缩略图的宽和高都小于MICRO_KIND，则类型要使用MICRO_KIND作为kind的值，这样会节省内存。
     *
     * @param videoPath 视频的路径
     * @param width     指定输出视频缩略图的宽度
     * @param height    指定输出视频缩略图的高度度
     * @param kind      参照MediaStore.Images(Video).Thumbnails类中的常量MINI_KIND和MICRO_KIND。
     *                  其中，MINI_KIND: 512 x 384，MICRO_KIND: 96 x 96
     * @return 指定大小的视频缩略图
     */
    public static Bitmap getVideoThumbnail(String videoPath, int width, int height, int kind) {
        Bitmap bitmap = null;
        // 获取视频的缩略图
        bitmap = ThumbnailUtils.createVideoThumbnail(videoPath, kind);
        if (bitmap != null) {
            bitmap = ThumbnailUtils.extractThumbnail(bitmap, width, height, ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        }
        return bitmap;
    }

    private static String getDateTime(){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String str = sdf.format(d);
        return  str;
    }
    /**
     * 获取指定路径中的视频文件
     *
     * @param list 装扫描出来的视频文件实体类
     * @param file 指定的文件
     */
    public static void getVideoFile(final List<VideoInfo> list, File file) {// 获得视频文件
        final String time = getDateTime();
        Log.i("nnn", "getDateTime90=="+time);
        //获取该目录下的所有文件
        file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                String name = file.getName();
                int i = name.lastIndexOf('.');
                if (i != -1 && name.contains(time)) {
                    name = name.substring(i);//获取文件后缀名
                    if (name.equalsIgnoreCase(".mp4")  //忽略大小写
                            || name.equalsIgnoreCase(".3gp") //
                            || name.equalsIgnoreCase(".wmv") //
                            || name.equalsIgnoreCase(".ts") //
                            || name.equalsIgnoreCase(".rmvb") //
                            || name.equalsIgnoreCase(".mov") //
                            || name.equalsIgnoreCase(".m4v")//
                            || name.equalsIgnoreCase(".avi") //
                            || name.equalsIgnoreCase(".m3u8") //
                            || name.equalsIgnoreCase(".3gpp")//
                            || name.equalsIgnoreCase(".3gpp2") //
                            || name.equalsIgnoreCase(".mkv")//
                            || name.equalsIgnoreCase(".flv") //
                            || name.equalsIgnoreCase(".divx") //
                            || name.equalsIgnoreCase(".f4v") //
                            || name.equalsIgnoreCase(".rm")//
                            || name.equalsIgnoreCase(".asf") //
                            || name.equalsIgnoreCase(".ram") //
                            || name.equalsIgnoreCase(".mpg") //
                            || name.equalsIgnoreCase(".v8")//
                            || name.equalsIgnoreCase(".swf") //
                            || name.equalsIgnoreCase(".m2v")//
                            || name.equalsIgnoreCase(".asx") //
                            || name.equalsIgnoreCase(".ra") //
                            || name.equalsIgnoreCase(".ndivx") //
                            || name.equalsIgnoreCase(".xvid")) {
                        VideoInfo vi = new VideoInfo();
                        if (file.getName().contains(".")) {
                            String[] strs = file.getName().split("\\.");
                            vi.setDisplayName(strs[0]);//文件名
                        } else {
                            vi.setDisplayName(file.getName());//文件名
                        }
                        vi.setFilePath(file.getAbsolutePath());//文件路径
                        list.add(vi);
                        Log.i("nnn", "文件名:" + vi.getDisplayName() + ",路径:" + vi.getFilePath());
                        return true;
                    }
                } else if (file.isDirectory()) {
                    getVideoFile(list, file);
                }
                return false;
            }
        });
    }

    /**
     * 获取指定路径中的iso文件
     *
     * @param isolist 装扫描出来的iso文件实体类
     * @param file    指定的文件
     */
    public static void getISOFile(final List<VideoInfo> isolist, File file) {// 获得iso文件
        //获取该目录下的所有文件
        file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                // sdCard找到视频名称
                String name = file.getName();
                int i = name.lastIndexOf('.');
                if (i != -1) {
                    name = name.substring(i);//获取文件后缀名
                    if (name.equalsIgnoreCase(".iso")) {
                        VideoInfo vi = new VideoInfo();
                        if (file.getName().contains(".")) {
                            String[] strs = file.getName().split("\\.");
                            vi.setDisplayName(strs[0]);//文件名
                        } else {
                            vi.setDisplayName(file.getName());//文件名
                        }
                        vi.setFilePath(file.getAbsolutePath());//文件路径
                        isolist.add(vi);
                        Log.i("nnn", "iso文件名:" + vi.getDisplayName() + ",iso路径:" + vi.getFilePath());
                        return true;
                    }
                } else if (file.isDirectory()) {
                    getISOFile(isolist, file);
                }
                return false;
            }
        });
    }

}