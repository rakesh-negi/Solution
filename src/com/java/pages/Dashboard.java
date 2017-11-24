package com.java.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Dashboard {

    WebDriver driver;

    public Dashboard(WebDriver driver)
    {
        this.driver=driver;
    }

    By title = By.className("navbar-logo");
    By select_member = By.xpath("//div[@class = 'member__name']");
    By preset = By.xpath("//button[@class='message__presets_button message__button']");
    By select_preset_message = By.xpath("//h2[@class='preset_name']");
    By send_button = By.xpath("//button[@class ='message__send_button']");
    By msg_txt_box = By.id("message__textarea");
    By msg_cancel_button = By.xpath("//button[@class ='message_cancel_button' and contains(text(),'cancle')]");
    By chat_window_msg = By.xpath("");
    By chat_window_sentBy = By.xpath("");
    By Scroll_To_View_msg = By.id("");
    By preset_viewMore = By.linkText("View More...");
    By preset_viewMore_searchBox = By.id("preset_search_input");
    By lessThan4Char_error = By.className("search__input__minimum_characters_error ng-tns-c3-3");
    By leftCharIn_searchBox = By.className("search__input__characters_left ng-tns-c3-3");
    By resultCountForSearch = By.className("preset results_bar ng-tns-c3-3");
    By viewMoreList = By.xpath("");
    By msgList_preset_viewMore = By.xpath("");
    By preset_title = By.xpath("");
    By preset_subject = By.xpath("");
    By preset_content = By.xpath("");

    public void sendPresetMessage()

    {
        try {
            //select a member
            driver.findElement(select_member).click();
            //click on preset message
            Thread.sleep(5000);
            driver.findElement(preset).click();
            //select a preset message
            Thread.sleep(5000);
            driver.findElement(select_preset_message).click();
            //click on send button
            Thread.sleep(10000);
            driver.findElement(send_button).click();
            Thread.sleep(10000);
        }
        catch (Exception e)
        {
            e.getMessage();
        }

    }

    public void updateSendPresetMessage()
    {
        try {
            //select a member
            driver.findElement(select_member).click();
            //click on preset message
            Thread.sleep(5000);
            driver.findElement(preset).click();
            //select a preset message
            Thread.sleep(5000);
            driver.findElement(select_preset_message).click();
            //click on send button
            Thread.sleep(5000);
            //Update the preset message
            driver.findElement(msg_txt_box).sendKeys("Test");
            Thread.sleep(5000);
            //sending the message
            driver.findElement(send_button).click();
            Thread.sleep(5000);
        }
        catch (Exception e)
        {
            e.getMessage();
        }

    }

    public void cancelSendingMessage()

    {
        try {
            //select a member
            driver.findElement(select_member).click();
            //click on preset message
            Thread.sleep(5000);
            driver.findElement(preset).click();
            //select a preset message
            Thread.sleep(5000);
            driver.findElement(select_preset_message).click();
            //click on send button
            Thread.sleep(3000);
            driver.findElement(send_button).click();
            Thread.sleep(2000);
            //cancelling the message
            driver.findElement(msg_cancel_button).click();
            Thread.sleep(5000);
        }
        catch (Exception e)
        {
            e.getMessage();
        }

    }
}
