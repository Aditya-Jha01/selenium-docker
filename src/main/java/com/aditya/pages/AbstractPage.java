package com.aditya.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractPage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final Actions actions;

    public AbstractPage(WebDriver driver) {
        this.driver=driver;
        this.wait=new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver,this);
        driver.manage().window().maximize();
        this.actions = new Actions(driver);
    }

    public abstract boolean isAt();
}
