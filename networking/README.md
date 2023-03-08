# Networking

<p style="font-size:2rem; color: #ff006e;">I don't know how to write unit tests for my this socket application, it kept being stuck when I run it. For testing this project, you should follow the instructions as follow.</p>

1. Run the following command in the terminal. The terminal will then block after a while for listening sockets.

   ~~~bash
   mvn compile  exec:java -Dexec.mainClass="edu.bhcc.networking.WeatherServer"
   ~~~

2. Open another terminal and run the following maven test command:

   ~~~bash
   mvn test
   ~~~

I am not sure whether it works on other computers, but it currently work well on my computer.
