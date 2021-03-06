package fr.softeam.formation.awsinitiation.todolist;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@DynamoDBTable(tableName = "todo")
public class Todo implements Serializable {

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey()
    private String id;

    @DynamoDBAttribute
    private String content;

    @DynamoDBAttribute
    @DynamoDBTypeConverted(converter = TodoStatusConverter.class)
    private TodoStatus status = TodoStatus.PENDING;

    @DynamoDBAttribute
    private String discriminant;

}
