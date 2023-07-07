package etu1806.framework;

import java.io.File;
import java.io.FileInputStream;

import jakarta.servlet.http.Part;
import jakarta.servlet.*;
import jakarta.servlet.http.*;



public class FileUpload {
    String name;
    String path;
    byte[] file;


    public FileUpload(String name, String path, byte[] file) {
        this.name = name;
        this.path = path;
        this.file = file;
    }
    public FileUpload(){

    }

    public static FileUpload uploadFile(HttpServletRequest request, HttpServlet servlet) throws Exception{
        FileUpload upload = new FileUpload();
        try {
            String upload_directory = "upload";
            String uploadPath = servlet.getServletContext().getRealPath("") + File.separator + upload_directory;
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }

            for(Part part : request.getParts()){
                String filename = getFileName(part);
                part.write(uploadPath + File.separator + filename);
                upload = getFileData(uploadPath + File.separator + filename, filename); 

            }
        } catch (Exception e) {
            throw new Exception(e);
        }
        return upload;
    }

    public static FileUpload getFileData(String pathFile , String filename) throws Exception{
        File file = new File(pathFile);
        FileUpload upload = new FileUpload();
        upload.setName(filename);
        FileInputStream fileinput = null;
        try {
            fileinput = new FileInputStream(file);
            upload.file = new byte[(int) file.length()];
            fileinput.read(upload.file);
        } catch (Exception e) {
            throw new Exception(e);
        } finally{
            if(fileinput!=null){
                fileinput.close();
            }
        }
        return upload;
    }

    public static String getFileName(Part part){
        for(String content : part.getHeader("content-disposition").split(";")){
            if(content.trim().startsWith("filename")){
                return content.substring(content.indexOf("=") + 2, content.length() -1);
            }
        }
        return "x32.png";
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPath() {
        return path;
    }


    public void setPath(String path) {
        this.path = path;
    }


    public byte[] getFile() {
        return file;
    }


    public void setFile(byte[] file) {
        this.file = file;
    }
}
