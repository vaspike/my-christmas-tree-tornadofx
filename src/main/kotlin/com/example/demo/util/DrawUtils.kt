package com.example.demo.util

import com.example.demo.model.Point
import javafx.scene.canvas.GraphicsContext
import javafx.scene.paint.Color

class DrawUtils {
    val startNum = 1

    /**
     *
     * 获取渲染点
     * @param x 横坐标
     * @param y 纵坐标
     * @param color 颜色
     */
    fun getPoint(x: Double, y: Double, color: Color): Point {
        return Point(x, y, color)
    }
companion object{

    /**
     * TODO
     *
     * @param centerX 中轴线X轴位置
     * @param context GraphicsContext
     * @param rowStart 起始行
     * @param rowEnd 终止行
    //     * @param columnStart 起始列
    //     * @param columnEnd 终止列
     * @param weight 块宽度
     * @param height 块高度
     */
    fun drawLoop(
        centerX: Double,
        startY: Double,
        context: GraphicsContext,
        rowStart: Int,
        rowEnd: Int,
        weight: Double,
        height: Double,
    ) {
        var x = centerX
        var y = startY
        for (currentRow in rowStart..rowEnd) {
            for (currentColumn in 0 until currentRow * 2) {
                context.fillOval(x, y, weight, height)
                x += weight
//                Thread.sleep(1000)
            }
            x =centerX -currentRow * weight
            y += height
        }
    }
}
}