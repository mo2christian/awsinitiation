package fr.softeam.formation.awsinitiation.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexController {

    @Value("${app.version}")
    private String version;

    private final TodoService repository;

    @Autowired
    public IndexController(TodoService repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("version", version);
        model.addAttribute("todos", repository.findAll());
        return "index";
    }

    @PostMapping("/add")
    public String addTodo(@ModelAttribute TodoForm form){
        repository.add(todoMapper(form));
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteTodo(@RequestParam("id") String id){
        repository.findById(id)
                .ifPresent(repository::delete);
        return "redirect:/";
    }

    @GetMapping("/start")
    public String startTodo(@RequestParam("id") String id){
        repository.findById(id)
                .ifPresent(todo -> {
                    todo.setStatus(TodoStatus.START);
                    repository.update(todo);
                });
        return "redirect:/";
    }

    @GetMapping("/end")
    public String endTodo(@RequestParam("id") String id){
        repository.findById(id)
                .ifPresent(todo -> {
                    todo.setStatus(TodoStatus.COMPLETE);
                    repository.update(todo);
                });
        return "redirect:/";
    }

    private Todo todoMapper(TodoForm form){
        Todo todo = new Todo();
        todo.setContent(form.getContent());
        return todo;
    }

}
