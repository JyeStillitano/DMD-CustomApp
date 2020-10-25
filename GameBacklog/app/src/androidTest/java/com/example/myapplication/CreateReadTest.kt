package com.example.myapplication


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class CreateReadTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun createReadTest() {
        // Click Plus/Add FAB
        onView(
            withId(R.id.fab)
        ).perform(click())
        // Name = Test Game
        onView(
            withId(R.id.name)
        ).perform(replaceText("Test Game"), closeSoftKeyboard())
        // Description = Test Description
        onView(
            withId(R.id.description)
        ).perform(replaceText("Test Description"), closeSoftKeyboard())
        // Completion = 50
        onView(
            withId(R.id.completion)
        ).perform(replaceText("50"), closeSoftKeyboard())
        // Done
        onView(
            allOf(
                withId(R.id.completion),
                withText("50"),
                isDisplayed()
            )
        ).perform(pressImeActionButton())
        // Save
        onView(
            allOf(
                withId(R.id.saveFab),
                isDisplayed()
            )
        ).perform(click())
        // Assert Game with Test Game is added.
        onView(
            allOf(
                withId(R.id.name), withText("Test Game"),
                withParent(withParent(withId(R.id.games))),
                isDisplayed()
            )
        ).check(matches(isDisplayed()))
        // Assert Game has correct completion percentage.
        onView(
            allOf(
                withId(R.id.completion), withText("50.0 %"),
                withParent(withParent(withId(R.id.games))),
                isDisplayed()
            )
        ).check(matches(isDisplayed()))
    }
}
