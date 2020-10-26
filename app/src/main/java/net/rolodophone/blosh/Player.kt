package net.rolodophone.blosh

import android.graphics.Color
import android.graphics.RectF
import net.rolodophone.core.*

object Player {

    val w = w(25)
    val h = w(50)
    val defaultX = w(60)
    val defaultY = Ground.h - h
    val runSpeed = w(150)

    val dim = RectF(defaultX, defaultY, defaultX + w, defaultY + h)
    var velocityX = 0f
    var velocityY = 0f
    var accelerationY = 0f

    var onGround = true
    var punching = false

    fun update() {
        velocityY += accelerationY / fps
        dim.offset(velocityX / fps, velocityY / fps)

        // reset velocity etc when character lands
        if (dim.bottom > Ground.h) {
            dim.offsetTo(dim.left, defaultY)
            velocityY = 0f
            accelerationY = 0f
            onGround = true
        }

        // go back to usual place after punching etc
        if (dim.left > defaultX + w(60)) {
            velocityX = -w(200)
        }

        // finish going back to usual place
        else if (dim.left < defaultX) {
            velocityX = 0f
        }
    }

    fun draw() {
        paint.color = Color.GRAY
        canvas.drawRect(dim, paint)
        if (punching) canvas.drawRect(dim.right, dim.top + w(13), dim.right + w(25), dim.top + w(18), paint)
    }

    fun jump() {
        if (onGround) {
            accelerationY = w(1000)
            velocityY = -w(400)
            onGround = false
        }
    }

    fun advance() {
        velocityX = w(600)
    }

    fun punch() {
        punching = true
        timers.add(Timer(0.5f) { punching = false })
    }
}