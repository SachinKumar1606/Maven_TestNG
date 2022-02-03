package src.test.java;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.IOException;

public class DataFetching {
    WebDriver driver;

    @BeforeSuite
    public void launch(){
        WebDriverManager.chromedriver().setup();
    }

    @Test
    void login() throws IOException {
        String path = System.getProperty("user.dir") + "/TestData/Login.xlsx";
        //Reading the Excel file
        FileInputStream file = new FileInputStream(path);
        //Reading the workBook
        XSSFWorkbook bk = new XSSFWorkbook(file);
        //Reading the sheet
        XSSFSheet sh = bk.getSheetAt(0);

        int row = sh.getLastRowNum();

        for(int i=1;i<=row;i++){
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.navigate().to("https://www.saucedemo.com/");
            String name = sh.getRow(i).getCell(0).getStringCellValue();
            String pass = sh.getRow(i).getCell(1).getStringCellValue();
            driver.findElement(By.id("user-name")).sendKeys(name);
            driver.findElement(By.id("password")).sendKeys(pass);
            driver.findElement(By.id("login-button")).click();
            String act = driver.getCurrentUrl();
            driver.close();
            String ect = "https://www.saucedemo.com/inventory.html";
//            Assert.assertEquals(act,ect);
        }

    }

}
