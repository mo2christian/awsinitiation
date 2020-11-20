package fr.softeam.formation.awsinitiation.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
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
    public AmazonDynamoDB amazonDynamoDB(AWSCredentials credentials) {
        AmazonDynamoDB amazonDynamoDB
                = new AmazonDynamoDBClient(credentials);
        amazonDynamoDB.setRegion(Region.getRegion(Regions.fromName(region)));

        if (amazonDynamoDBEndpoint != null && !amazonDynamoDBEndpoint.isEmpty()) {
            amazonDynamoDB.setEndpoint(amazonDynamoDBEndpoint);
        }

        return amazonDynamoDB;
    }

    @Bean
    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB){
        DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
        return dynamoDBMapper;
    }

    @Bean
    public AWSCredentials amazonAWSCredentials() {
        if (instanceProfile){
            return new InstanceProfileCredentialsProvider(false).getCredentials();
        }
        else{
            return new BasicAWSCredentials(
                    amazonAWSAccessKey, amazonAWSSecretKey);
        }
    }
}
