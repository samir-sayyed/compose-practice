import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch


@Composable
fun <T> SyncedTabsList(
    syncedTabsContent: List<T>,
    isTab: (T) -> Boolean,
    syncedTab: @Composable (T, Boolean) -> Unit,
    syncedItem: @Composable (T) -> Unit,
) {
    val tabs = syncedTabsContent.filter { isTab(it) }
    val indexes = syncedTabsContent.mapTabs(isTab = isTab)
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
            syncedTabsContent = syncedTabsContent,
            syncedItem = syncedItem,
        )
    }
}

fun <T> List<T>.mapTabs(isTab: (T) -> Boolean): Map<T, Int> = buildMap {
    var headerIndex = -1
    this@mapTabs.forEach {
        if (isTab(it)) {
            headerIndex++
        }
        this[it] = headerIndex
    }
}

@Composable
private fun <T> SyncedTabs(
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit,
    scrollToItem: (T) -> Unit,
    tabs: List<T>,
    syncedTab: @Composable (T, Boolean) -> Unit,
) {
    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        edgePadding = 0.dp
    ) {
        tabs.forEachIndexed { index, item ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = {
                    onTabSelected(index)
                    scrollToItem(item)
                }
            ) {
                syncedTab(item, selectedTabIndex == index)
            }
        }
    }
}

private fun <T> scroller(
    verticalListState: LazyListState,
    coroutineScope: CoroutineScope,
    syncedTabsContent: List<T>,
): (T) -> Unit = {
    coroutineScope.launch {
        val tabIndex = syncedTabsContent.indexOf(it)
        verticalListState.animateScrollToItem(index = tabIndex)
    }
}

@Composable
private fun <T> SyncedTabsItems(
    listState: LazyListState,
    syncedTabsContent: List<T>,
    syncedItem: @Composable (T) -> Unit,
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