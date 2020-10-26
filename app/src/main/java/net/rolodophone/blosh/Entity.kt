package net.rolodophone.blosh

import android.graphics.RectF
import androidx.annotation.CallSuper
import net.rolodophone.core.fps

abstract class Entity {

    abstract val dim: RectF

    @CallSuper
    open fun update() {
        dim.offset(-Player.runSpeed / fps, 0f)
    }

    abstract fun draw()
}