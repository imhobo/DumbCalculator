import socket, parse, cache, time
from flask import Flask, render_template  
from thread import *    

app = Flask(__name__)


class Server:
    def __init__(self, port = 5000, host = "10.42.0.45"):
	self.host = host # Get local machine name
	self.port = port                # Reserve a port for your service.

	start_new_thread(app.run, ('0.0.0.0',7000))
	start_new_thread(self.begin, ())
	time.sleep(60000)
	


    @app.route('/')
    def main():
 	return render_template("log.html")

    @app.route('/cache')
    def cache():
 	return render_template("db.txt")


    def begin(self):
    
    	f = open("templates/log.html", "a+")
	f.write("Server started on " + time.strftime("%d/%m/%Y") + " at " + time.strftime("%H:%M:%S") + "<br>")
	f.close()

    	s = socket.socket()         # Create a socket object
    	s.bind((self.host, self.port))        # Bind to the port
	obj = parse.Parser()
	db = cache.Cache(20)
	s.listen(5)                 # Now wait for client connection.
    	
	while True:
	   self.f = open("templates/log.html", "a+")
	   c, addr = s.accept()     # Establish connection with client.
	   print 'Got connection from', addr
	   self.f.write('Got connection from' + str(addr) + "<br>")
	   str1 = c.recv(1024)
	   str1 = str1.rstrip('\n')
	   print "Query : ",str1
	   self.f.write("Query : "+str1+"<br>")
	   res = db.findQuery(str1)
	   if res == "NO":
	   	res = obj.parse(str1)
	   	db.writeQuery(str1, res)
	   	print "Result calculated : ",res
	   	self.f.write("Result calculated : "+res+"<br>")
	   else :
	       print "Result from cache : ",res
	       self.f.write("Result from cache : "+res+"<br>")
	   c.sendall(res)
	   c.close()
	   self.f.close()   
	   
	   
if __name__ == "__main__":
    obj = Server()

