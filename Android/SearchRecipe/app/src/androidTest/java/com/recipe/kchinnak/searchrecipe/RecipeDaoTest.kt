package com.recipe.kchinnak.searchrecipe


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.recipe.kchinnak.searchrecipe.databaseClasses.RecipeDatbase
import com.recipe.kchinnak.searchrecipe.databaseClasses.RecipeRoom
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class RecipeDaoTest {

    @Rule
    @JvmField
    val instanceTaskExecuteRule = InstantTaskExecutorRule()

    companion object {
        private lateinit var recipeR: RecipeRoom
    }

    private var mDatabase: RecipeDatbase? = null

    @Before
    fun initDb() {
        mDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), RecipeDatbase::class.java)
                .allowMainThreadQueries()
                .build()
    }

    @After
    fun closeDb() {
        mDatabase.let { it?.close() }
    }

    @Test
    fun insertAndGetRecipe() {

        mDatabase?.recipeDao()?.insertOneRecipe(RecipeRoom("1", "pub", "f2f", "Title", "SrcUrl", "ImageUrl", 1.0, "pubUrl"))
        mDatabase?.recipeDao()!!.fetchRecipeById(recipeId = 1).test()
                .assertValue() {
                    it.getRecipeId().toInt() == 1
                }
    }

    @Test
    fun deleteAndGetRecipe() {

        mDatabase?.recipeDao()!!.deleteRecipe(RecipeRoom("1", "pub", "f2f", "Title", "SrcUrl", "ImageUrl", 1.0, "pubUrl"))
        mDatabase?.recipeDao()!!.fetchRecipeById(1).test()
                .assertNoValues()
    }
}