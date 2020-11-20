package fr.softeam.formation.awsinitiation.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Value("${app.version}")
    private String version;

    private final TodoRepository repository;

    @Autowired
    public IndexController(TodoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("version", version);
        model.addAttribute("todos", repository.findAll());
        return "index";
    }

}
