package rpc;

import java.io.*;

public class Message {
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    public static final String OK = "ok";
    public static final String ERROR = "error";

    private String header;
    private String body;

    public Message(String header, String body) {
        this.header = header;
        this.body = body;
    }

    public Message() {

    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Message{" +
                "header='" + header + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

    public void readFrom(InputStream inStream) throws IOException {
        var br = new BufferedReader(new InputStreamReader(inStream));
        header = br.readLine();
        body = br.readLine();
    }

    public void writeTo(OutputStream outStream) throws IOException {
        outStream.write((header + LINE_SEPARATOR + body + LINE_SEPARATOR).getBytes());
    }
}
