package fr.softeam.formation.awsinitiation.todolist;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    public void add(Todo todo){
        dbMapper.save(todo);
    }

    public void update(Todo todo){
        dbMapper.save(todo);
    }

    public void delete(Todo todo){
        dbMapper.delete(todo);
    }

    public Optional<Todo> findById(String id){
        return Optional.ofNullable(dbMapper.load(Todo.class, id));
    }
}
