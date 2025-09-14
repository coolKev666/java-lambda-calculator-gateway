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

### Option 1: SAM Deploy - Infrastructure as Code (Recommended)

**Creates a CloudFormation stack that manages all resources as a single unit.**

**Benefits:**
- ✅ Repeatable deployments
- ✅ Version controlled infrastructure
- ✅ Easy rollback: `aws cloudformation delete-stack`
- ✅ Multiple environments (dev/staging/prod)
- ✅ Team collaboration

1. **Build and deploy**:
   ```bash
   gradle buildZip
   sam deploy --guided
   ```
   
2. **Follow prompts**:
   - Stack name: `your-calculator-stack`
   - AWS Region: `us-east-2` (or your preferred region)
   - Confirm changes: `Y`
   - Allow SAM to create IAM roles: `Y`

### Option 2: Manual AWS Console Deploy - Individual Resources

**Manually create each AWS resource through the console (not recommended for production).**

**Drawbacks:**
- ❌ Manual steps (error-prone)
- ❌ Hard to reproduce
- ❌ No version control
- ❌ Difficult cleanup
- ❌ Not suitable for teams

1. **Create deployment package**:
   ```bash
   gradle buildZip
   ```

2. **Manual steps**:
   - Go to Lambda Console → Create function
   - Upload `build/distributions/lambda-calculator.zip`
   - Set handler: `com.example.calculator.CalculatorHandler::handleRequest`
   - Go to API Gateway Console → Create REST API
   - Configure API Gateway trigger with Lambda Proxy Integration
   - Deploy API to stage

## Verify Deployment

1. **Check stack status**:
   ```bash
   aws cloudformation describe-stacks --stack-name your-stack-name
   ```
   Look for `"StackStatus": "CREATE_COMPLETE"`

2. **Get stack resources**:
   ```bash
   aws cloudformation describe-stack-resources --stack-name your-stack-name
   ```
   Find the API Gateway ID from `ServerlessRestApi` resource

3. **Get API endpoint**:
   ```bash
   aws cloudformation describe-stacks \
     --stack-name your-stack-name \
     --query 'Stacks[0].Outputs'
   ```

## Test Deployed API

Use your API Gateway endpoint (format: `https://API-ID.execute-api.REGION.amazonaws.com/Prod/calculate`):

```bash
# Addition
curl -X POST https://your-api-endpoint/calculate \
  -H "Content-Type: application/json" \
  -d '{"a": 10, "b": 5, "operation": "add"}'

# Subtraction
curl -X POST https://your-api-endpoint/calculate \
  -H "Content-Type: application/json" \
  -d '{"a": 15, "b": 3, "operation": "subtract"}'

# Multiplication
curl -X POST https://your-api-endpoint/calculate \
  -H "Content-Type: application/json" \
  -d '{"a": 4, "b": 7, "operation": "multiply"}'

# Division
curl -X POST https://your-api-endpoint/calculate \
  -H "Content-Type: application/json" \
  -d '{"a": 20, "b": 4, "operation": "divide"}'
```

**Expected Response**:
```json
{
  "result": 15.0,
  "operation": "addition",
  "success": true,
  "error": null
}
```

## Prerequisites

- Java 21+
- Docker Desktop (for local testing)
- AWS CLI configured
- AWS SAM CLI: `brew install aws-sam-cli`
- AWS IAM permissions:
  - `AWSCloudFormationFullAccess`
  - `AWSLambda_FullAccess` 
  - `AmazonAPIGatewayAdministrator`
  - `IAMFullAccess`