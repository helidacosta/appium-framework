package br.com.santander.core;

import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

public class DriverFactory {
	private DriverFactory() {}
	
    

	/**
	 * Le o valor de uma propriedade do arquivo <i>config.properties<i/>
	 * 
	 * @param propriedade nome da propriedade
	 * @return valor da propriedade
	 */ 
	public static String lerPropriedade(String propriedade) {
		Properties prop;
		String valor = null;
		try {
			prop = new Properties();
			prop.load(new FileInputStream(new File("config.properties")));

			valor = prop.getProperty(propriedade);

			if (valor == null || valor == "") {
				throw new PropertyException();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return valor;
	}

	private static AppiumDriver<?> initDriver(String plataforma) throws MalformedURLException, PlatformException {
		AppiumDriver<?> driver = null;
		DesiredCapabilities capacidade = new DesiredCapabilities();

		String urlCompleta = "http://" + lerPropriedade("execucao.ip") + ":" + lerPropriedade("execucao.porta")
				+ "/wd/hub";

		switch (plataforma.toLowerCase()) {

		case "ios":

			capacidade.setCapability(MobileCapabilityType.APP, new File(lerPropriedade("ios.app.nativo")).getAbsolutePath());
			capacidade.setCapability(MobileCapabilityType.DEVICE_NAME, lerPropriedade("ios.nome.dispositivo"));
			capacidade.setCapability(MobileCapabilityType.PLATFORM_VERSION, lerPropriedade("ios.versao.plataforma"));
			capacidade.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);

			if (Boolean.parseBoolean(lerPropriedade("ios.xcode8"))) {
				capacidade.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
			}

			driver = new IOSDriver<>(new URL(urlCompleta), capacidade);
			break;

		case "android":

			capacidade.setCapability(MobileCapabilityType.APP, new File(lerPropriedade("android.app.nativo")).getAbsolutePath());
			capacidade.setCapability(MobileCapabilityType.DEVICE_NAME, lerPropriedade("android.nome.dispositivo"));
			capacidade.setCapability(MobileCapabilityType.PLATFORM_NAME, "UiAutomator2");
			capacidade.setCapability(MobileCapabilityType.FORCE_MJSONWP, true);

			driver = new AndroidDriver<>(new URL(urlCompleta), capacidade);
			break;

		default:
			throw new PlatformException();
		}
		return driver;

	}
}
