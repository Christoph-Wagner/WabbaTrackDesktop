package com.ediposouza.util

import com.ediposouza.TESLTracker
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

/**
 * Created by ediposouza on 06/03/17.
 */
abstract class BaseRecognizeTests {

    protected fun getFileImage(testFileName: String): BufferedImage {
        val currentResolution = TESLTracker.referenceConfig.SCREEN_REFERENCE
        val image = ImageIO.read(TESLTracker::class.java.getResource("/Test$currentResolution/$testFileName"))
//        val reference = TESLTracker.referenceConfig.SCREEN_REFERENCE
//        Logger.i("Using $reference as screen reference with Image size: ${image.width}x${image.height}")
        return image
    }

    protected fun recognizeImage(image: BufferedImage, dHashMap: Map<String, String>, outputFile: Boolean = false): String? {
        if (outputFile) {
            val tmpFileName = "recognize_${System.currentTimeMillis()}.png"
            val cropFolderPath = File(File(TESLTracker.jarPath).parentFile, "data/crops").apply {
                if (!exists()) {
                    mkdir()
                }
            }
            ImageIO.write(image, "png", File(cropFolderPath, "Tmp/$tmpFileName"))
        }
        return Recognizer.recognizeImageInMap(image, dHashMap)
    }

}