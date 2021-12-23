package com.example.demo.app

import javafx.scene.paint.*
import javafx.scene.text.FontWeight
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        val heading by cssclass()
        val myBackgroundColor = MultiValue<Paint>(arrayOf(LinearGradient(
            0.0, 0.0, 1.0, 0.0, true, CycleMethod.NO_CYCLE,
            Stop(0.0, Color(0.38, 0.3, 0.63, 1.0)),
            Stop(1.0, Color(0.92, 0.68, 0.8, 1.0)))))
    }

    init {
        label and heading {
            padding = box(300.px)
            fontSize = 20.px
            fontWeight = FontWeight.BOLD
            backgroundColor = myBackgroundColor
        }
    }
}