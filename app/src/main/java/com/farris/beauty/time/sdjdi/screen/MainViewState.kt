package com.farris.beauty.time.sdjdi.screen

import com.farris.beauty.time.sdjdi.screen.sample.SampleItem

data class MainViewState(
    val isProgress: Boolean = false,
    val items: List<SampleItem> = emptyList()
)
