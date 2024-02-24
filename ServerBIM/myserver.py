import socket
import threading
import http.server
import tblUsers
import tblProyects
import tblObjects
import tblConstructionPapers


class MyServer(http.server.SimpleHTTPRequestHandler):
    
    def __init__(self, host, port):
        self.host = host
        self.port = port
        self.server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.server.bind((self.host, self.port))
        self.server.listen(5)
        print(f"Servidor escuchando en {self.host}:{self.port}")

    def handle_client(self, client_socket):
        try:
            #formato
            formato = "HTTP/1.1 200 OK\nContent-Type: text/plain\n\n"
            request = client_socket.recv(1024)
            print(f"Recibido:\n {request.decode()}")

            request_lines = request.decode()
            data = request_lines.split("\n")
            print("\nLo de java: " + data[8])
            response = "nada server"
            javaData = data[8].split("|")

            #Tbl Users
            if(javaData[0] == "user"):
                response = tblUsers.functions_user(javaData, formato)

            #Tbl proyects
            if(javaData[0] == "proyect"):
                response = tblProyects.functions_proyect(javaData,formato)

            #Tbl objects
            if(javaData[0] == "object"):
                response = tblObjects.functions_object(javaData, formato)

            #Tbl constuctionPapers
            if(javaData[0] == "constructionPaper"):
                response = tblConstructionPapers.functions_constructionPapers(javaData, formato)

            
        except Exception as e:
            print(f"Error al procesar la solicitud: {e}")
        finally:
            print("Lo que devolvió fue: " + response) 
            client_socket.send(response.encode("utf-8"))
            client_socket.close()
            print("\nConexión cerrada")
            print("\n------------------------------------------------------------------")

    def run(self):
        while True:
            client_socket, addr = self.server.accept()
            print(f"Conexión aceptada desde {addr}")
            client_handler = threading.Thread(target=self.handle_client, args=(client_socket,))
            client_handler.start()
