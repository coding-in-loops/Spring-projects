package com.example.webapplication.example.todo;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

//@Controller
//@SessionAttributes("name")
public class TodoController {
	private TodoService todoService;
	
	public TodoController(TodoService todoService) {
		super();
		this.todoService = todoService;
	}
	
	@RequestMapping("list-todos")
	public String listAllTodos(ModelMap model){
		String username=(String)getLoggedInUsername(model);
		List<Todo> todos=todoService.findByUsername(username);
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
        todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), todo.isDone());
        return "redirect:list-todos"; // Redirect to the list of todos
    }
	
	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam int id) {
		todoService.deleteById(id);
		return "redirect:list-todos";
	}
	
	@RequestMapping("update-todo")
	public String showUpdateTodoPage(@RequestParam int id,ModelMap model) {
		Todo todo=todoService.findById(id);
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
	    todoService.updateTodo(todo);
	    return "redirect:list-todos"; // Redirect to the list of todos
	}

	private String getLoggedInUsername(ModelMap model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String loggedinUsername = authentication.getName();
		return loggedinUsername;
	}
	
}
