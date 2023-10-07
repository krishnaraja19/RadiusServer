# RadiusServer

Assignment
----------
The assignment is to write a RADIUS server to the provided client. The client talks RADIUS using PAP over UDP.

How to run the client
---------------------
java -cp radiusclient.jar se.nexus.interview.radius.packet.Main 127.0.0.1 1812 sharedsecret
	
### Test cases
Tech Stack: Java 19, Maven and GitHub

1. successfully logging in with "frans1"/"fran123!" -- Working fine
2. successfully logging in with "frans2"/"fran123!" -- Working fine
3. other cases should fail with a sensible return value and behaviour according to the rfc extract. -- Radius code is unknown.
  
4. In rfc document I could not able to add the failed status code. I tried my best and I have used Radius Server core from Maven Central libraries.
