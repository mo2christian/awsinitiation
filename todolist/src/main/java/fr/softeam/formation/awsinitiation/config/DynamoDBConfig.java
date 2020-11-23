package fr.softeam.formation.awsinitiation.config;

import com.amazonaws.auth.*;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDBConfig {

    @Value("${aws.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;

    @Value("${cloud.aws.credentials.access-key}")
    private String amazonAWSAccessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String amazonAWSSecretKey;

    @Value("${cloud.aws.credentials.instanceProfile}")
    private boolean instanceProfile;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Bean
    public AmazonDynamoDB amazonDynamoDB(AWSCredentialsProvider credentialsProvider) {
        AmazonDynamoDBClientBuilder builder = AmazonDynamoDBClientBuilder.standard()
                .withCredentials(credentialsProvider);
        if (amazonDynamoDBEndpoint != null && !amazonDynamoDBEndpoint.isEmpty()) {
            AwsClientBuilder.EndpointConfiguration endpoint
                    = new AwsClientBuilder.EndpointConfiguration(amazonDynamoDBEndpoint, region);
            builder.withEndpointConfiguration(endpoint);
        }
        return builder.build();
    }

    @Bean
    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB){
        DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
        return dynamoDBMapper;
    }

    @Bean
    public AWSCredentialsProvider amazonAWSCredentials() {
        if (instanceProfile){
            return new InstanceProfileCredentialsProvider(false);
        }
        else{
            return new AWSStaticCredentialsProvider(
                    new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey)) ;
        }
    }
}
