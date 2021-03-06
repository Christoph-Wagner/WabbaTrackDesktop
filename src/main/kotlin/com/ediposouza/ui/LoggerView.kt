package com.ediposouza.ui

import com.ediposouza.TESLTracker
import com.ediposouza.util.Logger
import javafx.collections.FXCollections
import javafx.scene.control.ComboBox
import javafx.scene.control.TextArea
import javafx.scene.layout.BorderPane
import tornadofx.FX
import tornadofx.View
import tornadofx.singleAssign

/**
 * Created by Edipo on 18/03/2017.
 */
class LoggerView : View("WabbaTrack Log") {

    override val root = BorderPane()

    private val loggerController by inject<LoggerController>()
    private val logLevels = FXCollections.observableArrayList("All", "Info", "Debug", "Error")

    var textArea: TextArea by singleAssign()
    var logLevelView: ComboBox<String> by singleAssign()

    init {
        FX.primaryStage.icons += TESLTracker.legendsIcon
        textArea = TextArea().apply {
            isEditable = false
            isWrapText = true
            textProperty().addListener { _ ->
                textArea.scrollTop = Double.MAX_VALUE
            }
        }
        logLevelView = ComboBox<String>(logLevels).apply {
            selectionModel.select(1)
            setOnAction {
                val selectedIndex = logLevels.indexOf(selectionModel.selectedItem)
                loggerController.changeLogLevelView(Logger.Level.values()[selectedIndex])
            }
        }
        with(root) {
            top = logLevelView
            center = textArea
        }
    }

}