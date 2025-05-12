# quarkus-regex-pathparams
Reproducer for rest pathparams regex issue

Until Quarkus 3.16.4, the pathparam regex work as expected:
    <quarkus.platform.version>3.16.4</quarkus.platform.version>

1. test: http://localhost:8081/test/params/greetings/hello/world
   op: greetings, left: hello, right: world
2. test: http://localhost:8081/test/params/greetings/hello
   op: greetings, left: hello, right:

From 3.17.0.CR1 on, the regex path params are not working as expected:
    <quarkus.platform.version>3.17.0.CR1</quarkus.platform.version>

1. test: http://localhost:8081/test/params/greetings/hello/world 
   op: greetings, left: hello, right: world
2. test: http://localhost:8081/test/params/greetings/hello
   op: greetings, left: , right: hello
- > java.lang.AssertionError: 1 expectation failed.
   Response body doesn't match expectation.
   Expected: is "op: greetings, left: hello, right: "
   Actual: op: greetings, left: , right: hello
