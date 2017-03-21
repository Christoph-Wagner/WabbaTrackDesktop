package com.ediposouza

import com.ediposouza.extensions.getArenaClassSelectedCroppedImage
import com.ediposouza.extensions.getArenaPicksRemainingCroppedImage
import java.awt.image.BufferedImage
import java.io.File

/**
 * Created by ediposouza on 06/03/17.
 */
object CalcArenaDHash : BaseCalcDHash() {

    val CROP_FOLDER_ARENA = "Arena"
    val TYPE_PICKS_REMAINING = 0
    val TYPE_CLASS_SELECTED = 1

    @JvmStatic fun main(args: Array<String>) {
        getDHashFile(File(javaClass.getResource("/Arena/Pick.png").toURI()), CROP_FOLDER_ARENA, TYPE_PICKS_REMAINING)
        val cls = File(javaClass.getResource("/Arena/Class").toURI())
        getDHashFolderFiles(cls, cls.path, CROP_FOLDER_ARENA, TYPE_CLASS_SELECTED)
    }

    override fun getCroppedImage(fullImage: BufferedImage, type: Int): BufferedImage? {
        return when (type) {
            TYPE_PICKS_REMAINING -> fullImage.getArenaPicksRemainingCroppedImage()
            TYPE_CLASS_SELECTED -> fullImage.getArenaClassSelectedCroppedImage()
            else -> null
        }
    }
}