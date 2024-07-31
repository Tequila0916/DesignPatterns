package org.whisky.decorator_pattern;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName BaseFileDataLoader
 * @Description TODO
 * @Author GT-R
 * @Date 2024/7/2下午4:35
 * @Version 1.0
 */
public class BaseFileDataLoader implements DataLoader{
    private String filePath;
    public BaseFileDataLoader(String filePath) {
        this.filePath = filePath;
    }
    @Override
    public String read() {
        String result = null;
        try {
            result = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public void write(String data) {
        try {
            FileUtils.write(new File(filePath),data,StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
