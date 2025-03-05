import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import java.awt.Robot as Robot
import java.awt.event.KeyEvent as KeyEvent
import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.util.KeywordUtil

KeywordLogger log = new KeywordLogger()

try {
	
	'Call test case login'
	WebUI.callTestCase(findTestCase('Common/Login/Common Login'), [:], FailureHandling.STOP_ON_FAILURE)
	
	'User in appointment menu'
	WebUI.delay(1)
	WebUI.verifyElementPresent(findTestObject('Appointment Page/text_makeAppointment'),1)
	
	'User logout via sidebar'
	WebUI.verifyElementPresent(findTestObject('Sidebar/btn_menuToggle'), 1)
	WebUI.click(findTestObject('Sidebar/btn_menuToggle'))
	
	'Verify user success logout'
	WebUI.verifyElementPresent(findTestObject('Home Page/text_CURAHealthcareService'), 1)
	WebUI.verifyElementPresent(findTestObject('Home Page/btn_makeAppointment'), 1)
	
	'User press back button'
	WebUI.back()
	WebUI.waitForPageLoad(5)
	
	'Verify System Behavior'
	String currentUrl = WebUI.getUrl()
	String expectedLoginUrl = "https://katalon-demo-cura.herokuapp.com/profile.php#login"
	if (currentUrl == expectedLoginUrl) {
		WebUI.comment("✅ Test Passed: User  to the login page after clicking back.")
	} else {
		WebUI.comment("❌ Test Failed: User was able to navigate back to the previous page after logout.")
		assert false
	}
	
	'User still in homepage'
	WebUI.verifyElementPresent(findTestObject('Home Page/text_CURAHealthcareService'), 1)
	WebUI.verifyElementPresent(findTestObject('Home Page/btn_makeAppointment'), 1)
}
	
catch (Exception e) {
    WebUI.takeScreenshot()
    KeywordUtil.markFailedAndStop('Failed: ' + e.getMessage())
}

finally {
//	WebUI.closeBrowser()
}
