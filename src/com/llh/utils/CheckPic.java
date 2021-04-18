package com.llh.utils;

import java.io.File;

//检查图片路径是否正确
public class CheckPic {
    public static boolean checkPic(String pic) {
        File file = new File(pic);
        //判断文件是否存在
        boolean picExist = file.exists();
        if (picExist == false) {
            return false;
        }

        // 获取文件后缀名并转化为写，用于后续比较
        String fileType = pic.substring(pic.lastIndexOf(".") + 1, pic.length()).toLowerCase();
        // 创建图片类型数组
        String img[] = {"bmp", "jpg", "jpeg", "png", "tiff", "gif", "pcx", "tga", "exif", "fpx", "svg", "psd",
                "cdr", "pcd", "dxf", "ufo", "eps", "ai", "raw", "wmf"};
        for (int i = 0; i < img.length; i++) {
            if (img[i].equals(fileType)) {
                return true;
            }
        }
        return false;

   }
}
