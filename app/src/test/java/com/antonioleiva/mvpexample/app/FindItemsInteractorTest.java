package com.antonioleiva.mvpexample.app;

import android.os.Build;

import com.antonioleiva.mvpexample.app.main.FindItemsInteractor;
import com.antonioleiva.mvpexample.app.main.FindItemsInteractorImpl;

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

import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricGradleTestRunner.class)
@Config(sdk = Build.VERSION_CODES.LOLLIPOP, constants = BuildConfig.class)
public class FindItemsInteractorTest {

    @Mock
    FindItemsInteractor.OnFinishedListener listener;

    @Captor
    ArgumentCaptor<List<String>> captor;

    @Mock
    FindItemsInteractorImpl interactor;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void testSearch() throws Exception {
        final List<String> items = Arrays.asList(
                "Item 1",
                "Item 2",
                "Item 3",
                "Item 4",
                "Item 5",
                "Item 6",
                "Item 7",
                "Item 8",
                "Item 9",
                "Item 10"
        );

        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                FindItemsInteractor.OnFinishedListener callBack = (FindItemsInteractor.OnFinishedListener) invocation.getArguments()[0];
                callBack.onFinished(items);
                return null;
            }
        }).when(interactor).findItems(any(FindItemsInteractor.OnFinishedListener.class));

        interactor.findItems(listener);
        verify(interactor, times(1)).findItems(any(FindItemsInteractor.OnFinishedListener.class));
        verify(listener).onFinished(items);
    }


}
