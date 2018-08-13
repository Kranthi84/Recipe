package com.recipe.kchinnak.searchrecipe


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.recipe.kchinnak.searchrecipe.DatabaseClasses.RecipeDatbase
import com.recipe.kchinnak.searchrecipe.DatabaseClasses.RecipeRoom
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class RecipeDaoTest {

    @Rule @JvmField
    val instanceTaskExecuteRule = InstantTaskExecutorRule()

    companion object {
        private var recipe: RecipeRoom = RecipeRoom("1", "publisher", "f2fUrl", "title", "sourceUrl", "imageUrl", 1.0, "publisherUrl")
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

        mDatabase?.recipeDao()?.insertOneRecipe(recipe)
        mDatabase?.recipeDao()!!.fetchRecipeById(recipeId = recipe.getRecipeId().toInt()).test()
                .assertValue() {
                    it.getRecipeId().toInt() == recipe.getRecipeId().toInt()
                }
    }

    @Test
    fun deleteAndGetRecipe() {
        mDatabase?.recipeDao()!!.deleteRecipe(recipe)
        mDatabase?.recipeDao()!!.fetchRecipeById(recipe.getRecipeId().toInt()).test()
                .assertNoValues()
    }
}