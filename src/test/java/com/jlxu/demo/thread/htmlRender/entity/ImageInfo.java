package com.jlxu.demo.thread.htmlRender.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * 功能：图片下载
 * 创建时间：2020年03月28日
 * 文件名称：ImageData
 * 版本：1.0.0
 * 最后修改时间：2020/3/28 21:05
 *
 * @auther jlxu
 */
public class ImageInfo {
    Logger logger = LoggerFactory.getLogger(ImageInfo.class);

    public ImageData downloadImage() throws InterruptedException {
        logger.info("go into downloadImage");
        TimeUnit.SECONDS.sleep(1);
        logger.info("finish downloadImage");
        return new ImageData();
    }
}
