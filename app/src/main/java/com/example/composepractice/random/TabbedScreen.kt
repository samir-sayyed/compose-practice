//package com.gravty.chalhoub.views.compose
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.itemsIndexed
//import androidx.compose.foundation.lazy.rememberLazyListState
//import androidx.compose.material3.ScrollableTabRow
//import androidx.compose.material3.Tab
//import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
//import androidx.compose.material3.Text
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.geometry.Rect
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.LayoutCoordinates
//import androidx.compose.ui.layout.boundsInRoot
//import androidx.compose.ui.layout.onGloballyPositioned
//import androidx.compose.ui.layout.positionInRoot
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.Font
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.gravty.chalhoub.R
//import com.gravty.chalhoub.models.TabItemData
//import dev.jeziellago.compose.markdowntext.MarkdownText
//
//@Composable
//fun TabbedScreen(
//    tabList: ArrayList<String>,
//    itemList: ArrayList<TabItemData>
//) {
//    var selectedTabIndex by remember { mutableIntStateOf(0) }
//    var tabClicked by remember { mutableStateOf(false) }
//    val listState = rememberLazyListState()
//
//    Column {
//        ScrollableTabRow(
//            selectedTabIndex = selectedTabIndex,
//            edgePadding = 0.dp,
//            indicator = { tabPositions ->
//                Box(
//                    Modifier
//                        .tabIndicatorOffset(tabPositions[selectedTabIndex])
//                        .height(1.dp)
//                        .padding(end = 20.dp)
//                        .background(color = colorResource(id = R.color.muse_text_color))
//                )
//            }
//        ) {
//            tabList.forEachIndexed { index, item ->
//                Tab(
//                    modifier = Modifier.background(color = Color.White),
//                    unselectedContentColor = Color(0xFF868686),
//                    selectedContentColor = colorResource(id = R.color.muse_text_color),
//                    selected = selectedTabIndex == index,
//                    onClick = {
//                        selectedTabIndex = index
//                        tabClicked = true
//                    },
//                ) {
//                    Text(
//                        modifier = Modifier
//                            .padding(16.dp)
//                            .fillMaxWidth(),
//                        text = item,
//                        fontSize = 14.sp,
//
//                        )
//                }
//            }
//        }
//
//        LazyColumn(state = listState) {
//            itemsIndexed(tabList) { index, _ ->
//                val isSelected = remember {
//                    mutableStateOf(selectedTabIndex == index)
//                }
//                if (isSelected.value) {
//                    LaunchedEffect(selectedTabIndex) {
//                        listState.animateScrollToItem(index)
//                        tabClicked = false
//                    }
//                }
//                val item = itemList[index]
//                Column(
//                    modifier = Modifier
//                        .isVisible(50) {
//                            if (it && !tabClicked) {
//                                selectedTabIndex = index
//                                tabClicked = false
//                            }
//                        }
//                ) {
//                    Spacer(
//                        modifier = Modifier
//                            .height(4.dp)
//                            .fillMaxWidth()
//                            .background(color = androidx.compose.ui.graphics.Color(0xFFE5E1E6))
//                    )
//                    item.title?.let {
//                        Text(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(bottom = 10.dp, start = 15.dp, end = 15.dp, top = 25.dp),
//                            text = it,
//                            fontSize = 18.sp,
//                            fontFamily = FontFamily(Font(R.font.playfair_bold)),
//                            color = colorResource(id = R.color.muse_text_color)
//                        )
//                    }
//                    Spacer(
//                        modifier = Modifier
//                            .height(1.dp)
//                            .fillMaxWidth()
//                            .padding(horizontal = 15.dp)
//                            .background(color = colorResource(id = R.color.border_grey))
//
//                    )
//
//                    item.description?.let { it1 ->
//                        MarkdownText(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(
//                                    top = 10.dp,
//                                    start = 15.dp,
//                                    end = 15.dp,
//                                    bottom = 30.dp
//                                ),
//                            markdown = it1,
//                            fontResource = R.font.muli_regular,
//                            style = TextStyle(
//                                fontSize = 14.sp,
//                                textAlign = TextAlign.Justify
//                            ),
//                        )
//                    }
//
//                }
//            }
//        }
//    }
//}
//
//fun Modifier.isVisible(
//    threshold: Int,
//    onVisibilityChange: (Boolean) -> Unit
//): Modifier {
//
//    return this then Modifier.onGloballyPositioned { layoutCoordinates: LayoutCoordinates ->
//        val layoutHeight = layoutCoordinates.size.height
//        val thresholdHeight = layoutHeight * threshold / 100
//        val layoutTop = layoutCoordinates.positionInRoot().y
//        val layoutBottom = layoutTop + layoutHeight
//
//        // This should be parentLayoutCoordinates not parentCoordinates
//        val parent =
//            layoutCoordinates.parentLayoutCoordinates
//
//        parent?.boundsInRoot()?.let { rect: Rect ->
//            val parentTop = rect.top
//            val parentBottom = rect.bottom
//
//            if (
//                parentBottom - layoutTop > thresholdHeight &&
//                (parentTop < layoutBottom - thresholdHeight)
//            ) {
//                onVisibilityChange(true)
//            } else {
//                onVisibilityChange(false)
//
//            }
//        }
//    }
//}
