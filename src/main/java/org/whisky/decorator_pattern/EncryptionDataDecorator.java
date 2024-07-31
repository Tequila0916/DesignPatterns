package org.whisky.decorator_pattern;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @ClassName EncryptionDataDecorator
 * @Description TODO
 * @Author GT-R
 * @Date 2024/7/2下午4:43
 * @Version 1.0
 */
public class EncryptionDataDecorator extends DataLoaderDecorator {
    public EncryptionDataDecorator(DataLoader dataLoader) {
        super(dataLoader);
    }

    @Override
    public String read() {
        return decode(super.read());
    }

    private String decode(String read) {
        Base64.Decoder decoder = Base64.getDecoder();
        return new String(decoder.decode(read), StandardCharsets.UTF_8);

    }

    @Override
    public void write(String data) {
        super.write(encode(data));
    }

    private String encode(String data) {
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
        return encoder.encodeToString(bytes);
    }
}
