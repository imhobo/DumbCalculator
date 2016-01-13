# DumbCalculator
A dumb android Calculator which doesn't calculate itself but sends a request to a python server for computation.

**Android Client is present in `master1` branch.**

**Python server is present in `master2` branch.**

#Instructions

0. Install `Flask` library for python.
1. Run `server.py` . This will start the server on your ip address at port 7000
2. Try opening `https://localhost:7000` to see if it is working.
3. Run the `DumbCalculator` after ensuring that it is connected to the same network as the server.
4. Use it like a normal calculator. You can check the log by refreshing `https://localhost:7000`.
5. You can check the server cache at `https://localhost:7000/cache`

