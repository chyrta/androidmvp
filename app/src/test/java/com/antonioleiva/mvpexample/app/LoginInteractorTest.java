package com.antonioleiva.mvpexample.app;

import android.os.Build;

import com.antonioleiva.mvpexample.app.Login.LoginInteractor.OnLoginFinishedListener;
import com.antonioleiva.mvpexample.app.Login.LoginInteractorImpl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricGradleTestRunner.class)
@Config(sdk = Build.VERSION_CODES.LOLLIPOP, constants = BuildConfig.class)
public class LoginInteractorTest {

    @Mock
    OnLoginFinishedListener listener;
    @Mock
    LoginInteractorImpl loginInteractor;

    @Captor
    ArgumentCaptor<Void> someMethod;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void shouldTestSuccess() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                OnLoginFinishedListener listener = (OnLoginFinishedListener) invocation.getArguments()[0];
                listener.onSuccess();
                return null;
            }
        }).when(loginInteractor).login(any(String.class), any(String.class), any(OnLoginFinishedListener.class));

        loginInteractor.login(any(String.class), any(String.class), any(OnLoginFinishedListener.class));
        verify(listener).onSuccess();
    }

}
