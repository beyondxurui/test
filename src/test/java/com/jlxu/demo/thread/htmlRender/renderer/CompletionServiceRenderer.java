package com.jlxu.demo.thread.htmlRender.renderer;

import com.jlxu.demo.thread.htmlRender.entity.ImageData;
import com.jlxu.demo.thread.htmlRender.entity.ImageInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 功能：多线程渲染器（异构并行，同构并行）
 * 创建时间：2020年03月28日
 * 文件名称：FutureRenderer
 * 版本：1.0.0
 * 最后修改时间：2020/3/28 21:39
 *
 * @auther jlxu
 */
public class CompletionServiceRenderer implements HtmlRenderer {
    private final ExecutorService exe = Executors.newCachedThreadPool();

    public void renderPage(String source) throws Exception {
        List<ImageInfo> infos = scanForImageInfo();

        CompletionService<ImageData> completionService = new ExecutorCompletionService<>(exe);

        for (final ImageInfo info : infos) {
//            Callable callable = () -> info.downloadImage();
            completionService.submit(() ->
                    info.downloadImage()
            );
        }
        renderText(source);

        for (int t = 0, n = infos.size(); t < n; t++) {
            Future<ImageData> f = completionService.take();
            ImageData image = f.get();
            rendererImage(image);
        }
    }
}
