package com.sg.capstone.service;

import com.sg.capstone.data.HashtagDAO;
import com.sg.capstone.data.PostDAO;
import com.sg.capstone.data.UserDAO;
import com.sg.capstone.model.Post;
import com.sg.capstone.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

class ContentManagementServiceImplTest {
    
    @Autowired
    ContentManagementService service;
    
    @Autowired
    PostDAO postDAO;
    
    @Autowired
    HashtagDAO hashtagDAO;
    
    @Autowired
    UserDAO userDAO;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Sql(scripts = {"file:Capstone_Schema_Test.sql","file:Capstone_data.sql"})
    void addPost() {
        
    }
    
    @Test
    @Sql(scripts = {"file:Capstone_Schema_Test.sql","file:Capstone_data.sql"})
    void addUser() {
        User u = new User();
        u.setFirstName("Test");
        u.setLastName("User");
        // User is incomplete
        assertThrows(Exception.class, () -> service.addUser(u));
        u.setRole("member");
        User newUser = service.addUser(u);
        assertNotEquals(null,newUser);
        assertEquals(newUser,userDAO.getUserById(newUser.getUserId()));
    }
    
    @Test
    @Sql(scripts = {"file:Capstone_Schema_Test.sql","file:Capstone_data.sql"})
    void approvePostById() {
        assertFalse(service.approvePostById(-1));
        Post p = new Post();
        p.setTitle("testTitle");
        p.setContent("test content");
        p.setApproved(false);
        p.setUserId(1);
        p = service.addPost(p, null);
        assertTrue(service.approvePostById(p.getPostId()));
        assertTrue(service.getPostById(p.getPostId(), false, false, false, null).isApproved());
    }
    
    @Test
    @Sql(scripts = {"file:Capstone_Schema_Test.sql","file:Capstone_data.sql"})
    void deletePostById() {
        for(Post p : postDAO.getAllPosts()) {
            assertTrue(service.deletePostById(p.getPostId()));
            assertFalse(service.deletePostById(p.getPostId()));
        }
    }
    
    @Test
    @Sql(scripts = {"file:Capstone_Schema_Test.sql","file:Capstone_data.sql"})
    void editPost() {
        
    }
    
    @Test
    @Sql(scripts = {"file:Capstone_Schema_Test.sql","file:Capstone_data.sql"})
    void getAllPosts() {
        
    }
    
    @Test
    @Sql(scripts = {"file:Capstone_Schema_Test.sql","file:Capstone_data.sql"})
    void getPostById() {
        
    }
}