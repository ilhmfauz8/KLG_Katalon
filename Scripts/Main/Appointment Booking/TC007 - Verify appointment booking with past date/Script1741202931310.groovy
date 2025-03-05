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
	
	'User make an appointment'
	WebUI.click(findTestObject('Appointment Page/cb_hospitalReadmission'))
	WebUI.click(findTestObject('Appointment Page/rb_Medicaid'))
	WebUI.setText(findTestObject('Appointment Page/selector_date'),'12/02/2021')
	WebUI.setText(findTestObject('Appointment Page/textfield_comment'),'Test Comment Automation')
	WebUI.click(findTestObject('Appointment Page/btn_appointment'))
	
	'Verify user success appointment booking'
	WebUI.verifyElementPresent(findTestObject('Confirmation Page/text_appointmentConfirmation'),1)
	WebUI.verifyElementPresent(findTestObject('Confirmation Page/text_TokyoCURAHealthcareCenter'),1)
	
	'Verify date is match'
	String baseXPathTanggal= "//*[text() = '12/02/2021' or . = '12/02/2021']"
	// Buat TestObject berdasarkan teks tertentu
	TestObject headerObject = createTestObject(baseXPathTanggal)
	// Verifikasi elemen ada di halaman
	WebUI.verifyElementPresent(headerObject, 1)
	// Verifikasi teks dalam elemen
	String expectedDate = "12/02/2021"
	WebUI.verifyElementText(headerObject, expectedDate)
	
}
	
catch (Exception e) {
    WebUI.takeScreenshot()
    KeywordUtil.markFailedAndStop('Failed: ' + e.getMessage())
}

finally {
	WebUI.closeBrowser()
}
