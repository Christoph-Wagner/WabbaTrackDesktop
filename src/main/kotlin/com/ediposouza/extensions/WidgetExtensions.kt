package com.ediposouza.extensions

import javafx.scene.control.Control
import javax.swing.JFrame

/**
 * Created by Edipo on 26/03/2017.
 */

fun Control.makeDraggable(frame: JFrame) {

    class DragContext {
        var x: Double = 0.toDouble()
        var y: Double = 0.toDouble()
    }

    val dragDelta = DragContext()
    setOnMousePressed({ mouseEvent ->
        dragDelta.x = frame.location.getX() - mouseEvent.screenX
        dragDelta.y = frame.location.getY() - mouseEvent.screenY
    })
    setOnMouseDragged({ mouseEvent ->
        val newX = (mouseEvent.screenX + dragDelta.x).toInt()
        val newY = (mouseEvent.screenY + dragDelta.y).toInt()
        frame.setLocation(newX, newY)
    })

}
