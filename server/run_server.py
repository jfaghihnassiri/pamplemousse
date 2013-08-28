#!/usr/bin/python

# =========== Generic Named User Server ============
# this program is intended to allow clients to communicate
# with each other without knowing each other's IP address

# == Module Imports ==
import socket
import sys
import os
import time
from datetime import date
from datetime import datetime
import threading
import thread
import Queue

# == Static Variables ==
# TCP buffer
buffer_len = 256

# Clients
num_clients = 0;
sockfd = None
client = None
client_ready = False
num_clients = 0
MAX_NUM_CLIENTS = 5

# TCP Server
def tcp_serve():
	while True:
		time.sleep(0.001) # to release processor
		# wait for a connection
		print "Ready and waiting"
		global client, client_ready
		client, address = sockfd.accept()
		print "    < incoming connection: " + str(address) + " > "
		client_ready = True
		while client_ready:
			time.sleep(0.001) # to release processor
			# receive up to 1024 bytes
			msg = None # clear message first
			msg = (client.recv(buffer_len)).strip()
			print "    < Received query: " + msg + " > "
			# message should be of the form "gs year month day" (get stream)
			

# Main Function
if __name__ == '__main__':
	arg_len = len(sys.argv)
	if arg_len != 1:
		print "ERROR:"
		print "usage :python run_server.py"
		sys.exit(0)
    
	host = ""
	port = 25000
	print "====================================================="
	print "          Starting Named Client Server             "
	print "====================================================="
	#print "Killing any previous port on " + str(port)
	#kill_str = 'fuser -k -n tcp 25000'
	#os.system(kill_str)
	#time.sleep(3)
	print "Opening port " + str(port) + " on localhost for TCP queries...",
	sockfd = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
	sockfd.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
	sockfd.bind((host, port))
	# we can handle up to 5 queued requests
	sockfd.listen(5)
	print "DONE"

	val = 0

	while True:
		if val == 0:
			val = 1
			for i in range(0,MAX_NUM_CLIENTS)
				t_cp = threading.Thread(target=tcp_serve)
				t_cp.start()









