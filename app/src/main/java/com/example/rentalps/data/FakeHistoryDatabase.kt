package com.example.rentalps.data

object FakeHistoryDatabase {
    private val historyList = mutableListOf<PlayStationItem>()

    fun addToHistory(item: PlayStationItem) {
        historyList.add(item)
    }

    fun getHistory(): List<PlayStationItem> = historyList
}