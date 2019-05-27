package com.itechart.webflux.web.util

import java.io.ByteArrayInputStream
import java.io.File
import java.io.IOException
import java.util.*
import javax.imageio.ImageIO

class FileUtil {

    @Throws(IOException::class)
    fun saveBase64(base64: String?, folder: String, filename: String?, format: String): String? {
        if (base64 == null || filename == null) {
            return null
        }
        val split = base64.split("base64,".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        if (split.size != 2) {
            return null
        }
        val decode = Base64.getDecoder().decode(split[1])
        val bais = ByteArrayInputStream(decode)
        val image = ImageIO.read(bais)
        bais.close()
        val filePath = String.format("%s/%s.%s", folder, filename, format)
        val file = File(filePath)
        ImageIO.write(image, format, file)
        return filePath
    }

}