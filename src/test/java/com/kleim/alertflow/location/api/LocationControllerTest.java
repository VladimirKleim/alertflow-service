package com.kleim.alertflow.location.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kleim.alertflow.location.domain.CreateLocationRequestDto;
import com.kleim.alertflow.security.auth.domain.User;
import com.kleim.alertflow.security.auth.domain.UserDepartment;
import com.kleim.alertflow.security.auth.domain.UserRole;
import com.kleim.alertflow.security.auth.domain.UserService;
import com.kleim.alertflow.security.token.JwtTokenManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class LocationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private JwtTokenManager jwtTokenManager;
    @MockitoBean
    private UserService userService;

    @BeforeEach
    void setUp() {

            when(jwtTokenManager.isTokenValid(anyString())).thenReturn(true);
            when(jwtTokenManager.getLoginFromToken(anyString())).thenReturn("testuser");
            when(jwtTokenManager.getRoleFromToken(anyString())).thenReturn("GUEST");
            when(jwtTokenManager.getIdFromToken(anyString())).thenReturn("1");
        User testUser = new User(
                1L,
                "testuser",
                "password",
                UserDepartment.ANALYST_DEPARTMENT,
                UserRole.GUEST
        );
        when(userService.getUserByLogin("testuser")).thenReturn(testUser);
    }

    @Test
    void createLocation_withValidRequest_returnCreated() throws Exception {

        var location = new CreateLocationRequestDto(
                null,
                "Офис Москва",
                "ул. Тверская, 1",
                50,
                "Главный офис компании"
        );

        String testToken = "test.valid.jwt.token";

        mockMvc.perform(MockMvcRequestBuilders.post("/location")
                        .header("Authorization", "Bearer " + testToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(location)))
                .andExpectAll(
                        status().isCreated(),
                        content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
                        jsonPath("$.id").isNumber()
                )
                .andDo(document("create-location",
                        requestFields(
                                fieldWithPath("name").description("Наименование локации"),
                                fieldWithPath("address").description("Адрес локации"),
                                fieldWithPath("workers").description("Количество работников"),
                                fieldWithPath("description").description("Описание локации").optional()
                        ),
                        responseFields(
                                fieldWithPath("id").description("ID созданной локации"),
                                fieldWithPath("name").description("Наименование локации"),
                                fieldWithPath("address").description("Адрес локации"),
                                fieldWithPath("workers").description("Количество работников"),
                                fieldWithPath("description").description("Описание локации")
                        )
                ));
    }



}
