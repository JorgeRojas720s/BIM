from myserver import MyServer

if __name__ == "__main__":
    host = '172.17.45.50'
    port = 8000

    server = MyServer(host, port)
    server.run()