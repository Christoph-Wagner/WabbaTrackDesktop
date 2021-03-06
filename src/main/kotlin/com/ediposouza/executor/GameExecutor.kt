package com.ediposouza.executor

import com.ediposouza.data.DHashCards
import com.ediposouza.data.PHash
import com.ediposouza.data.TESLTrackerData
import com.ediposouza.extensions.*
import com.ediposouza.model.Card
import com.ediposouza.model.DeckClass
import com.ediposouza.util.Recognizer
import java.awt.image.BufferedImage

/**
 * Created by Edipo on 27/03/2017.
 */
object GameExecutor {

    fun processPlayerGoFirst(screenshot: BufferedImage): Boolean? {
        return screenshot.getGamePlayerFirstCrop().let {
            Recognizer.recognizeImageInMap(it, PHash.GAME_ITEMS_LIST).equalsOrNull(PHash.PLAYER_GAME_FIRST)
        } ?: screenshot.getGamePlayerSecondCrop().let {
            Recognizer.recognizeImageInMap(it, PHash.GAME_ITEMS_LIST).equalsOrNull(PHash.PLAYER_GAME_SECOND)?.let { false }
        }
    }

    fun processPlayerDeckClass(screenshot: BufferedImage): DeckClass? {
        return screenshot.getGamePlayerClassCrop().let {
            Recognizer.recognizeImageInMap(it, PHash.GAME_PLAYER_CLASS_LIST)?.let {
                DeckClass.of(it)
            }
        }
    }

    fun processPlayerRank(screenshot: BufferedImage): Int? {
        return screenshot.getGamePlayerRankCrop().let {
            Recognizer.recognizeImageInMap(it, PHash.GAME_PLAYER_RANK_LIST)?.let(String::toIntOrNull)
        }
    }

    fun processOpponentDeckClass(screenshot: BufferedImage): DeckClass? {
        return screenshot.getGameOpponentClassCrop().let {
            Recognizer.recognizeImageInMap(it, PHash.GAME_OPPONENT_CLASS_LIST)?.let {
                DeckClass.of(it)
            }
        }
    }

    fun processOpponentRank(screenshot: BufferedImage): Int? {
        return screenshot.getGameOpponentRankCrop().let {
            Recognizer.recognizeImageInMap(it, PHash.GAME_PLAYER_RANK_LIST)?.let(String::toIntOrNull)
        }
    }

    fun processFirstCardDraws(screenshot: BufferedImage): Triple<String?, String?, String?> {
        val first = Recognizer.recognizeImageInMap(screenshot.getGameInitialCardDrawCrop(1), DHashCards.LIST)
        val second = Recognizer.recognizeImageInMap(screenshot.getGameInitialCardDrawCrop(2), DHashCards.LIST)
        val third = Recognizer.recognizeImageInMap(screenshot.getGameInitialCardDrawCrop(3), DHashCards.LIST)
        return Triple(first, second, third)
    }

    fun processCardDraw(screenshot: BufferedImage): Card? {
        return screenshot.getGameCardDrawCrop().let {
            TESLTrackerData.getCard(Recognizer.recognizeCardImage(it))
        }
    }

    fun processCardDrawProphecy(screenshot: BufferedImage): Card? {
        return screenshot.getGameCardDrawProphecyCrop().let {
            TESLTrackerData.getCard(Recognizer.recognizeCardImage(it))
        }
    }

    fun processCardGenerated(screenshot: BufferedImage): Boolean? {
        return screenshot.getGameCardGenerateCrop().let {
            Recognizer.recognizeScreenImage(it)?.let {
                it == PHash.SCREEN_GAME_CARD_GENERATED
            }
        }
    }

    fun processMatchEnd(screenshot: BufferedImage): Boolean? {
        return screenshot.getGameWinCrop().let {
            Recognizer.recognizeImageInMap(it, PHash.GAME_ITEMS_LIST).equalsOrNull(PHash.GAME_WIN)
        } ?: screenshot.getGameWin2Crop().let {
            Recognizer.recognizeImageInMap(it, PHash.GAME_ITEMS_LIST).equalsOrNull(PHash.GAME_WIN2)
        } ?: screenshot.getGameLossCrop().let {
            Recognizer.recognizeImageInMap(it, PHash.GAME_ITEMS_LIST).equalsOrNull(PHash.GAME_LOSS)?.let { false }
        } ?: screenshot.getGameLoss2Crop().let {
            Recognizer.recognizeImageInMap(it, PHash.GAME_ITEMS_LIST).equalsOrNull(PHash.GAME_LOSS2)?.let { false }
        }
    }

}