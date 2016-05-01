package com.antonioleiva.mvpexample.app;

import android.os.Build;

import com.antonioleiva.mvpexample.app.main.MainPresenter;
import com.antonioleiva.mvpexample.app.main.MainPresenterImpl;
import com.antonioleiva.mvpexample.app.main.MainView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricGradleTestRunner.class)
@Config(sdk = Build.VERSION_CODES.LOLLIPOP, constants = BuildConfig.class)
public class MainPresenterTest {

    @Mock
    MainView mainView;

    MainPresenter mainPresenter;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        mainPresenter = new MainPresenterImpl(mainView);
    }

    @Test
    public void shouldOnResume() throws Exception {
        mainPresenter.onResume();
        verify(mainView).showProgress();
    }

    @Test
    public void shouldOnItemClicked() throws Exception {
        int position = 0;
        mainPresenter.onItemClicked(position);
        verify(mainView).showMessage(String.format("Position %d clicked", position + 1));
    }

    @Test
    public void shouldOnDestroy() throws Exception {
        mainPresenter.onDestroy();
        verify(mainView).equals(Matchers.isNull());
    }

}
