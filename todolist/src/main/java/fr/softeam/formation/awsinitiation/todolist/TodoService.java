package fr.softeam.formation.awsinitiation.todolist;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.net.InetAddress;
import java.util.List;
import java.util.Optional;

@Repository
public class TodoService {

    private final DynamoDBMapper dbMapper;
    private final String discriminant;

    public TodoService(DynamoDBMapper dbMapper, @Qualifier("disc") String discriminant) {
        this.dbMapper = dbMapper;
        this.discriminant = discriminant;
    }

    public List<Todo> findAll(){
        AttributeValue discValue = new AttributeValue();
        discValue.withS(discriminant);
        Condition condition = new Condition();
        condition.withComparisonOperator(ComparisonOperator.EQ)
                .withAttributeValueList(discValue);
        DynamoDBScanExpression expression = new DynamoDBScanExpression();
        expression.addFilterCondition("discriminant", condition);
        return dbMapper.scan(Todo.class, expression);
    }

    public void add(Todo todo){
        todo.setDiscriminant(discriminant);
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
