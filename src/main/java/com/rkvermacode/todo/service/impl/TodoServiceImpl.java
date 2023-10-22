package com.rkvermacode.todo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.rkvermacode.todo.dto.TodoDto;
import com.rkvermacode.todo.entity.Todo;
import com.rkvermacode.todo.exception.ResourceNotFoundException;
import com.rkvermacode.todo.repository.TodoRepository;
import com.rkvermacode.todo.service.TodoService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService{

	private TodoRepository todoRepository;
	
	private ModelMapper modelMapper;
	
	@Override
	public TodoDto addTodo(TodoDto todoDto) {
		
		//convert TodoDto into Todo Jpa entity
//		Todo todo = new Todo();
//		todo.setTitle(todoDto.getTitle());
//		todo.setDescription(todoDto.getDescription());
//		todo.setComplete(todoDto.isCompleted());
		
		Todo todo = modelMapper.map(todoDto, Todo.class);
		
		//Todo Jpa entity
		Todo savedTodo = todoRepository.save(todo);
		
		//convert saved Todo Jpa entity object into TodoDto object
//		TodoDto saveTodoDto = new TodoDto();
//		saveTodoDto.setId(savedTodo.getId());
//		saveTodoDto.setTitle(savedTodo.getTitle());
//		saveTodoDto.setDescription(savedTodo.getDescription());
//		saveTodoDto.setCompleted(savedTodo.isComplete());
		
		TodoDto saveTodoDto = modelMapper.map(savedTodo, TodoDto.class);
		
		return saveTodoDto;
	}

	@Override
	public TodoDto getTodo(Long id) {
		
		Todo todo =  todoRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Todo not found with id: "+id));
		
		
		return modelMapper.map(todo, TodoDto.class);
	}

	@Override
	public List<TodoDto> getAllTodos() {
		
		List<Todo> todos = todoRepository.findAll();
		
		return todos.stream().map((todo)-> modelMapper.map(todo, TodoDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public TodoDto updateTodo(TodoDto todoDto, Long id) {
		
		Todo todo = todoRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Todo not found with id: "+id));
		
		todo.setTitle(todoDto.getTitle());
		todo.setDescription(todoDto.getDescription());
		todo.setComplete(todoDto.isCompleted());
		
		Todo updatedTodo = todoRepository.save(todo);
		
		return modelMapper.map(updatedTodo, TodoDto.class);
	}

	@Override
	public void deleteTodo(Long id) {
		
		todoRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Todo not found with id: "+id));
		
		todoRepository.deleteById(id);
	}

	@Override
	public TodoDto completeTodo(Long id) {
		Todo todo =  todoRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Todo not found with id: "+id));
		
		todo.setComplete(Boolean.TRUE);
		
		Todo updatedtodo = todoRepository.save(todo);
		
		
//		TodoDto todoDto = new TodoDto();
//        todoDto.setId(todo.getId());
//        todoDto.setTitle(todo.getTitle());
//        todoDto.setDescription(todo.getDescription());
//        todoDto.setCompleted(todo.isComplete());
		
		
		return modelMapper.map(updatedtodo, TodoDto.class);
	}

	@Override
	public TodoDto inCompleteTodo(Long id) {
		
		Todo todo =  todoRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Todo not found with id: "+id));
		
		todo.setComplete(Boolean.FALSE);
		
//		TodoDto todoDto = new TodoDto();
//        todoDto.setId(todo.getId());
//        todoDto.setTitle(todo.getTitle());
//        todoDto.setDescription(todo.getDescription());
//        todoDto.setCompleted(todo.isComplete());
		
		Todo updatedTodo = todoRepository.save(todo);
		
		return modelMapper.map(updatedTodo, TodoDto.class);
	}

}
