package com.anotherParameter.demo.config;

import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.*;

@Configuration
public class AWSConfig {
    private static final Region region = Region.EU_CENTRAL_1;
    private static SsmClient ssmClient = SsmClient.builder()
            .region(region).build();


    public static void readParameterStoreValue(String paramPath) {
        try {
            System.out.println("Starting to Read Params");
            GetParameterRequest parameterRequest = GetParameterRequest.builder()
                    .name(paramPath)
                    .build();

            GetParameterResponse parameterResponse = ssmClient.getParameter(parameterRequest);
            System.out.println("The parameter value is " + parameterResponse.parameter().value());

        } catch (SsmException ssmException) {
            System.out.println("Ssm Exception occurred" + ssmException);
        } /*finally {
            ssmClient.close();
        }*/
    }


    public static void updateParameterStoreValue(String paramPath, String value) {
        try {
            System.out.println("Starting to Update Params");

            //Create request object with the key and value in String
            PutParameterRequest parameterRequest = PutParameterRequest.builder()
                    .name(paramPath)
                    .type(ParameterType.STRING)
                    .value(value)
                    .overwrite(true)
                    .build();

            PutParameterResponse parameterResponse =ssmClient.putParameter(parameterRequest);
            System.out.println("The parameter was successfully added.");

            System.out.println("The tierAsString value is " + parameterResponse.tierAsString());
            System.out.println("The Response value is " + parameterResponse.toString());

        } catch (SsmException ssmException) {
            System.out.println("Ssm Exception occurred" + ssmException);
        } /*finally {
            ssmClient.close();
        }*/
    }

}
