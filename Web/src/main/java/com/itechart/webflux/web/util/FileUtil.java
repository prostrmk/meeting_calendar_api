package com.itechart.webflux.web.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class FileUtil {

    public static String saveBase64(String base64, String folder, String filename, String format) throws IOException {
        if (base64 == null || filename == null) {
            return null;
        }
        String[] split = base64.split("base64,");
        if (split.length != 2) {
            return null;
        }
        byte[] decode = Base64.getDecoder().decode(split[1]);
        ByteArrayInputStream bais = new ByteArrayInputStream(decode);
        BufferedImage image = ImageIO.read(bais);
        bais.close();
        String filePath = String.format("%s/%s.%s", folder, filename, format);
        File file = new File(filePath);
        ImageIO.write(image, format, file);
        return filePath;
    }

}
