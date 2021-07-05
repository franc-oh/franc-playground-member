package com.playground.member.form;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IndexControllerTest {

    @Autowired
    MockMvc mockMvc;


    /**
     * index 페이지 - 미인증 사용자 접근 + 인증 사용자 접근
     * @throws Exception
     */
    @Test
    public void index_page() throws Exception {
        String viewName = "/index";
        String message_anonymous = "Welcome";
        String message_user = "Welcome user@naver.com";

        mockMvc.perform(get("/").with(anonymous()))
                .andExpect(status().isOk())
                .andExpect(model().attribute("message", message_anonymous))
                .andExpect(view().name(viewName))
                .andDo(print());

        mockMvc.perform(get("/").with(user("user@naver.com").roles("USER")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("message", message_user))
                .andExpect(view().name(viewName))
                .andDo(print());
    }

    /**
     * admin 페이지 - 유저권한이 접근했을 때 권한에 걸리는 지...
     * @throws Exception
     */
    @Test
    @WithMockUser(username = "user@naver.com", roles = "USER")
    public void admin_page_fail() throws Exception{

        mockMvc.perform(get("/admin"))
                .andExpect(status().isForbidden())
                .andDo(print());
    }

    /**
     * admin 페이지 - 정상 접근 테스트
     * @throws Exception
     */
    @Test
    @WithMockUser(username = "admin@naver.com", roles = "ADMIN")
    public void admin_page_success() throws Exception{
        String message = "Admin admin@naver.com";
        String viewName = "/admin";

        mockMvc.perform(get("/admin"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("message", message))
                .andExpect(view().name(viewName))
                .andDo(print());
    }

    /**
     * user 페이지 - 정상 접근 테스트(어드민, 유저)
     * @throws Exception
     */
    @Test
    public void user_page_success() throws Exception{
        String user_message = "User user@naver.com";
        String admin_message = "User admin@naver.com";
        String viewName = "/user";

        mockMvc.perform(get("/user").with(user("user@naver.com").roles("USER")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("message", user_message))
                .andExpect(view().name(viewName))
                .andDo(print());

        mockMvc.perform(get("/user").with(user("admin@naver.com").roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(model().attribute("message", admin_message))
                .andExpect(view().name(viewName))
                .andDo(print());
    }

}