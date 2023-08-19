package com.spring.community.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.community.entity.Post;
import com.spring.community.service.PostService;
import com.spring.community.service.PostServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.parameters.P;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostControllerTest {



}