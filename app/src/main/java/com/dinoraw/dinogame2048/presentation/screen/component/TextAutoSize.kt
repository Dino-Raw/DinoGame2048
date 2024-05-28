package com.dinoraw.dinogame2048.presentation.screen.component

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphIntrinsics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.createFontFamilyResolver
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import com.dinoraw.dinogame2048.presentation.ui.DinoGame2048Theme


@Composable
fun TextAutoSize(
    text: AnnotatedString,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    style: TextStyle = TextStyle.Default,
) {
    BoxWithConstraints(
        modifier = modifier
    ) {
        var compressedFontSize = style.fontSize
        val calculateTexWidth: @Composable () -> Float = @Composable {
            ParagraphIntrinsics(
                text = text.text,
                style = style.copy(fontSize = compressedFontSize),
                density = LocalDensity.current,
                fontFamilyResolver = createFontFamilyResolver(LocalContext.current)
            ).maxIntrinsicWidth
        }

        var textWidth = calculateTexWidth()
        with(LocalDensity.current) {
            while (textWidth > maxWidth.toPx()) {
                compressedFontSize *= 0.95f
                textWidth = calculateTexWidth()
            }
        }

        Text(
            text = text,
            color = color,
            maxLines = maxLines,
            fontStyle = fontStyle,
            fontWeight = fontWeight,
            fontFamily = fontFamily,
            letterSpacing = letterSpacing,
            textDecoration = textDecoration,
            textAlign = textAlign,
            lineHeight = lineHeight,
            overflow = overflow,
            fontSize = compressedFontSize,
            softWrap = false,
            style = style.copy(fontSize = compressedFontSize)
        )
    }
}

@Composable
fun TextAutoSize(
    text: String,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    style: TextStyle = TextStyle.Default,
) {
    TextAutoSize(
        modifier = modifier,
        text = AnnotatedString(text),
        color = color,
        maxLines = maxLines,
        fontStyle = fontStyle,
        fontWeight = fontWeight,
        fontFamily = fontFamily,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        lineHeight = lineHeight,
        overflow = overflow,
        style = style
    )
}

@Preview(showSystemUi = true)
@Composable
private fun TextResizePreview() {
    DinoGame2048Theme {
        Surface(color = MaterialTheme.colorScheme.primary) {
            TextAutoSize(
                text = ("This is a bunch of text that will be auto sized"),
                style = MaterialTheme.typography.headlineLarge,
            )
        }
    }
}