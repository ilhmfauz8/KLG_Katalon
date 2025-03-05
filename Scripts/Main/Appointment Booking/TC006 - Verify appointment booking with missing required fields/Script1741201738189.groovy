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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.testobject.ConditionType
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.testobject.ConditionType


// TestObject dinamis
TestObject createTestObject(String xpath) {
	TestObject to = new TestObject()
	to.addProperty("xpath", ConditionType.EQUALS, xpath)
	return to
}

try {
	
	'Call test case login'
	WebUI.callTestCase(findTestCase('Common/Login/Common Login'), [:], FailureHandling.STOP_ON_FAILURE)
	
	'User in appointment menu'
	WebUI.delay(1)
	WebUI.verifyElementPresent(findTestObject('Appointment Page/text_makeAppointment'),1)
	WebUI.verifyElementPresent(findTestObject('Appointment Page/selector_facility'),1)
	WebUI.verifyElementPresent(findTestObject('Appointment Page/cb_hospitalReadmission'),1)
	WebUI.verifyElementPresent(findTestObject('Appointment Page/rb_Medicaid'),1)
	WebUI.verifyElementPresent(findTestObject('Appointment Page/rb_Medicare'),1)
	WebUI.verifyElementPresent(findTestObject('Appointment Page/rb_None'),1)
	WebUI.verifyElementPresent(findTestObject('Appointment Page/textfield_comment'),1)
	WebUI.verifyElementPresent(findTestObject('Appointment Page/selector_date'),1)
	
	'User make an appointment with missing required fields'
	WebUI.click(findTestObject('Appointment Page/btn_appointment'))
	
	'Verify Error message indicating required fields appears'
	TestObject inputField = new TestObject()
	inputField.addProperty("xpath", ConditionType.EQUALS, "//input[@id='txt_visit_date']")
	// Execute JavaScript to get the validation message
	String validationMessage = WebUI.executeJavaScript("return arguments[0].validationMessage;", Arrays.asList(WebUI.findWebElement(inputField)))
	
	// Expected validation message 
	String expectedMessage = "Please fill out this field." 
	
	// Assert the validation message
	assert validationMessage == expectedMessage : "Expected validation message not found! Actual: " + validationMessage
	
	WebUI.takeScreenshot()
	
}
	
catch (Exception e) {
    WebUI.takeScreenshot()
    KeywordUtil.markFailedAndStop('Failed: ' + e.getMessage())
}

finally {
	WebUI.closeBrowser()
}
