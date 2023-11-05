package com.anotherParameter.demo;

import com.amazonaws.auth.SystemPropertiesCredentialsProvider;
import com.anotherParameter.demo.config.AWSConfig;
import org.apache.tomcat.jni.Time;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		// This needs to be put before the SSM Client in order to authenticate correctly
		SystemPropertiesCredentialsProvider systemPropertiesCredentialsProvider=new SystemPropertiesCredentialsProvider();
		System.setProperty("aws.accessKeyId",REPLACE_WITH_YOUR_ACCESS_KEY);
		System.setProperty("aws.secretAccessKey",REPLACE_WITH_YOUR_SECRET_KEY);

		AWSConfig.readParameterStoreValue("/config/validator/last-success");
		AWSConfig.updateParameterStoreValue("/config/validator/last-success", "Updated Supposedly by Me at " + System.currentTimeMillis());
		AWSConfig.readParameterStoreValue("/config/validator/last-success");

		SpringApplication.run(DemoApplication.class, args);
	}

}
