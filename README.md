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

## Local Development & Testing

### Prerequisites
- Java 21+
- Docker Desktop
- AWS SAM CLI: `brew install aws-sam-cli`

### Run Locally

1. **Build the project**:
   ```bash
   gradle buildZip
   ```

2. **Start local API**:
   ```bash
   sam local start-api
   ```
   This starts a local server at `http://127.0.0.1:3000`

3. **Test locally**:
   ```bash
   # Addition
   curl -X POST http://127.0.0.1:3000/calculate \
     -H "Content-Type: application/json" \
     -d '{"a": 10, "b": 5, "operation": "add"}'
   
   # Division
   curl -X POST http://127.0.0.1:3000/calculate \
     -H "Content-Type: application/json" \
     -d '{"a": 20, "b": 4, "operation": "divide"}'
   ```

4. **Run unit tests**:
   ```bash
   gradle test
   ```

## AWS Deployment

1. **Create deployment package**:
   ```bash
   gradle buildZip
   ```

2. **Deploy to AWS Lambda**:
   - Upload `build/distributions/lambda-calculator.zip`
   - Set handler: `com.example.calculator.CalculatorHandler::handleRequest`
   - Configure API Gateway trigger with Lambda Proxy Integration

## Prerequisites

- Java 21+
- Docker Desktop (for local testing)
- AWS CLI configured
- AWS Lambda and API Gateway permissions