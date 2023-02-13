package com.educandoweb.course.resources;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.UserService;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class UserResourceTest {

    private UserService service;
    private UserResource controller;
    @BeforeEach
    void setUp() {
        this.service = Mockito.mock(UserService.class);
        this.controller = new UserResource(service);
    }

    @Test
    void deve_retornar_todos_os_usuarios_quando_for_chamado_o_endpoint_findAll() {
        List<User> expectedResponse = new ArrayList<>();
        expectedResponse.add(new User(1L,"","","",""));

        when(service.findAll()).thenReturn(expectedResponse);

        ResponseEntity<List<User>> response = controller.findAll();

        Assertions.assertEquals(expectedResponse,response.getBody());
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
        Mockito.verify(service,times(1)).findAll();
        Mockito.verify(service,never()).delete(any());
        Mockito.verify(service,never()).insert(any());
        Mockito.verify(service,never()).findById(any());
    }

    @Test
    void deve_retornar_erro_quando_for_chamado_o_endpoint_findAll_e_o_service_retornar_um_erro() {
        when(service.findAll()).thenThrow(new ResourceNotFoundException(1L));

        Assertions.assertThrows(ResourceNotFoundException.class,() -> controller.findAll());
    }

    @Test
    void deve_retornar_um_usuario_quando_for_chamado_o_endpoint_findById() {
        User expectedResponse = new User(1L,"","","","");

        when(service.findById(any())).thenReturn(expectedResponse);

        ResponseEntity<User> response = controller.findById(1L);

        Assertions.assertEquals(expectedResponse,response.getBody());
        Assertions.assertEquals(HttpStatus.OK,response.getStatusCode());
        Mockito.verify(service,times(1)).findById(any());
        Mockito.verify(service,never()).delete(any());
        Mockito.verify(service,never()).insert(any());
        Mockito.verify(service,never()).findAll();
    }
    @Test
    void deve_retornar_erro_quando_for_chamado_o_endpoint_findById_e_o_service_retornar_um_erro() {
        when(service.findById(any())).thenThrow(new ResourceNotFoundException(1L));

        Assertions.assertThrows(ResourceNotFoundException.class,() -> controller.findById(1L));
    }

    @Test
    void deve_retornar_sucesso_quando_for_chamado_o_endpoint_insert() throws URISyntaxException {
        User expectedResponse = new User(1L,"","","","");

        UriComponentsBuilder uriComponentsBuilder = Mockito.mock(UriComponentsBuilder.class);
        UriComponents uriComponents = Mockito.mock(UriComponents.class);
        when(uriComponentsBuilder.path(any())).thenReturn(uriComponentsBuilder);
        when(uriComponentsBuilder.buildAndExpand(anyLong())).thenReturn(uriComponents);
        when(service.insert(any())).thenReturn(expectedResponse);

        ResponseEntity<User> response = controller.insert(expectedResponse, uriComponentsBuilder);

        Assertions.assertEquals(HttpStatus.CREATED,response.getStatusCode());
        Mockito.verify(service,times(1)).insert(any());
        Mockito.verify(service,never()).delete(any());
        Mockito.verify(service,never()).findById(any());
        Mockito.verify(service,never()).findAll();
    }
    @Test
    void deve_retornar_erro_quando_for_chamado_o_endpoint_insert_e_o_service_retornar_um_erro() {

        User request = new User(1L,"","","","");

        when(service.insert(any())).thenThrow(new ResourceNotFoundException(1L));
        UriComponentsBuilder uriComponentsBuilder = Mockito.mock(UriComponentsBuilder.class);
        Assertions.assertThrows(ResourceNotFoundException.class,() -> controller.insert(request, uriComponentsBuilder));
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