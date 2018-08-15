package name.huatong.dtwl.utils;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.Flushable;
import java.io.IOException;

/**
 * Created by tong.hua on 2018/8/8.
 */
public class MyIOUtils extends IOUtils {


    private static Logger logger = LoggerFactory.getLogger(MyIOUtils.class);

    public static void flush(Flushable flushable) {
        try {
            if (flushable != null)
                flushable.flush();
        } catch (IOException e) {
            // 忽略错误
            logger.warn("flushable.flush() eror!", e);
        }
    }

    /**
     * @param content
     * @param filePath
     */
    public static boolean write(String content, String filePath, boolean ignoreExists)
            throws IOException {
        File f = new File(filePath);
        if (f.exists() && ignoreExists)
            return true;

        new File(f.getParent()).mkdirs();
        f.createNewFile();
        FileWriter fw = new FileWriter(f);
        MyIOUtils.write(content, fw);
        fw.flush();
        MyIOUtils.closeQuietly(fw);
        return true;
    }
}
