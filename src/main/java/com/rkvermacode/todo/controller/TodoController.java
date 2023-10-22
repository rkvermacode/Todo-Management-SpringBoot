package com.rkvermacode.todo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rkvermacode.todo.dto.TodoDto;
import com.rkvermacode.todo.service.TodoService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/todos")
@AllArgsConstructor
public class TodoController {
	
	private TodoService todoService;
	
	//build  Add Todo REST API
	@PostMapping
	public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto){
		TodoDto savedTodo = todoService.addTodo(todoDto);
		return new ResponseEntity<>(savedTodo,HttpStatus.CREATED);
	}
	
	//build Get Todo REST API
	@GetMapping("{id}")
	public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long id){
		TodoDto todoDto = todoService.getTodo(id);
		return new ResponseEntity<>(todoDto,HttpStatus.OK);
	}
	
	//Build Get All Todos REST API
	@GetMapping
	public ResponseEntity<List<TodoDto>> getAllTodos(){
		List<TodoDto> todos = todoService.getAllTodos();
		return new ResponseEntity<>(todos,HttpStatus.OK);
	}
	
	//Build update Todo REST API
	@PutMapping("{id}")
	public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto, @PathVariable("id") Long id){
		
		TodoDto updatedTodo = todoService.updateTodo(todoDto, id);
		
		return ResponseEntity.ok(updatedTodo);
	}
	
	//Build Delete REST API
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteTodo(@PathVariable("id") Long id){
		
		todoService.deleteTodo(id);
		
		return ResponseEntity.ok("Todo Deleted Successfully!...");
	}
	
	
	//Build complete Todo REST API
	@PatchMapping("{id}/completed")
	public ResponseEntity<TodoDto> completeTodo(@PathVariable("id") Long id){
		TodoDto updateTodo = todoService.completeTodo(id);
		return ResponseEntity.ok(updateTodo);
	}
	
	//Build complete Todo REST API
		@PatchMapping("{id}/in-completed")
		public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable("id") Long id){
			TodoDto updateTodo = todoService.completeTodo(id);
			return ResponseEntity.ok(updateTodo);
		}
	
}
