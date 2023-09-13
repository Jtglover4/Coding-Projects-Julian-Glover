package Lab10;

import java.awt.image.BufferedImage;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;


public class ServerThread extends Thread{
	Socket s;
    Server server;
    private BufferedReader br;
    private PrintStream ps;



    // to do --> private variables for the server thread


    public ServerThread(Socket s, Server server)
    {
        try
        {
            this.s = s;
            this.server = server;
            // to do --> store them somewhere, you will need them later
            this.br =  new BufferedReader(new InputStreamReader(s.getInputStream()));
            this.ps = new PrintStream(new BufferedOutputStream(s.getOutputStream()));
            this.start();
            // to do --> complete the implementation for the constructor

        }
        catch (IOException ioe)
        {
            System.out.println("ioe in ServerThread constructor: " + ioe.getMessage());
        }
    }

    // to do --> what method are we missing? Implement the missing method
   //  @Override
    public void run()
    {

        while (true)
        {
            try
            {
                String message = br.readLine();
                if (message == null)
                {
                    break;
                }
                else
                {
                    if (message.startsWith("GET"))
                    {
                        String request = message.split(" ")[1].substring(1);
                        System.out.println(request);
                        File f = new File(request);
                        if (f.exists())
                        {
                            System.out.println("Exists");
                            ps.append("HTTP/1.1 200 0K\r\n");
                            String myDestination = request.split("\\.")[1];
                            if (myDestination.equalsIgnoreCase("html"))
                            {
                                ps.append("Content-Type: text/html\r\n\r\n");
                                ps.flush();
                                BufferedReader myReader = new BufferedReader(new FileReader(f));
                                String line = "";
                                while ((line = myReader.readLine()) != null)
                                {
                                    ps.append(line);
                                }
                                ps.flush();
                            }
                            else if (myDestination.equalsIgnoreCase("jpeg") || myDestination.equalsIgnoreCase("jpg"))
                            {
                                ps.append("Content-Type: image/jpeg\r\n\r\n");
                                ps.flush();
                                int bytes = (int) f.length();
                                FileInputStream iFile = new FileInputStream(request);
                                byte[] myFileInBytes = new byte[bytes];
                                iFile.read(myFileInBytes);
                                ps.write(myFileInBytes, 0, bytes);
                                ps.flush();

                            }

                        }
                        else {
                            ps.print("HTTP/1.0 404 Not Found\r\n" +
                                    "Content-type: text/html\r\n\r\n"+
                                    "<html><head></head><body><h1>404 Error</h1>" + " not found</body></html>\n");
                        }
                        ps.close();
                        break;
                    }
                }
            }catch(IOException e) {
                e.printStackTrace();
            }

        }

    }
}

