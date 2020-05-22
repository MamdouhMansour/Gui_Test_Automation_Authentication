package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {

    private Properties properties;

    /**
     * This method used to extract the test data from the properties file "Test Data
     * properties file". Properties file relative path is used in loading the
     * FileInputStream.
     * 
     * @param properties object of Properties class to load and access properties
     *                   file to externalize test data.
     * @return the test data value.
     */
    public String getTestData(String valueName) {
	properties = new Properties();

	try {
	    properties.load(new FileInputStream("src/test/resources/TestDataFiles/TestData.properties"));
	} catch (IOException e) {
	    e.printStackTrace();
	}

	return properties.getProperty(valueName);
    }
}
