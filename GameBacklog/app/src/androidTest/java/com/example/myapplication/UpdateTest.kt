package com.example.myapplication


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class UpdateTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun updateTest() {
        // Create Game for Test
        onView(
                withId(R.id.fab)
        ).perform(click())
        onView(
                withId(R.id.name)
        ).perform(replaceText("Test Game"), closeSoftKeyboard())
        onView(
                withId(R.id.description)
        ).perform(replaceText("Test Description"), closeSoftKeyboard())
        onView(
                withId(R.id.completion)
        ).perform(replaceText("50"), closeSoftKeyboard())
        onView(
                withId(R.id.saveFab)
        ).perform(click())
        // Select Game
        onView(
                withId(R.id.games)
        ).perform(actionOnItemAtPosition<ViewHolder>(0, click()))
        // Edit Completion to 100%
        onView(
            allOf(
                withId(R.id.completion), withText("50.0"),
                isDisplayed()
            )
        ).perform(replaceText("100"))
        onView(
            allOf(
                withId(R.id.completion), withText("100"),
                isDisplayed()
            )
        ).perform(closeSoftKeyboard())
        onView(
            allOf(
                withId(R.id.completion), withText("100"),
                isDisplayed()
            )
        ).perform(pressImeActionButton())
        // Save Changes
        onView(
                withId(R.id.saveFab)
        ).perform(click())
        // Go to completed games.
        onView(
                withContentDescription("Open navigation drawer")
        ).perform(click())
        onView(
                withId(R.id.nav_gallery)
        ).perform(click())
        // Assert Game is there.
        onView(
            allOf(
                withId(R.id.name), withText("Test Game"),
                withParent(withParent(withId(R.id.games))),
                isDisplayed()
            )
        ).check(matches(isDisplayed()))
        // Assert game completion is 100%
        onView(
            allOf(
                withId(R.id.completion), withText("100.0 %"),
                withParent(withParent(withId(R.id.games))),
                isDisplayed()
            )
        ).check(matches(withText("100.0 %")))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
