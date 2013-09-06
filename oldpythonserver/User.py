#!/bin/bash

# -- imported modules --


class User:
	# -- static variables --
	ipaddress = ''
	username = ''

	def __init__(self, ip):
		self.ipaddress = ip;

	def __del__(self):
		a = 1

	def sendMessage(self):
		a = 1
