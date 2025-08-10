# Lambda Calculator API

A simple calculator API built with AWS Lambda and API Gateway supporting basic arithmetic operations.

## Features

- **Add**: Addition of two numbers
- **Subtract**: Subtraction of two numbers  
- **Multiply**: Multiplication of two numbers
- **Divide**: Division of two numbers (with zero-division protection)

## API Usage

**Endpoint**: POST to your API Gateway URL

**Request Body**:
```json
{
  "a": 10,
  "b": 5,
  "operation": "add"
}
```

**Response**:
```json
{
  "result": 15.0,
  "operation": "addition",
  "success": true
}
```

## Operations

- `"add"` - Addition
- `"subtract"` - Subtraction  
- `"multiply"` - Multiplication
- `"divide"` - Division

## Build & Deploy

1. **Build the project**:
   ```bash
   ./gradlew build
   ```

2. **Create deployment package**:
   ```bash
   ./gradlew buildZip
   ```

3. **Deploy to AWS Lambda**:
   - Upload `build/distributions/lambda-calculator.zip`
   - Set handler: `com.example.calculator.CalculatorHandler::handleRequest`
   - Configure API Gateway trigger

## Prerequisites

- Java 21+
- AWS CLI configured
- AWS Lambda and API Gateway permissions