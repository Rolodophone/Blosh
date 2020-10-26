package net.rolodophone.blosh

import net.rolodophone.core.fps

class Timer(duration: Float, val onFinish: () -> Unit) {
    var timeLeft = duration

    fun update() {
        timeLeft -= 1 / fps
        if (timeLeft < 0f) onFinish()
    }
}