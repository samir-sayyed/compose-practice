package com.example.composepractice.customtab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TabbedScreen(
) {
    val tabs = listOf("Tab 1", "Tab 2", "Tab 3")
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    var tabClicked by remember { mutableStateOf(false) }
    val listState = rememberLazyListState()

    Column {
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            edgePadding = 0.dp,
            indicator = { tabPositions ->
                Box(
                    Modifier
                        .tabIndicatorOffset(tabPositions[selectedTabIndex])
                        .height(1.dp)
                        .padding(end = 20.dp)
                        .background(color = Color.Blue)
                )
            }
        ) {
            tabs.forEachIndexed { index, item ->
                Tab(
                    modifier = Modifier.background(color = Color.White),
                    unselectedContentColor = Color(0xFF868686),
                    selectedContentColor = Color.Red,
                    selected = selectedTabIndex == index,
                    onClick = {
                        selectedTabIndex = index
                        tabClicked = true
                    },
                ) {
                    Text(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        text = item,
                        fontSize = 14.sp,

                    )
                }
            }
        }

        LazyColumn(state = listState) {
            itemsIndexed(tabs) { index, _ ->
                val isSelected = selectedTabIndex == index
                if (isSelected) {
                    // Scroll to this item if it's selected
                    LaunchedEffect(isSelected) {
                        listState.animateScrollToItem(index)
                        tabClicked =  false
                    }
                }
                Text(
                    text = "Content for Tab ${index + 1} Why do you use?\n" +
                            "The Lorem ipum filling text is used by graphic designers, programmers and printers with the aim of occupying the spaces of a website, an advertising product or an editorial production whose final text is not yet ready.\n" +
                            "This expedient serves to get an idea of the finished product that will soon be printed or disseminated via digital channels.\n" +
                            "\n" +
                            "In order to have a result that is more in keeping with the final result, the graphic designers, designers or typographers report the Lorem ipsum text in respect of two fundamental aspects, namely readability and editorial requirements.\n" +
                            "\n" +
                            "The choice of font and font size with which Lorem ipsum is reproduced answers to specific needs that go beyond the simple and simple filling of spaces dedicated to accepting real texts and allowing to have hands an advertising/publishing product, both web and paper, true to reality.\n" +
                            "\n" +
                            "Its nonsense allows the eye to focus only on the graphic layout objectively evaluating the stylistic choices of a project, so it is installed on many graphic programs on many software platforms of personal publishing and content management system.",
                    modifier = Modifier
                        .isVisible(80) {
                            if (it && !tabClicked){
                                selectedTabIndex = index
                                tabClicked = false
                            }
                        }
                        .background(color =Color(0xFF625b71))
                        .padding(16.dp),
                    color = Color.White
                )
                
                Spacer(modifier = Modifier.padding(16.dp))
            }
        }
    }
}

fun Modifier.isVisible(
    threshold: Int,
    onVisibilityChange: (Boolean) -> Unit
): Modifier  {

    return this then Modifier.onGloballyPositioned { layoutCoordinates: LayoutCoordinates ->
        val layoutHeight = layoutCoordinates.size.height
        val thresholdHeight = layoutHeight * threshold / 100
        val layoutTop = layoutCoordinates.positionInRoot().y
        val layoutBottom = layoutTop + layoutHeight

        // This should be parentLayoutCoordinates not parentCoordinates
        val parent =
            layoutCoordinates.parentLayoutCoordinates

        parent?.boundsInRoot()?.let { rect: Rect ->
            val parentTop = rect.top
            val parentBottom = rect.bottom

            if (
                parentBottom - layoutTop > thresholdHeight &&
                (parentTop < layoutBottom - thresholdHeight)
            ) {
                onVisibilityChange(true)
            } else {
                onVisibilityChange(false)

            }
        }
    }
}
