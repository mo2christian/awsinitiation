package fr.softeam.formation.awsinitiation.todolist;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodoRepository {

    private final DynamoDBMapper dbMapper;

    public TodoRepository(DynamoDBMapper dbMapper) {
        this.dbMapper = dbMapper;
    }

    public List<Todo> findAll(){
        DynamoDBScanExpression expression = new DynamoDBScanExpression();
        expression.setLimit(20);
        return dbMapper.scan(Todo.class, expression);
    }

}
