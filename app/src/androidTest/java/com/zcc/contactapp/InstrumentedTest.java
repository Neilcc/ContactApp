package com.zcc.contactapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;

import com.zcc.contactapp.contactmodel.repository.ContactDataBean;
import com.zcc.contactapp.contactmodel.repository.ContactDataRepository;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.zcc.contactapp", appContext.getPackageName());
    }

    @Test
    public void testDataRepository() {
        List<ContactDataBean> contactDataBeans = ContactDataRepository.loadContactDataSync();
        assertEquals(contactDataBeans.size(), 28);
        for (ContactDataBean contactDataBean : contactDataBeans) {
            assertFalse(TextUtils.isEmpty(contactDataBean.getAvatarFilename()));
            assertFalse(TextUtils.isEmpty(contactDataBean.getFirstName()));
            assertFalse(TextUtils.isEmpty(contactDataBean.getLastName()));
            assertFalse(TextUtils.isEmpty(contactDataBean.getTitle()));
            assertFalse(TextUtils.isEmpty(contactDataBean.getIntroduction()));
        }
    }
}
