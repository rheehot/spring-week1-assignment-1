package com.codesoom.assignment.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    void makeTask() {
        final long id = 1;
        final String title = "sample";
        Task task = new Task(id, title);

        assertEquals(id, task.id());
        assertEquals(title, task.title());
    }

    @Test
    void parseTaskByJackson() throws JsonProcessingException {
        final long id = 1;
        final String title = "sample";
        final String taskStr = String.format("{\"id\": %d, \"title\": \"%s\"}", id, title);

        ObjectMapper mapper = new ObjectMapper();
        Task task = mapper.readValue(taskStr, Task.class);

        assertEquals(1, task.id());
        assertEquals("sample", task.title());
    }

    @Test
    void taskToString() throws IOException {
        OutputStream outputStream = new ByteArrayOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        Task task = new Task(1, "title");

        mapper.writeValue(outputStream, task);

        assertEquals("{\"id\":1,\"title\":\"title\"}", outputStream.toString());
    }
}
