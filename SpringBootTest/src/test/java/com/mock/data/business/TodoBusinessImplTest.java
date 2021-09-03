package com.mock.data.business;

import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.mock.data.api.TodoService;
import com.mock.data.api.TodoServiceStub;

class TodoBusinessImplStubTest {

	@Test
	void usingAStub() {
		// Set Stub/dummy service implementation in TodoServiceStub class
		TodoService todoService = new TodoServiceStub();
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
		List<String> todos = todoBusinessImpl.retrieveTodosRelatedToSpring("Sushant");
		assertEquals(2, todos.size());
	}
}
