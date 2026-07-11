package com.arjuna.offlineassistant.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color



data class myColors(
    val primary : Color,
    val background : Color,
    val userBubblebackground : Color,
    val botBubblebackground : Color,
    val botBubbleText : Color,
    val userBubbleText : Color,
    val floatingMenuBackground : Color,
    val floatingMenuText : Color,
    val bottomBarBackground : Color
)

val lightColors = myColors(
    primary = Color(0xFF10A37F),

    background = Color(0xFFF7F7F8),

    userBubblebackground = Color(0xFF10A37F),
    userBubbleText = Color(0xFFFFFFFF),

    botBubblebackground = Color(0xFFFFFFFF),
    botBubbleText = Color(0xFF1F1F1F),

    floatingMenuBackground = Color(0xFFFFFFFF),
    floatingMenuText = Color(0xFF1F1F1F),

    bottomBarBackground = Color(0xFFFFFFFF)
)

val darkColors = myColors(
    primary = Color(0xFF10A37F),

    background = Color(0xFF121212),

    userBubblebackground = Color(0xFF10A37F),
    userBubbleText = Color(0xFFFFFFFF),

    botBubblebackground = Color(0xFF2B2B2B),
    botBubbleText = Color(0xFFF5F5F5),

    floatingMenuBackground = Color(0xFF1E1E1E),
    floatingMenuText = Color(0xFFF5F5F5),

    bottomBarBackground = Color(0xFF1E1E1E)
)

val primaryLight = Color(0xFF10A37F)
val primaryDark = Color(0xFF10A37F)

val backgroundLight = Color(0xFFF7F7F8)
val backgroundDark = Color(0xFF121212)

val botBubbleDark = Color(0xFF2B2B2B)
val botBubbleLight = Color(0xFFFFFFFF)

val userBubbleLight = Color(0xFF10A37F)
val userBubbleDark = Color(0xFF10A37F)

val textPrimaryLight = Color(0xFF1F1F1F)
val textPrimaryDark = Color(0xFFF5F5F5)

val textSecondaryDark = Color(0xFFB0B0B0)
val textSecondaryLight = Color(0xFF6E6E73)

val borderLight = Color(0xFFE5E5E5)
val borderDark = Color(0xFF303030)


val LocalAppColors = staticCompositionLocalOf<myColors> { error("No colors provided") }

