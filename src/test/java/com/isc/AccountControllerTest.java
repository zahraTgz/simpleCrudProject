package com.isc;

import com.isc.entity.Account;
import com.isc.service.AccountService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AccountService accountService;

    @Test
    @WithMockUser(username = "admin", authorities = {"ADMIN"})
    void getAccount() throws Exception {

        List<Account> mockAccounts = new ArrayList<>();
        mockAccounts.add(Account.builder().id(1L).name("John").email("john@example.com").age(21).build());
        mockAccounts.add(Account.builder().id(2L).name("Jane").email("jane@example.com").age(20).build());
        Mockito.when(accountService.getAccount()).thenReturn(mockAccounts);

        mockMvc.perform(MockMvcRequestBuilders.get("/account"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("John")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is("Jane")));
    }

    @Test
    @WithMockUser(username = "user", authorities = {"USER"})
    void updateAccount() throws Exception {
        Account mockUpdateAccount = Account.builder().id(1L).name("John").email("john@example.com").age(32).build();

        Mockito.when(accountService.findById(mockUpdateAccount.getId())).thenReturn(mockUpdateAccount);

        Mockito.when(accountService.updateAccount(anyLong(), any(Account.class)))
                .thenReturn(mockUpdateAccount);

        mockMvc.perform(MockMvcRequestBuilders.put("/account/{id}", mockUpdateAccount.getId())
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "id": 1,
                                  "name": "John",
                                  "email": "john@example.com",
                                  "age": 32
                                }
                                """))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Account serviceById = accountService.findById(mockUpdateAccount.getId());
        Assertions.assertEquals(serviceById.getAge(), mockUpdateAccount.getAge());
    }

}
