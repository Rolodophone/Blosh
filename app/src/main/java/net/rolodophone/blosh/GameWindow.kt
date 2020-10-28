package net.rolodophone.blosh

import android.graphics.RectF
import net.rolodophone.core.*
import kotlin.math.abs
import kotlin.random.Random.Default.nextInt

val entities = mutableListOf<Entity>()
val timers = mutableListOf<Timer>()

class GameWindow(ctx: MainActivityCore): Window(ctx) {

    private val leftSeekable = object: Seekable(RectF(0f, 0f, width/2, height)) {
        override fun onFling(vx: Float, vy: Float) {
            when {
                vy < 0 && -vy > abs(vx) -> Player.jumpIfOnGround() // fling up
                vx > 0 && vx > abs(vy) -> Player.advance() // fling right
                vy > 0 && vy > abs(vx) -> Player.rollOrDrop() // fling down
            }
        }
        override fun onSeek(x: Float, y: Float) {}
        override fun onStopSeek() {}
    }

    private val rightButton = Button(RectF(width/2, 0f, width, height)) {
        Player.punch()
    }

    override val downButtons = mutableListOf(rightButton)
    override val seekables = listOf<Seekable>(leftSeekable)

    override fun update() {
        Player.update()
        for (entity in entities) entity.update()
        for (button in downButtons) button.update()
        for (seekable in seekables) seekable.update()

        // spawn entities
        when {
            nextInt((5 * fps).toInt()) == 0 -> entities.add(Zombie())
        }

        //update timers
        timers.removeAll { it.timeLeft == 0f } // remove timers that were finished early by the player or the entities
        for (timer in timers) timer.update()
        timers.removeAll { it.timeLeft == 0f } // remove timers that were finished because they reached 0
    }

    override fun draw() {
        Background.draw()
        Ground.draw()
        Player.draw()
        for (entity in entities) entity.draw()
        for (button in downButtons) button.draw()
        for (seekable in seekables) seekable.draw()
    }
}