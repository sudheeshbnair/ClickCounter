package com.sb.clickcounter.di

import com.sb.clickcounter.ui.state.UiState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.flow.MutableStateFlow

@Module
@InstallIn(ViewModelComponent::class)
object ScreenStateModule {

    @Provides
    fun provideUiState(): MutableStateFlow<UiState> {
        return MutableStateFlow(UiState())
    }
}