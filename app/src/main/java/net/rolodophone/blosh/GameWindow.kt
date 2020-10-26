package net.rolodophone.blosh

import android.graphics.RectF
import net.rolodophone.core.*
import kotlin.math.abs

class GameWindow(ctx: MainActivityCore): Window(ctx) {

    val background = Background(this)
    val ground = Ground(this)
    val player = Player(this)

    private val leftSeekable = object: Seekable(RectF(0f, 0f, width/2, height)) {
        override fun onFling(vx: Float, vy: Float) {
            when {
                // fling up - jump
                vy < 0 && -vy > abs(vx) -> player.jump()
            }
        }
        override fun onSeek(x: Float, y: Float) {}
        override fun onStopSeek() {}
    }

    override val seekables = listOf<Seekable>(leftSeekable)

    override fun update() {
        player.update()
        for (seekable in seekables) seekable.update()
    }

    override fun draw() {
        background.draw()
        ground.draw()
        player.draw()
        for (seekable in seekables) seekable.draw()
    }
}