package com.jlxu.demo.thread.htmlRender.renderer;

import com.jlxu.demo.thread.htmlRender.entity.ImageData;
import com.jlxu.demo.thread.htmlRender.entity.ImageInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 功能：多线程渲染器(任务异构并行)
 * 创建时间：2020年03月28日
 * 文件名称：FutureRenderer
 * 版本：1.0.0
 * 最后修改时间：2020/3/28 21:39
 *
 * @auther jlxu
 */
public class FutureRenderer implements HtmlRenderer {
    private final ExecutorService exe = Executors.newCachedThreadPool();//不要static


    public void renderPage(String source) throws Exception {
        List<ImageInfo> imageInfos = scanForImageInfo();//解析在外面 ？
        Callable<List<ImageData>> task = () -> {
            List<ImageData> imageData = new ArrayList<>();
            for (ImageInfo imageInfo : imageInfos) {
                imageData.add(imageInfo.downloadImage());
            }
            return imageData;
        };
        Future<List<ImageData>> future = exe.submit(task);
        renderText(source);
        List<ImageData> imageData = future.get();//获取任务执行结果
        for (ImageData image : imageData) {
            rendererImage(image);
        }
    }
}
