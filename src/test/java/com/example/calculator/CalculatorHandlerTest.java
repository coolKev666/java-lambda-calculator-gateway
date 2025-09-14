package com.example.calculator;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorHandlerTest {
    
    @Test
    public void testAddition() {
        CalculatorHandler handler = new CalculatorHandler();
        
        APIGatewayProxyRequestEvent request = new APIGatewayProxyRequestEvent();
        request.setBody("{\"a\": 10, \"b\": 5, \"operation\": \"add\"}");
        
        APIGatewayProxyResponseEvent response = handler.handleRequest(request, null);
        
        assertEquals(200, response.getStatusCode());
        assertTrue(response.getBody().contains("15.0"));
    }
}