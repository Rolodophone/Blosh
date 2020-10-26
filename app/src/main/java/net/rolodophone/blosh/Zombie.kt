package net.rolodophone.blosh

import android.graphics.Color
import android.graphics.RectF
import net.rolodophone.core.*

class Zombie: Entity() {

    companion object {
        val w = Player.w
        val h = Player.h
        val velocity = -w(50)
    }

    override val dim = RectF(width, Ground.h - h, width + w, Ground.h)

    override fun update() {
        super.update()
        dim.offset(velocity / fps, 0f)
    }

    override fun draw() {
        paint.color = Color.rgb(111, 29, 27)
        canvas.drawRect(dim, paint)
    }
}