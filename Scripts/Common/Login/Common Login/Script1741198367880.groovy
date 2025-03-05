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
	WebUI.openBrowser(GlobalVariable.baseURL)
	WebUI.maximizeWindow()
	
	'User in homepage and make appointment'
	WebUI.verifyElementPresent(findTestObject('Home Page/text_CURAHealthcareService'), 1)
	WebUI.verifyElementPresent(findTestObject('Home Page/btn_makeAppointment'), 1)
	WebUI.click(findTestObject('Home Page/btn_makeAppointment'))
	
	'Verify user in login page'
	WebUI.waitForElementPresent(findTestObject('Login Page/text_Login'), 1)
	WebUI.verifyElementPresent(findTestObject('Login Page/btn_Login'),1)
	WebUI.verifyElementPresent(findTestObject('Login Page/text_Login'), 1)
	WebUI.verifyElementPresent(findTestObject('Login Page/textfield_password'), 1)
	WebUI.verifyElementPresent(findTestObject('Login Page/textfield_username'),1)
	
	'User input credentials'
	WebUI.click(findTestObject('Login Page/textfield_username'))
	WebUI.setText(findTestObject('Login Page/textfield_username'), 'John Doe')
	WebUI.click(findTestObject('Login Page/textfield_password'))
	WebUI.setEncryptedText(findTestObject('Login Page/textfield_password'), 'g3/DOGG74jC3Flrr3yH+3D/yKbOqqUNM')
	
	'Login'
	WebUI.delay(1)
	WebUI.click(findTestObject('Login Page/btn_Login'))
	WebUI.verifyElementPresent(findTestObject('Appointment Page/text_makeAppointment'),1)
	
}
	
catch (Exception e) {
    WebUI.takeScreenshot()
    KeywordUtil.markFailedAndStop('Failed: ' + e.getMessage())
}

