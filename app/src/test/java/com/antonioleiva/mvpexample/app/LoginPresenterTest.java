package com.antonioleiva.mvpexample.app;

import android.os.Build;

import com.antonioleiva.mvpexample.app.Login.LoginPresenter;
import com.antonioleiva.mvpexample.app.Login.LoginPresenterImpl;
import com.antonioleiva.mvpexample.app.Login.LoginView;

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
public class LoginPresenterTest {

    @Mock
    LoginView loginView;

    LoginPresenter loginPresenter;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        loginPresenter = new LoginPresenterImpl(loginView);
    }

    @Test
    public void testName() throws Exception {
        loginPresenter.validateCredentials("login", "password");
        verify(loginView).showProgress();
    }

    @Test
    public void shouldOnDestroy() throws Exception {
        loginPresenter.onDestroy();
        verify(loginView).equals(Matchers.isNull());
    }

}
