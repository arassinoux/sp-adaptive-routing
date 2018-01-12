package simulation;

import messages.TypeConnection;

import java.io.*;
import java.net.Socket;

class ConnectionClient {

    private final ObjectInputStream reader;
    private final ObjectOutputStream writer;
    private final Socket socket;
    private TypeConnection type;
    public boolean stopped = false;

    public ConnectionClient(Socket socket) throws IOException {
        this.socket = socket;
        this.writer = new ObjectOutputStream(socket.getOutputStream());
        this.reader = new ObjectInputStream(socket.getInputStream());
    }

    public ObjectInputStream getReader() {
        return reader;
    }

    public ObjectOutputStream getWriter() {
        return writer;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setType(TypeConnection type) {
        this.type = type;
    }

    public TypeConnection getType() {
        return type;
    }
}
