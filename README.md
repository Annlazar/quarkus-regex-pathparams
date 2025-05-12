# quarkus-regex-pathparams
Reproducer for REST path parameters regex issue.

## Issue Description
From version **3.17.0.CR1** onward, path parameters defined with regex are not working as expected.

### Expected Behavior (Up to Quarkus 3.16.4)
When specifying a path parameter with regex, it was possible to define optional path parameters. For example:

```java
@Path("/params/{op}{left : (/[^/]*)?}{right : (/[^/]*?)}")
```
The above regex allows the left and right path parameters to be empty.
1.  **Request**:

    http://localhost:8081/test/params/greetings/hello/world

     **Result**:
   
    op: greetings, left: hello, right: world


2. **Request (without the optional right parameter)**:
 
    http://localhost:8081/test/params/greetings/hello
    
    **Result**:

    op: greetings, left: hello, right:

### Actual Behavior (From Quarkus 3.17.0.CR1)
With version 3.17.0.CR1, the value of the left parameter is incorrectly assigned to the right parameter.
The above regex allows the left and right path parameters to be empty.

1.  **Request**:

    http://localhost:8081/test/params/greetings/hello/world
    
    **Result**:
    
    op: greetings, left: hello, right: world



2. **Request (without the optional right parameter):**

    http://localhost:8081/test/params/greetings/hello

    **Result**:

   op: greetings, left: , right: hello

### Error Details
When the right parameter is omitted, the following error occurs:

```
java.lang.AssertionError: 1 expectation failed.
Response body doesn't match expectation.
Expected: is "op: greetings, left: hello, right: "
Actual: op: greetings, left: , right: hello
```