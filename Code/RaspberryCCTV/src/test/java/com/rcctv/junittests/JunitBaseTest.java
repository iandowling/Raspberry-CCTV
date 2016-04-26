package com.rcctv.junittests;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import com.rcctv.Application;

/**
 * The AbstractTest class is the parent of all JUnit test classes. This class
 * configures the test ApplicationContext and test runner environment.
 */

@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration(
        classes = Application.class)
public abstract class JunitBaseTest {
	
     // The Logger instance for all classes in the unit test framework.
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
}
