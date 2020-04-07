package com.jlxu.demo.thread.htmlRender.renderer;

import com.jlxu.demo.thread.htmlRender.entity.ImageData;
import com.jlxu.demo.thread.htmlRender.entity.ImageInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能：单线程渲染器
 * 创建时间：2020年03月28日
 * 文件名称：SingleThreadRenderer
 * 版本：1.0.0
 * 最后修改时间：2020/3/28 21:12
 *
 * @auther jlxu
 */
public class SingleThreadRenderer implements HtmlRenderer {

    public void renderPage(String source) throws Exception {
        renderText(source);

        List<ImageData> imageData = new ArrayList<>();
        for (ImageInfo imageInfo : scanForImageInfo()) {
            imageData.add(imageInfo.downloadImage());
        }

        for (ImageData image : imageData) {
            rendererImage(image);
        }
    }
}
