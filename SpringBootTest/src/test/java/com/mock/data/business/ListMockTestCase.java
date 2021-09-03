package com.mock.data.business;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class ListMockTestCase {

	@Test
	void testMockListSizeMethod() {
		List<String> mockList = mock(List.class);
		when(mockList.size()).thenReturn(2).thenReturn(3);
		
		assertEquals(2, mockList.size());
		assertEquals(3, mockList.size());
	}

	@Test
	void testMockListGetMethod() {
		List<String> mockList = mock(List.class);
		when(mockList.get(0)).thenReturn("Sushant");
		
		// when(mockList.get(0)).thenThrow(new RuntimeException("Runtime error"));
		// assertThrows(RuntimeException.class, () -> mockList.get(0));
		
		// Argument Matchers
		// when(mockList.get(anyInt())).thenReturn("Sushant");
		
		assertEquals("Sushant", mockList.get(0));
		// When you don't tell mockito what to do then return default value. ;)
		assertEquals(null, mockList.get(1));
	}
}
