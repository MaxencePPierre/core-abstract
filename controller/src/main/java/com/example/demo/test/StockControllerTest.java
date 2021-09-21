package com.example.demo.test;


import com.example.demo.controller.StockController;
import com.example.demo.dto.in.ShoeFilter;
import com.example.jpa.ShoeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(StockController.class)
public class StockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ShoeRepository mockShoeRepository;

    @Before
    public void setUp() {
        mockShoeRepository = Mockito.mock(ShoeRepository.class);
    }

    @Test
    public void getSockTest() throws Exception {
        List<ShoeFilter> shoeFilters = new ArrayList();
        when(mockShoeRepository.findAll()).thenReturn(shoeFilters);

        this.mockMvc.perform(get("/stock")).andDo(print()).andExpect(status().isOk());
    }
}
