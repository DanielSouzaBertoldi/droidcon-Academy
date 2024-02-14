package daniel.bertoldi.materialstudynotes.ui.theme

import android.os.Build
import androidx.compose.material3.Typography
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import daniel.bertoldi.materialstudynotes.R


val staticOpenSansFontFamily = FontFamily(
    Font(R.font.opensans_static_light, FontWeight.Light),
    Font(R.font.opensans_static_regular, FontWeight.Normal),
    Font(R.font.opensans_static_medium, FontWeight.Medium),
    Font(R.font.opensans_static_semi_bold, FontWeight.SemiBold),
    Font(R.font.opensans_static_bold, FontWeight.Bold),
    Font(R.font.opensans_static_extra_bold, FontWeight.ExtraBold),
    Font(R.font.opensans_static_light_italic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.opensans_static_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.opensans_static_medium_italic, FontWeight.Medium, FontStyle.Italic),
    Font(R.font.opensans_static_semi_bold_italic, FontWeight.SemiBold, FontStyle.Italic),
    Font(R.font.opensans_static_bold_italic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.opensans_static_extra_bold_italic, FontWeight.ExtraBold, FontStyle.Italic),
)

@OptIn(ExperimentalTextApi::class)
val displayLarge = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
    FontFamily(
        Font(
            resId = R.font.opensans_variable,
            variationSettings = FontVariation.Settings(
                FontVariation.weight(950),
                FontVariation.width(30f),
                FontVariation.slant(-6f),
            )
        ),
        Font(
            resId = R.font.opensans_variable_italic,
            variationSettings = FontVariation.Settings(
                FontVariation.weight(950),
                FontVariation.width(500f),
                FontVariation.slant(60f),
            ),
            style = FontStyle.Italic,
        ),
    )
} else {
    staticOpenSansFontFamily
}

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = staticOpenSansFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(fontFamily = displayLarge),
    labelSmall = TextStyle(
        fontFamily = staticOpenSansFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    bodySmall = TextStyle(
        fontFamily = staticOpenSansFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)