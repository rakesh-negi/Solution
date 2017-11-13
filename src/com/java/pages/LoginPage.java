package com.java.pages;

import com.java.main.Common;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class LoginPage {
    WebDriver driver;
    public static Logger log = LoggerFactory.getLogger(LoginPage.class.getName());
    Common commonpage = new Common();
    By username = By.id("id_username");
    By password_ = By.id("id_password");
    By login_button = By.xpath("//*[@id='login-form']/div[3]/input");
    By text_on_home_page = By.xpath("//*[@id='content']/h1[contains(text(),'Site administration')]");
    By Message_verify=By.xpath("//*[@id='content']/p");
    By logout=By.xpath("//*[@id='user-tools']/a[2]");
    By login_again=By.xpath("//*[@id='content']/p[2]/a");
    public LoginPage(WebDriver driver) {
    this.driver=driver;
    }


    public void Login(String UserName, String Password) throws Exception {
        Assert.assertTrue(Common.isElementPresentbyxpath(username));
        driver.findElement(username).clear();
        log.info("Cleared username");
        driver.findElement(username).sendKeys(UserName);
        log.info("The entered email is={}.", UserName);
        Assert.assertTrue(Common.isElementPresentbyxpath(password_));
        driver.findElement(password_).clear();
        log.info("Cleared password");
        driver.findElement(password_).sendKeys(Password);
        log.info("Entered password");
        Assert.assertTrue(Common.isElementPresentbyxpath(login_button));
        driver.findElement(login_button).click();
        log.info("login button clicked");
        Assert.assertTrue(commonpage.waitforelementpresent(text_on_home_page, 2), "User is not able to login successfully");
        log.info("Page title is{}: ", driver.getTitle());
        Assert.assertEquals(driver.getTitle(),"Site administration | jango site admin","Page Title Not Matching");
        log.info("Driver closed");

       }

    public void Login_excel(String UserName, String Password, String Xpath, String scenario) throws Exception {
        Assert.assertTrue(Common.isElementPresentbyxpath(username));
        driver.findElement(username).clear();
        log.info("Cleared username");
        driver.findElement(username).sendKeys(UserName);
        log.info("The entered email is={}.", UserName);
        Assert.assertTrue(Common.isElementPresentbyxpath(password_));
        driver.findElement(password_).clear();
        log.info("Cleared password");
        driver.findElement(password_).sendKeys(Password);
        log.info("Entered password");
        Assert.assertTrue(Common.isElementPresentbyxpath(login_button));
        driver.findElement(login_button).click();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   
        log.info("login button clicked");
        Thread.sleep(2000);
        log.info("sceanrio value{}", scenario);
        String succ="success";
        if( scenario.equals(succ)){
            log.info("Logged Successfully");
            driver.findElement(logout).click();
            Thread.sleep(2000);
            driver.findElement(login_again).click();
            Thread.sleep(1000);
        }
        else{
        String Original_Message=driver.findElement(Message_verify).getText();
            log.info("sdasd {}",Original_Message);
        Assert.assertEquals(Original_Message,Xpath);
            //driver.navigate().refresh();
    }
    }}






