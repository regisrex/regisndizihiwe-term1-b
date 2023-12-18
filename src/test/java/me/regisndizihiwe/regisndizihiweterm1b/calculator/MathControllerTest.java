package me.regisndizihiwe.regisndizihiweterm1b.calculator;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.regisndizihiwe.regisndizihiweterm1b.calculator.dto.DoMathRequest;
import me.regisndizihiwe.regisndizihiweterm1b.exceptions.InvalidOperationException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MathControllerTest {

    @Mock
    private static MathOperatorImpl mathOperatorMock = mock(MathOperatorImpl.class);


    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    public static void setUp() throws InvalidOperationException {
        when(mathOperatorMock.doMath(1, 2, "+")).thenReturn(3.0);
        when(mathOperatorMock.doMath(4, 0, "/")).thenThrow(new InvalidOperationException("Cannot divide by zero"));
        when(mathOperatorMock.doMath(6, 6, "&")).thenThrow(new RuntimeException("Unknown operation"));
    }


    @Test
    void shouldreturnOperationResult() throws InvalidOperationException {
        when(mathOperatorMock.doMath(1, 2, "+")).thenReturn(3.0);
        assertEquals(3.0, mathOperatorMock.doMath(1, 2, "+"));
    }


    @Test
    void givenANumber_whenDividedByZero_itThrowAnError() throws InvalidOperationException {
        when(mathOperatorMock.doMath(4, 0, "/")).thenThrow(new InvalidOperationException("Cannot divide by zero"));
        assertThrows(InvalidOperationException.class, () -> mathOperatorMock.doMath(4, 0, "/"));
    }

    @Test
    void givenAnUnknownOperator_whenUsed_itThrowsRuntimeException() throws InvalidOperationException {
        when(mathOperatorMock.doMath(6, 6, "&")).thenThrow(new RuntimeException("Unknown operation"));
        assertThrows(RuntimeException.class, () -> mathOperatorMock.doMath(6, 6, "&"));
    }


    @Test
    public void should_return_calcResponse() throws Exception {
        when(mathOperatorMock.doMath(2.0, 3.0, "+")).thenReturn(5.0);
        DoMathRequest doMathRequest = new DoMathRequest(2, 3, "+");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/math-operator/do-math")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content((new ObjectMapper()).writeValueAsString(doMathRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.calcResponse").value(5.0));
    }


    @Test
    public void should_returnNotAcceptable() throws Exception {
        when(mathOperatorMock.doMath(4, 0, "/")).thenThrow(new InvalidOperationException("Cannot divide by zero"));

        DoMathRequest doMathRequest = new DoMathRequest(4, 0, "/");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/math-operator/do-math")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content((new ObjectMapper()).writeValueAsString(doMathRequest)))
                .andExpect(status().isNotAcceptable());
    }

    @Test
    public void should_throwRuntimeException() throws Exception {
        when(mathOperatorMock.doMath(6, 6, "$")).thenThrow(new RuntimeException("Cannot divide by zero"));

        DoMathRequest doMathRequest = new DoMathRequest(6, 6, "$");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/math-operator/do-math")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content((new ObjectMapper()).writeValueAsString(doMathRequest)))
                .andExpect(status().isInternalServerError());
    }


}
