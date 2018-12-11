package task.nts.com.ntsmobiletask;

import android.content.ClipData;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.core.app.ActivityScenario;
import task.nts.com.ntsmobiletask.pojo.PhotosModel;
import task.nts.com.ntsmobiletask.view.PhotosList;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class RecyclerViewTest {


    @Rule
    public ActivityTestRule<PhotosList> mActivityRule =
            new ActivityTestRule<>(PhotosList.class);


    @Test
    public void scrollTo_ItemTextMatches_Click() {

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.photo_list))
                .perform(RecyclerViewActions.actionOnItem(
                        hasDescendant(withText("officia porro iure quia iusto qui ipsa ut modi")),
                        click()));


    }

    @Test
    public void scrollTo_itemPosition() {

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.photo_list)).perform(RecyclerViewActions.scrollToPosition(30));
    }
    @Test
    public void scrollTo_itemPosition_click() {

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(ViewMatchers.withId(R.id.photo_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(30, click()));        // Match the text in an item below the fold and check that it's displayed.

    }
}