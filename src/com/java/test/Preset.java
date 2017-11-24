package com.java.test;

import com.java.main.BaseClass;
import com.java.pages.Dashboard;
import org.testng.annotations.Test;

public class Preset extends BaseClass{



    @Test(priority = 0)
    public void sendPresetMsg()
    {
        Dashboard db = new Dashboard(driver);
        db.sendPresetMessage();
    }

    @Test(priority = 1)
    public void UpdateSendPresetMsg()
    {
        Dashboard db = new Dashboard(driver);
        db.updateSendPresetMessage();
    }

    @Test(priority = 2)
    public void cancelSendPresetMsg()
    {
        Dashboard db = new Dashboard(driver);
        db.cancelSendingMessage();
    }
}
