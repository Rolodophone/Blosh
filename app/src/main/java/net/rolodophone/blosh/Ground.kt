package net.rolodophone.blosh

import android.graphics.Color
import net.rolodophone.core.*

class Ground(private val window: GameWindow) {

    val h = height - w(25)

    fun draw() {
        paint.color = Color.GREEN
        canvas.drawRect(0f, h, width, height, paint)
    }
}