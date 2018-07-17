package com.ilesanmi.oluwole.bakingapplication;

import com.ilesanmi.oluwole.bakingapplication.data.DataManager;
import com.ilesanmi.oluwole.bakingapplication.data.model.Recipe;
import com.ilesanmi.oluwole.bakingapplication.ui.main.MainActivity;
import com.ilesanmi.oluwole.bakingapplication.ui.main.MainPresenter;
import com.ilesanmi.oluwole.bakingapplication.utils.rx.SchedulerProvider;
import com.ilesanmi.oluwole.bakingapplication.utils.rx.TestSchedulerProvider;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.TestScheduler;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MainPresenterUnitTest {
    @Mock
    public DataManager dm;

    @Mock
    public MainActivity activity;

    @Mock
    Recipe mRecipe;

    private ArrayList<Recipe> recipeList;

    private TestScheduler testScheduler;

    private MainPresenter presenter;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        CompositeDisposable cp = new CompositeDisposable();

        recipeList = new ArrayList<>();
        recipeList.add(mRecipe);

        // Mock scheduler using RxJava TestScheduler.
        testScheduler = new TestScheduler();
        SchedulerProvider schedulerProvider = new TestSchedulerProvider(testScheduler);

        presenter = new MainPresenter(dm, schedulerProvider, cp);
        presenter.onAttach(activity);
    }

    @Test
    public void WhenDataIsAvailable_ViewsShouldUpdate() {
        //Simulate that we acquired an array-list from the internet.
        Mockito.doReturn(Flowable.just(recipeList)).when(dm).loadRecipes(false);

        //When the below is executed wait until loadRecipes method is called before triggering doReturn.
        presenter.onViewPrepared(false);

        //Provides schedulers to the presenter as required.
        testScheduler.triggerActions();

        //CrossCheck that the above instructions resulted in the execution of instruction verify.
        Mockito.verify(activity).updateViewInActivity(recipeList);

    }
}
