#!/usr/bin/env python
import socket

TCP_IP = '172.217.163.100'
TCP_PORT = 9001
BUFFER_SIZE = 1024

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((TCP_IP, TCP_PORT))
with open('received_file', 'wb') as f:
	print("file opened")
	while True:
		print("Receiving data...")
		data = s.recv(BUFFER_SIZE)
		print('data=%s', (data))
		if not data:
			f.close()
			print('file closed')
			break

		f.write(data)

print('Successfully get the file')
s.close()
print('connection refused')
