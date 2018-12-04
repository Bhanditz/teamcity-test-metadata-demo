package foo

import foo.bar.Code
import org.testng.annotations.AfterClass
import org.testng.annotations.AfterMethod
import org.testng.annotations.AfterTest
import org.testng.annotations.Test

/**
 * @author kir
 */


class CodeTest {

    @Test
    fun test_ok() {

        // Simple text metadata:
        println("##teamcity[testMetadata key='some key' value='some value']")

        // Numeric metadata
        println("##teamcity[testMetadata key='a numeric value' type='number' value='${Code().value()}']")

        // Reference to an artifact
        // gradle_test_report.zip should be created by specifying corresponding artifact path in TC
        val testsPath = "gradle_test_report.zip!/classes/${javaClass.name}.html"
        println("##teamcity[testMetadata key='Gradle test report' type='artifact' value='$testsPath']")

    }

    @Test
    fun test_failure() {
        test_ok();

        throw Exception("And here comes some problem");
    }

    @AfterClass
    fun publishScreenshot() {

        println("This is AfterMethod")
        println("##teamcity[publishArtifact 'pictureForAttention.png']")
        println("##teamcity[testMetadata type='image' name='Some screenshot' value='pictureForAttention.png']")

    }

}