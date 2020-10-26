package net.rolodophone.blosh

import android.graphics.Color
import android.graphics.RectF
import net.rolodophone.core.*

class Player(private val window: GameWindow) {

    val w = w(25)
    val h = w(50)

    val dim = RectF(w(60), window.ground.h - h, w(60) + w, window.ground.h)
    var velocityX = 0f
    var velocityY = 0f
    var accelerationY = 0f

    var isOnGround = true

    fun update() {
        velocityY += accelerationY / fps
        dim.offset(velocityX / fps, velocityY / fps)

        // reset velocity etc when character lands
        if (dim.bottom > window.ground.h) {
            dim.offsetTo(dim.left, window.ground.h - h)
            velocityY = 0f
            accelerationY = 0f
            isOnGround = true
        }
    }

    fun draw() {
        paint.color = Color.GRAY
        canvas.drawRect(dim, paint)
    }

    fun jump() {
        if (isOnGround) {
            accelerationY = w(1000)
            velocityY = -w(400)
            isOnGround = false
        }
    }
}