package com.rkvermacode.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rkvermacode.todo.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long>{

}
