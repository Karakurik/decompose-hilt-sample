package com.example.myapplication.repository

import javax.inject.Inject

class Repository @Inject constructor() {

    private val itemMap: Map<String, Item> =
        List(100) { index ->
            Item(
                id = index.toString(),
                title = "Item $index",
                text = "Item $index. ".repeat(1000),
            )
        }.associateBy(Item::id)

    fun getItems(): List<Item> =
        itemMap.values.toList()

    fun getItem(id: String): Item =
        itemMap.getValue(key = id)
}
