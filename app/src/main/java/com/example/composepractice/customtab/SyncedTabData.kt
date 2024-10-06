package com.example.composepractice.customtab

sealed class SyncedTabData(open val title: String, open val description: String? = null) {

    data class Header(override val title: String) : SyncedTabData(title = title)

    data class Item(override val title: String, override val description: String) : SyncedTabData(title = title, description = description)
}