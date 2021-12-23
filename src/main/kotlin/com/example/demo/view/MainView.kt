package com.example.demo.view

import com.example.demo.app.Styles
import com.example.demo.model.Point
import com.example.demo.util.DrawUtils
import javafx.application.Platform
import javafx.geometry.Pos
import javafx.scene.canvas.GraphicsContext
import javafx.scene.paint.*
import tornadofx.*
import java.util.*

class MainView : View("Hello TornadoFX") {
    val timer = Timer()

    val isRun = booleanProperty()

    val pNums = intProperty()

    lateinit var context: GraphicsContext

    override val root = borderpane {
        top = hbox(10) {
//            label(title) {
//                addClass(Styles.heading)
//            }
            style {
                alignment = Pos.CENTER
                backgroundColor = MultiValue(arrayOf(LinearGradient(
                    0.0, 0.0, 1.0, 0.0, true, CycleMethod.NO_CYCLE,
                    Stop(0.0, Color(0.38, 0.3, 0.63, 1.0)),
                    Stop(1.0, Color(0.92, 0.68, 0.8, 1.0)))))
            }
            button("start") {
                action {
                    if (isRun.value) {
                        timer.cancel()
                        this.text = "Start"
                        isRun.value = false
                    } else {
//                        timer.schedule(task, 0, 100)
//                        this.text = "Pause"
//                        isRun.value = true
                        context.fill = Color.WHITE
                        context.clearRect(0.0, 0.0, 800.0, 600.0)
                        context.fill = Color.DARKGREEN
                        DrawUtils.drawLoop(320.0,100.0,context,1,3,10.0,10.0)
                    }
                }
            }
            label(pNums.stringBinding { "当前点数：$it" })
        }
        center = canvas(800.0, 600.0) {
            style {
                alignment = Pos.CENTER
                backgroundColor = MultiValue(arrayOf(LinearGradient(
                    0.0, 0.0, 1.0, 0.0, true, CycleMethod.NO_CYCLE,
                    Stop(0.0, Color(0.38, 0.3, 0.63, 1.0)),
                    Stop(1.0, Color(0.92, 0.68, 0.8, 1.0)))))
            }
            context = this.graphicsContext2D
            paddingAll = 30
        }
        primaryStage.setOnCloseRequest { timer.cancel() }
    }
    private val task = object : TimerTask() {
        var renderedList: MutableList<Point> = LinkedList()
        var syncLock = Any()
        override fun run() {
            Platform.runLater {
                val random = Random()
                val x = 100 + random.nextDouble() * 500
                val y = 100 + random.nextDouble() * 500
                val p = Point(x, y, Color.RED)
                // 锁住，防止其他线程修改
                synchronized(syncLock) {
                    // 添加历史记录
                    renderedList.add(p)
                    // 清屏
                    context.fill = Color.WHITE
                    context.clearRect(0.0, 0.0, 800.0, 600.0)
                    context.fill = p.color
                    // 渲染点
                    for (point in renderedList) {
                        context.fillOval(point.x, point.y, 20.0, 20.0)
                    }
                    pNums.value += 1
                    // 控制点的数量
                    if (renderedList.size > 120) {
                        renderedList.clear()
                        pNums.value = 0
                    }
                }
            }
        }
    }
}