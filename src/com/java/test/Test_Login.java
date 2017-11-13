package com.java.test;

import com.java.main.BaseClass;
import com.java.pages.LoginPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class Test_Login extends BaseClass {
    public static Logger log = LoggerFactory.getLogger(Test_Login.class.getName());

    @Test
    public void Verify_Successful_Login() throws Exception {
        try {
            log.info("Successful Login TestCase");
            LoginPage LP = new LoginPage(driver);
            LP.Login("rakesh_QA", "rake@123");
            log.info("Logged Successfully");
            System.out.println("Page titile is" + driver.getTitle());

        } catch (Exception e) {
            log.error("Failed in this xpath {}.", e);
        }

    }

    //@DataProvider
    public Object[][] logindata() {
        Object[][] data = getdata("login_demo.xlsx", "login");

        return data;

    }

   // @Test(dataProvider = "logindata")
    public void test2_login_credentials(String email, String password, String order,String Xpath, String scenario) throws Exception {


            if (order.endsWith("no")) {
                throw new SkipException("Skipping this test");
            }
            log.info("Successful Login TestCase");
            LoginPage LP = new LoginPage(driver);
            LP.Login_excel(email,password,Xpath,scenario);
            log.info("Logged Successfully");

    }
}
