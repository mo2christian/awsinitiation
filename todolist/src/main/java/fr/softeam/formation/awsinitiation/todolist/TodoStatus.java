package fr.softeam.formation.awsinitiation.todolist;

import java.util.Arrays;

public enum TodoStatus {

    START("s"), COMPLETE("c"), PENDING("p");

    private final String code;

    private TodoStatus(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }

    public static TodoStatus valueFrom(String code){
        return Arrays.stream(TodoStatus.values())
                .filter(status -> status.code.equalsIgnoreCase(code))
                .findAny()
                .orElseThrow();
    }

}
