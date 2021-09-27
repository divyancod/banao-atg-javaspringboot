package com.banao.task.Contoller;

import com.banao.task.JwtUtils.JwtTokenResponse;
import com.banao.task.Model.MyUser;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collection;
import java.util.HashSet;

import static org.junit.Assert.*;

public class UnknownClass extends APIControllerTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void checkLoginEndPoint() throws Exception {
        String uri = "/api/auth/login";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .param("email", "iamadmin@test.com").param("password", "admin123")
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        JwtTokenResponse jwtTokenResponse = super.mapFromJson(content, JwtTokenResponse.class);
        assertNotNull(jwtTokenResponse);
    }

    @Test
    public void checkGetUserListEndPoint() throws Exception {
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        Collection authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("admin"));
        Mockito.when(authentication.getAuthorities()).thenReturn(authorities);
        String uri = "/api/users/list";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        MyUser[] myUsers = super.mapFromJson(content, MyUser[].class);
        assertTrue(myUsers.length > 0);
    }
}
