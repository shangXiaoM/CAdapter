package com.shangxiaom.commonlist;

import com.shangxiaom.commonlist.design.DProxy;
import com.shangxiaom.commonlist.design.IBuy;
import com.shangxiaom.commonlist.design.User;
import com.shangxiaom.commonlist.design.UserProxy;

import org.junit.Test;

import java.lang.reflect.Proxy;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void buyHouse() {
        IBuy user = new User();
        user.buyHouse(10000);

        IBuy userProxy = new UserProxy(user);
        userProxy.buyHouse(10000);

        IBuy dProxy = (IBuy) Proxy.newProxyInstance(user.getClass().getClassLoader(),user.getClass().getInterfaces(),new DProxy(user));
        dProxy.buyHouse(10000);
    }
}