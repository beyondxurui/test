package com.jlxu.demo.thread.htmlRender.renderer;

import com.jlxu.demo.thread.htmlRender.entity.ImageData;
import com.jlxu.demo.thread.htmlRender.entity.ImageInfo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 功能：页面渲染接口
 * 创建时间：2020年03月28日
 * 文件名称：HtmlRenderer
 * 版本：1.0.0
 * 最后修改时间：2020/3/28 20:49
 *
 * @auther jlxu
 */
public interface HtmlRenderer {
    Logger logger = LoggerFactory.getLogger(HtmlRenderer.class);//从后面不提示的时候，试着从前面写看看
    int DEFAULT_ELE_NUM = 5;

    void renderPage(String source) throws Exception;

    default void renderText(String source) throws InterruptedException {
        logger.info("go into renderText");
        TimeUnit.SECONDS.sleep(1);
        logger.info("finish renderText");
    }

    default List<ImageInfo> scanForImageInfo() {
        logger.info("go into scanFoImageInfo");
        return Collections.nCopies(DEFAULT_ELE_NUM, new ImageInfo());
    }

    default void rendererImage(ImageData imageData) throws InterruptedException {
        logger.info("go into rendererImage");
        TimeUnit.SECONDS.sleep(1);
        logger.info("finish rendererImage");
    }


}
