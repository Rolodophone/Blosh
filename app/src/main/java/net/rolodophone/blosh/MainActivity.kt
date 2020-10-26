package net.rolodophone.blosh

import net.rolodophone.core.MainActivityCore

class MainActivity: MainActivityCore(R.string.app_name, { ctx -> GameWindow(ctx) })