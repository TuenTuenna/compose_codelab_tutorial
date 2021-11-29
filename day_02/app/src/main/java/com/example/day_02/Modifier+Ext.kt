package com.example.day_02

import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout


fun Modifier.customLayoutModifier() : Modifier {
    return this.then(

        layout { measurable, constraints ->

            val placeable = measurable.measure(constraints)

//            // Check the composable has a first baseline
//            check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
//            val firstBaseline = placeable[FirstBaseline]
//
//            // Height of the composable with padding - first baseline
//            val placeableY = firstBaselineToTop.roundToPx() - firstBaseline
//            val height = placeable.height + placeableY
            layout(placeable.width, placeable.height) {
                // Where the composable gets placed
                placeable.placeRelative(0, 0)
            }
    })
}
