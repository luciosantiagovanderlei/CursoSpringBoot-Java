package com.educandoweb.course.resources.exceptions;

import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ResourceExceptionHandlerTest {


    @Test
    void deve_retornar_responseEntity_not_found_quando_resouceNotFound() {

        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("");

        ResourceNotFoundException exception = new ResourceNotFoundException(1L);

        ResponseEntity<StandardError> response = new ResourceExceptionHandler().resouceNotFound(exception,request);

        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());

    }

    @Test
    void deve_retornar_responseEntity_BadRequest_quando_databaseExceptionHandler() {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        when(request.getRequestURI()).thenReturn("");

        DatabaseException exception = new DatabaseException("");

        ResponseEntity<StandardError> response = new ResourceExceptionHandler().databaseExceptionHandler(exception,request);

        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
    }
}