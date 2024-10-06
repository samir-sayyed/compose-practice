package com.gravty.chalhoub.views.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch


@Composable
fun SyncedTabsList(
    syncedTabsContent: List<SyncedTabData>,
    isTab: (SyncedTabData) -> Boolean,
    syncedTab: @Composable (SyncedTabData, Boolean) -> Unit,
    syncedItem: @Composable (SyncedTabData) -> Unit,
) {
    val tabs = syncedTabsContent.filter { isTab(it) }
    val indexes = hashMapOf<SyncedTabData, Int>()
    tabs.forEachIndexed { index, tab ->
        indexes[tab] = index
    }
    val selectedTabIndex = remember { mutableIntStateOf(0) }
    val verticalListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(syncedTabsContent, verticalListState) {
        snapshotFlow { verticalListState.firstVisibleItemIndex }
            .mapNotNull {
                val tabData = syncedTabsContent.getOrNull(it)
                indexes[tabData]
            }
            .distinctUntilChanged()
            .collectLatest {
                selectedTabIndex.intValue = it
            }
    }

    val scrollToItem = scroller(
        verticalListState = verticalListState,
        coroutineScope = coroutineScope,
        syncedTabsContent = syncedTabsContent,
    )

    Column {
        SyncedTabs(
            selectedTabIndex = selectedTabIndex.intValue,
            onTabSelected = {
                selectedTabIndex.intValue = it
            },
            scrollToItem = scrollToItem,
            tabs = tabs,
            syncedTab = syncedTab,
        )

        SyncedTabsItems(
            listState = verticalListState,
//            syncedTabsContent = syncedTabsContent,
            syncedTabsContent = syncedTabsContent.filter { !isTab(it) },
            syncedItem = syncedItem,
        )
    }
}

@Composable
private fun  SyncedTabs(
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    scrollToItem: (SyncedTabData) -> Unit,
    tabs: List<SyncedTabData>,
    syncedTab: @Composable (SyncedTabData, Boolean) -> Unit,
) {
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
                selectedContentColor =  Color.Blue,
                selected = selectedTabIndex == index,
                onClick = {
                    onTabSelected(index)
                    scrollToItem(item)
                },
            ) {
                syncedTab(item, selectedTabIndex == index)
            }
        }
    }
}

private fun scroller(
    verticalListState: LazyListState,
    coroutineScope: CoroutineScope,
    syncedTabsContent: List<SyncedTabData>,
): (SyncedTabData) -> Unit = {
    coroutineScope.launch {
        val tabIndex = syncedTabsContent.indexOf(it)
        verticalListState.animateScrollToItem(index = tabIndex)
    }
}

@Composable
private fun SyncedTabsItems(
    listState: LazyListState,
    syncedTabsContent: List<SyncedTabData>,
    syncedItem: @Composable (SyncedTabData) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        state = listState
    ) {
        syncedTabsContent.forEach {
            item {
                Box {
                    syncedItem(it)
                }
            }
        }
    }
}