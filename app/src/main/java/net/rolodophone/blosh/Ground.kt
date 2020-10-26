package net.rolodophone.blosh

import android.graphics.Color
import net.rolodophone.core.*

object Ground {

    val h = height - w(25)

    fun draw() {
        paint.color = Color.rgb(43, 147, 72)
        canvas.drawRect(0f, h, width, height, paint)
    }
}