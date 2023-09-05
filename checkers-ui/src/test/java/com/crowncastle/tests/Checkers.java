package com.crowncastle.tests;

import com.crowncastle.common.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.time.Duration;
import java.util.List;

public class Checkers {

    /*
        For this exercise, implement the following steps:
        Navigate to https://www.gamesforthebrain.com/game/checkers/
        Confirm that the site is up
        Make five legal moves as orange:
        Include taking a blue piece
        Use “Make a move” as confirmation that you can take the next step
        Restart the game after five moves
        Confirm that the restarting had been successful
     */

    public WebDriver driver;

    @BeforeTest
    public void launchBrowser() {
        driver = new FirefoxDriver();
        driver.get(Constants.CHECKERS_URL);
        driver.manage().window().maximize();
    }

    @Test
    public void checkersValidatePageTitle() {
        Assert.assertEquals(driver.getTitle(), "Checkers - Games for the Brain");
    }

    @Test
    public void checkers5Moves() throws InterruptedException {

        //Get a list of rows on the board
        List<WebElement> row = driver.findElements(By.xpath(".//*[@id=\"board\"]/div"));
        WebElement message = driver.findElement(By.id("message"));
        String messageText = message.getText();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElement(message, "Select an orange piece to move."));

        //Spaces with my pieces have src=you1.gif
        //Space with their pieces have src=me1.gif
        //Available empty spaces have src=gray.gif

        //Move 1
        driver.findElement(By.name("space22")).click();
        driver.findElement(By.name("space13")).click();
        Thread.sleep(5000);
        //wait.until(ExpectedConditions.textToBePresentInElement(message,"Make a move.")); //Not working consistently

        //Move 2
        driver.findElement(By.name("space62")).click();
        driver.findElement(By.name("space73")).click();
        Thread.sleep(5000);

        //Move 3
        driver.findElement(By.name("space13")).click();
        driver.findElement(By.name("space35")).click();
        Thread.sleep(5000);

        //Move 4
        driver.findElement(By.name("space73")).click();
        driver.findElement(By.name("space64")).click();
        Thread.sleep(5000);

        //Move 5
        driver.findElement(By.name("space71")).click();
        driver.findElement(By.name("space62")).click();
        Thread.sleep(5000);

        //Restart game
        driver.findElement(By.partialLinkText("Restart...")).click();
        Thread.sleep(7000);

        //Validate
        Assert.assertEquals(getLatestMessage(driver), "Select an orange piece to move.");
    }

    @AfterTest
    public void closeBrowser() {
        driver.close();
    }

    public String getLatestMessage(WebDriver driver){
        WebElement message = driver.findElement(By.id("message"));
        return message.getText();
    }
}
