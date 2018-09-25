package com.recipe.kchinnak.searchrecipe

import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.recipe.kchinnak.searchrecipe.rxjavafiles.RxJavaPresenter
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailRecipeTest {

    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.recipe.kchinnak.searchrecipe", appContext.packageName)
        RxJavaPresenter(appContext).getDetailRecipe("35382")
    }
}