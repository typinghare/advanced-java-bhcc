# Networking

<p style="font-size:2rem; color: #ff006e;">I don't know how to code up unit tests for my this socket application, it kept being blocked when I ran it. For testing this project, you should follow the instructions as follow.</p>

1. Run the following command in the terminal. The terminal will then block after a while for listening sockets.

   ~~~bash
   mvn compile  exec:java -Dexec.mainClass="edu.bhcc.networking.WeatherServer"
   ~~~

2. Open another terminal and run the following maven test command:

   ~~~bash
   mvn test
   ~~~

I am not sure whether it works on other computers, but it currently works well on my computer.

By the way I really write a lot of loggings in my code, but when I run it by

~~~bash
mvn compile  exec:java -Dexec.mainClass="edu.bhcc.networking.WeatherServer"
~~~

It output the following warning out of my expectation:

~~~
SLF4J: No SLF4J providers were found.
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See https://www.slf4j.org/codes.html#noProviders for further details.
~~~

However, you can see the logging information when running `mvn test`.
