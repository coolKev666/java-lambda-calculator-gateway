package com.example.calculator;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class CalculatorHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Access-Control-Allow-Origin", "*");
        response.setHeaders(headers);

        try {
            String body = input.getBody();
            if (body == null || body.isEmpty()) {
                CalculatorResponse errorResponse = new CalculatorResponse("Request body is empty");
                response.setStatusCode(400);
                response.setBody(objectMapper.writeValueAsString(errorResponse));
                return response;
            }
            
            CalculatorRequest request = objectMapper.readValue(body, CalculatorRequest.class);
            CalculatorResponse result = calculate(request);
            
            response.setStatusCode(200);
            response.setBody(objectMapper.writeValueAsString(result));
            
        } catch (Exception e) {
            CalculatorResponse errorResponse = new CalculatorResponse("Error: " + e.getMessage());
            response.setStatusCode(400);
            try {
                response.setBody(objectMapper.writeValueAsString(errorResponse));
            } catch (Exception ex) {
                response.setBody("{\"error\":\"Internal server error\",\"success\":false}");
            }
        }
        
        return response;
    }

    private CalculatorResponse calculate(CalculatorRequest request) {
        double a = request.getA();
        double b = request.getB();
        String operation = request.getOperation().toLowerCase();

        switch (operation) {
            case "add":
                return new CalculatorResponse(a + b, "addition");
            case "subtract":
                return new CalculatorResponse(a - b, "subtraction");
            case "multiply":
                return new CalculatorResponse(a * b, "multiplication");
            case "divide":
                if (b == 0) {
                    return new CalculatorResponse("Cannot divide by zero");
                }
                return new CalculatorResponse(a / b, "division");
            default:
                return new CalculatorResponse("Invalid operation. Use: add, subtract, multiply, divide");
        }
    }
}