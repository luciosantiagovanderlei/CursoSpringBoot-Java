package com.educandoweb.course.services;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class UserServiceTest {
    private UserRepository repository;
    private UserService service;

    @BeforeEach
    void setUp() {
        this.repository = Mockito.mock(UserRepository.class);
        this.service = new UserService(repository);
    }

    @Test
    void deve_retornar_uma_lista_de_usuarios_quando_for_chamado_findAll() {
        List<User> expectedResponse = new ArrayList<>();
        expectedResponse.add(new User(1L,"","","",""));

        when(repository.findAll()).thenReturn(expectedResponse);

        List<User> response = service.findAll();

        assertEquals(expectedResponse, response);
        verify(repository,times(1)).findAll();
    }
    @Test
    void deve_retornar_erro_quando_for_chamado_findAll(){
        when(repository.findAll()).thenThrow(new ResourceNotFoundException(1L));

        Assertions.assertThrows(ResourceNotFoundException.class,() -> service.findAll());
    }

    @Test
    void findById() {
    }

    @Test
    void insert() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }
}