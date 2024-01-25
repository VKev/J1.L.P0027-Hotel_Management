package Utils;


import java.io.*;
import java.util.ArrayList;

public class FileManager {
    
    private File file;
    private DataOutputStream outputStream;
    private DataInputStream inputStream;
    
    public void CreateFile(String name){
        this.file = new File(name);
        try {
            file.createNewFile();
            outputStream = new DataOutputStream(new FileOutputStream(file, true));
            inputStream = new DataInputStream(new FileInputStream(file));
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
    public void CloseFile(){
        try{
            outputStream.close();
            inputStream.close();
        }catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
    
    public void Writeln(String s){
        try{
            outputStream.writeBytes(s+"\n");
        }catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
    
    public void Write(String s) throws IOException{
        try{
            outputStream.writeBytes(s);
        }catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
    
    
    public ArrayList<String> GetLines(){
        ArrayList<String> lines = new ArrayList<String>();
        try {
            DataInputStream inputStream = new DataInputStream(new FileInputStream(file));
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
        }catch (IOException e) {
            System.out.println("An error occurred.");
        }
        
        return lines;
    }
    
    public void Update(ArrayList<String> lines){
        try {
            file.createNewFile();
            DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(file));
            for(String line: lines){
                outputStream.writeBytes(line+"\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
}
