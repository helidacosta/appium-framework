package br.com.santander.pages;

import java.time.temporal.ChronoUnit;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.WithTimeout;
import io.appium.java_client.pagefactory.iOSBy;

public class LoginPages {
	
	@AndroidFindBy(id = "org.traeg.fastip:id/billAmtEditText")
	@iOSBy(xpath = "//XCUIElementTypeTextField")
    @WithTimeout(time = 10, chronoUnit = ChronoUnit.SECONDS)
	MobileElement billAmount;
	
	
	public LoginPages(AppiumDriver<?> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	

}
