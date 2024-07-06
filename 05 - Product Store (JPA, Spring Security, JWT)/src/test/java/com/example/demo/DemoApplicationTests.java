package com.example.demo;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(JUnitPlatform.class)
@SelectClasses({
		AdminTest.class,
		SaleTest.class,
		UserTest.class,
		ProductTest.class
})
@SpringBootTest
public class DemoApplicationTests {
}