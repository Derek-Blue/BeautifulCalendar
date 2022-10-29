package com.farris.beauty.time.sdjdi.screen

import com.farris.beauty.time.sdjdi.module.repository.RepositoryLocationForecast

data class MainViewState(
    val isProgress: Boolean = true,
    val repositoryData: List<RepositoryLocationForecast> = emptyList()
)
