package com.example.webapplication.example.todo;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class TodoControllerJpa {
	private TodoRepository todoRepository;
	public TodoControllerJpa(TodoRepository todoRepository) {
		super();
		this.todoRepository = todoRepository;
	}
	
	@RequestMapping("list-todos")
	public String listAllTodos(ModelMap model){
		String username=(String)getLoggedInUsername(model);
		List<Todo> todos=todoRepository.findByUsername(username);
		model.addAttribute("todos",todos);
		return "listTodo";
	}
	
	@RequestMapping(value="add-todo",method=RequestMethod.GET)
	public String showNewTodoPage(ModelMap model){
		String username=(String)getLoggedInUsername(model);
		Todo todo=new Todo();
		todo.setUsername(username);
		//todo.setTargetDate(LocalDate.now().plusYears(1));;
		model.put("todo", todo);
		return "todo";
	}
	
	@RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String addNewTodo(@Valid Todo todo, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "todo"; // Return to the form page if there are validation errors
        }
        String username = (String) getLoggedInUsername(model);
        todo.setUsername(username);
        todoRepository.save(todo);
        
        return "redirect:list-todos"; // Redirect to the list of todos
    }
	
	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam int id) {
		todoRepository.deleteById(id);
		return "redirect:list-todos";
	}
	
	@RequestMapping("update-todo")
	public String showUpdateTodoPage(@RequestParam int id,ModelMap model) {
		Todo todo=todoRepository.findById(id).get();
		model.addAttribute("todo", todo);
		return "todo";
	}
	
	@RequestMapping(value="update-todo", method=RequestMethod.POST)
	public String updateTodo(@Valid Todo todo,  BindingResult result, ModelMap model) {
	    if (result.hasErrors()) {
	        return "todo"; // Return to the form page if there are validation errors
	    }
	    String username = (String) getLoggedInUsername(model);
	    todo.setUsername(username);
	    todoRepository.save(todo);
	    return "redirect:list-todos"; // Redirect to the list of todos
	}

	private String getLoggedInUsername(ModelMap model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String loggedinUsername = authentication.getName();
		return loggedinUsername;
	}

	
}
