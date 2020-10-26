package net.rolodophone.blosh

import android.graphics.Color
import net.rolodophone.core.canvas

class Background(private val window: GameWindow) {

    fun update() {
    }

    fun draw() {
        canvas.drawColor(Color.rgb(72, 202, 228))
    }
}