package net.rolodophone.blosh

import android.graphics.Color
import android.graphics.Rect
import android.graphics.RectF
import net.rolodophone.core.*

object Player {

    val defaultW = w(18)
    val defaultH = w(50)
    val defaultDim = RectF(w(60), Ground.h - defaultH, w(60) + defaultW, Ground.h)
    val runSpeed = w(150)

    val dim = RectF(defaultDim)
    var velocityX = 0f
    var velocityY = 0f
    var accelerationY = 0f

    var jumping = false
    var rolling = false

    var punching = false

    fun update() {
        velocityY += accelerationY / fps
        dim.offset(velocityX / fps, velocityY / fps)

        // reset velocity etc when character lands
        if (dim.bottom > Ground.h) {
            dim.offsetTo(dim.left, defaultDim.top)
            velocityY = 0f
            accelerationY = 0f
            jumping = false
        }

        // go back to usual place after punching etc
        if (dim.centerX() > defaultDim.centerX() + w(60)) {
            velocityX = -w(200)
        }

        // finish going back to usual place
        else if (dim.centerX() < defaultDim.centerX()) {
            velocityX = 0f
            //TODO reset x pos
        }
    }

    fun draw() {
        paint.color = Color.GRAY
        canvas.drawRect(dim, paint)
        if (punching) canvas.drawRect(dim.right, dim.top + w(13), dim.right + w(25), dim.top + w(18), paint)
    }

    fun jumpIfOnGround() {
        if (!jumping) {
            timers.find { it.tag == Timer.Tag.ROLL }?.finish()
            timers.find { it.tag == Timer.Tag.PUNCH }?.finish()

            accelerationY = w(1000)
            velocityY = -w(400)
            jumping = true
        }
    }

    fun rollOrDrop() {
        if (jumping) { // drop
            timers.find { it.tag == Timer.Tag.PUNCH }?.finish()
            
            velocityY = w(600)
        }
        else { // roll
            timers.find { it.tag == Timer.Tag.ROLL }?.finish()
            timers.find { it.tag == Timer.Tag.PUNCH }?.finish()
            
            val centerX = dim.centerX()
            dim.top = dim.bottom - w(28)
            dim.left = centerX - w(14)
            dim.right = centerX + w(14)

            rolling = true

            timers.add(Timer(1f, Timer.Tag.ROLL) {
                val xCenter = dim.centerX()
                dim.top = dim.bottom - defaultH
                dim.left = xCenter - defaultW/2
                dim.right = xCenter + defaultW/2

                rolling = false
            })
        }
    }

    fun advance() {
        velocityX = w(600)
    }

    fun punch() {
        punching = true

        timers.add(Timer(0.1f, Timer.Tag.PUNCH) {
            punching = false
        })
    }
}