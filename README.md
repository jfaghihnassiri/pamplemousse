pamplemousse
============

An experiment in socket communication, multi-platform development, and real-time gaming
------------

**Server:**
- python threaded_tcp_server.py 10000

**Client:**
- nc localhost 10000

**Git Etiquette:**
- git add *
- git commit -m 'your commit message'
- git push
- git pull

**Communication Protocol**
- *Header*
- 	Start Delimiter
- 	Control Byte
- 	Number of Commands
- *Command*
- 	Command Type
- 	Command Length
- 	Command Payload

