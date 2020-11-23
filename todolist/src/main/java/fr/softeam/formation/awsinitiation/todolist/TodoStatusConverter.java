package fr.softeam.formation.awsinitiation.todolist;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

public class TodoStatusConverter  implements DynamoDBTypeConverter<String, TodoStatus> {
    @Override
    public String convert(TodoStatus todoStatus) {
        return todoStatus.getCode();
    }

    @Override
    public TodoStatus unconvert(String s) {
        return TodoStatus.valueFrom(s);
    }
}
