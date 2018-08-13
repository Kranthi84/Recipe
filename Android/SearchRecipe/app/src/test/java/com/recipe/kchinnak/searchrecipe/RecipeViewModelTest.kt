package com.recipe.kchinnak.searchrecipe

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.recipe.kchinnak.searchrecipe.DatabaseClasses.ImplementRecipeDataSource
import com.recipe.kchinnak.searchrecipe.DatabaseClasses.RecipeRoom
import org.junit.Rule
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock


class RecipeViewModelTest {

    @Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var mDataSource: ImplementRecipeDataSource

    @Captor
    lateinit var mRecipeArgumentCategory: ArgumentCaptor<RecipeRoom>

    lateinit var mRecipeViewModel: RecipeViewModel

}