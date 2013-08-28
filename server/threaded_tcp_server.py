from socket import *
import thread
import sys

# -- variables --
BUFF = 1024
# named messaging
users = [];

def response(key):
	return 'Server response: ' + key

def handler(clientsock,addr):
	while 1:
		data = clientsock.recv(BUFF)
		if not data: break
		print repr(addr) + ' recv:' + repr(data)
		clientsock.send(response(data))
		print repr(addr) + ' sent:' + repr(response(data))
		if "close" == data.rstrip(): break # type 'close' on client console to close connection from the server side
    
	clientsock.close()
	print addr, "- closed connection" #log on console

if __name__=='__main__':
	if len(sys.argv) != 2:
		print "ERROR:"
		print "usage :python threaded_tcp_server.py <PORT_NUM>"
		sys.exit(0)
		
	PORT = int(sys.argv[1])
	HOST = '0.0.0.0' # accept all IPs
	ADDR = (HOST, PORT)
	serversock = socket(AF_INET, SOCK_STREAM)
	serversock.setsockopt(SOL_SOCKET, SO_REUSEADDR, 1)
	serversock.bind(ADDR)
	serversock.listen(5)
	while 1:
		print 'waiting for connection... listening on port', PORT
		clientsock, addr = serversock.accept()
		print '...connected from:', addr
		thread.start_new_thread(handler, (clientsock, addr))
