package com.example.demo;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(JUnitPlatform.class)
@SelectClasses({
		UserServiceTest.class,
		ProductServiceTest.class,
//		SaleServiceTest.class
})
@SpringBootTest
public class DemoApplicationTests {
}