package com.example.webapplication.example.todo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class TodoService {

	private  static List<Todo> todos=new ArrayList<>();
	
	@Autowired 
	TodoRepository todoRepository;
	public TodoService( TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

//	
//	static {
//        todos.add(new Todo( "simran","learn Aws", LocalDate.now().plusYears(1), false, false, LocalDateTime.now().plusDays(1), null));
//        todos.add(new Todo( "simran","learn devops", LocalDate.now().plusYears(2), false, false, LocalDateTime.now().plusDays(1)));
//        todos.add(new Todo("simran", "learn google cloud", LocalDate.now().plusYears(1), false, false, LocalDateTime.now().plusDays(1)));
//    }
	public List<Todo> findByUsername(String username) {
        return todoRepository.findByUsername(username);
    }
	
	public void addTodo(String username, String description, LocalDate targetDate, boolean done) {
	    Todo todo = new Todo(username, description, targetDate, done);
	    todoRepository.save(todo);
    }

	public void deleteById(int id) {
        todoRepository.deleteById(id);
    }
	
	public Todo findById(int id) {
		return todoRepository.findById(id).orElse(null);
	}
	public void updateTodo(@Valid Todo todo) {
		todoRepository.save(todo);
	}


}
