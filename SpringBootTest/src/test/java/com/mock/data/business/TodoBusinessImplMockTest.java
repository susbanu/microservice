package com.mock.data.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.mock.data.api.TodoService;

class TodoBusinessImplMockTest {

	@Test
	void usingAStub() {
		TodoService todoServiceMock = mock(TodoService.class);
		when(todoServiceMock.retrieveTodos("dummy")).thenReturn(Arrays.asList("Learn Spring MVC", "Learn Spring", "Learn to Dance"));
		
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
		List<String> todos = todoBusinessImpl.retrieveTodosRelatedToSpring("dummy");
		assertEquals(2, todos.size());
	}

}
