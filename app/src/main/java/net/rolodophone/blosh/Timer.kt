package net.rolodophone.blosh

import net.rolodophone.core.fps

class Timer(duration: Float, val tag: Tag? = null, private val onFinish: () -> Unit) {

    enum class Tag {
        ROLL, PUNCH
    }

    var timeLeft = duration

    fun update() {
        timeLeft -= 1 / fps
        if (timeLeft <= 0f) finish()
    }

    fun finish() {
        onFinish()
        timeLeft = 0f
    }
}