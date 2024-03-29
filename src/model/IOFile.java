/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.StreamSupport;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author minhduy
 */
public class IOFile {
    
 
    
    public void readFile(String tenFile, Dictionary dict) {

            try {
                File f = new File(tenFile);
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                br.readLine();
                
                Word word = new Word();
                word.setEng(br.readLine());
                while (true) {
                    String line = br.readLine();
                    if (line == null) {
                        break;
                    } else if (line.startsWith("@")) {
                        word = new Word();
                        String[] parts = line.split("/");
                        if (parts.length == 2) {
                            word.setEng(parts[0].substring(1).trim());
                            word.setPhienAm(parts[1].trim());
                        } else {
                            word.setEng(line.trim().substring(1));
                            
                        }
                    } else if (line.startsWith("*")) {
                        word.setGioiTu(line.trim());
                    } else if (line.startsWith("-") || (line.startsWith("="))) {
                        String vi = line.trim().substring(1);
                        vi = vi.replace("[", "").replace("]", "").trim();
                        if (vi != null && !vi.isEmpty()) {
                            word.getViet().add(vi);
                        }
                        
                        if (word.getGioiTu() != null && !word.getGioiTu().isEmpty()) {
                            word.getViet().add(word.getGioiTu());
                        }
                        if (word.getPhienAm() != null && !word.getPhienAm().isEmpty()) {
                            word.getViet().add(word.getPhienAm());
                        }
                    }
                    dict.loadData(word.getEng(), word.getViet());
                }
                
                br.close();
       
            } catch (IOException e) {
            }
        
    }
    
    public void ghiFile(String tenFile, Dictionary dict) {
        try {
            String fileName = tenFile;
            File f = new File(fileName);
            FileWriter fw = new FileWriter(f); // Mở tệp trong chế độ ghi, không phải ghi thêm
            BufferedWriter bw = new BufferedWriter(fw);
            for (Map.Entry<String, Word> entry : dict.getDict().entrySet()) {
                bw.write("@" + entry.getKey());
                bw.newLine();
                
                bw.write("- " + entry.getValue().getViet());
                bw.newLine();
                
            }
            
            bw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public IOFile() {
        
    }
    
    public void readFile(String tenFile, DictionaryFavorite dict) {

            try {
                File f = new File(tenFile);
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                Word word = new Word();
                while (true) {
                    String line = br.readLine();
                    if (line == null) {
                        break;
                    } else if (line.startsWith("@")) {
                        word = new Word();
                        String[] parts = line.split("/");
                        if (parts.length == 2) {
                            word.setEng(parts[0].substring(1).trim());
//                        System.out.println("ENG: "+word.getEng());
                            word.setPhienAm(parts[1].trim());
                            System.out.println("PHIEN AM: " + parts[1].trim());
                        } else {
                            word.setEng(line.trim().substring(1));
//                         System.out.println("ENG: "+word.getEng());
                        }
                    } else if (line.startsWith("*")) {
                        word.setGioiTu(line.trim());
//                     System.out.println("gioi tu: "+line.trim());
                    } else if (line.startsWith("-") || (line.startsWith("="))) {
                        String vi = line.trim().substring(1);
                        vi = vi.replace("[", "").replace("]", "").trim();
                        if (vi != null && !vi.isEmpty()) {
                            word.getViet().add(vi);
                        }
                        if (word.getGioiTu() != null && !word.getGioiTu().isEmpty()) {
                            word.getViet().add(word.getGioiTu());
                        }
                        if (word.getPhienAm() != null && !word.getPhienAm().isEmpty()) {
                            word.getViet().add(word.getPhienAm());
                        }
                    }
                    dict.loadData(word.getEng(), word.getViet());
                    
                }
                
                br.close();

            } catch (IOException e) {
            }
        
    }
    
    public void ghiFile(String tenFile, DictionaryFavorite dict) {
        try {
            File f = new File(tenFile);
            FileWriter fw = new FileWriter(f); // Mở tệp trong chế độ ghi, không phải ghi thêm
            BufferedWriter bw = new BufferedWriter(fw);
            
            for (Map.Entry<String, Word> entry : dict.getDict().entrySet()) {
                bw.write("@" + entry.getKey());
                bw.newLine();
                bw.write("- " + entry.getValue().getViet());
                bw.newLine();
                
            }
            
            bw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void readFile(String tenFile, DictionaryVNtoEN dict) {

            try {
                File f = new File(tenFile);
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                WordVN word = new WordVN();
                String l=br.readLine();
                word.setViet(l);
                while (true) {
                       String line = br.readLine();
//                       System.out.println(line);
                    if (line == null) {
                        break;
                    } else if (line.startsWith("@")) {
                        word = new WordVN();

//                        System.out.println("word"+line.substring(1));
                        word.setViet(line.substring(1).trim());
//                        String[] parts = line.split("/");
//                        if (parts.length == 2) {
//                            word.setViet(parts[0].substring(0).trim());
//                            System.out.println("VIET :"+parts[0].trim());
//                            word.setPhienAm(parts[1].trim());
////                            System.out.println("PHIEN AM :" + word.getPhienAm());
//                        } else {
//                            word.setViet(line.trim().substring(1));
////                            System.out.println("VIET :" + word.getViet());
//
//                        }
                    } else if (line.startsWith("*")) {
                        word.setGioiTu(line.trim());
//                        System.out.println("GIOI TU :" + word.getGioiTu());

                    } else if (line.startsWith("-") || (line.startsWith("="))) {
                        String vi = line.trim().substring(1);
                        vi = vi.replace("[", "").replace("]", "").trim();
                        if (vi != null && !vi.isEmpty()) {
                            word.getEng().add(vi);
                        }
                        
                        if (word.getGioiTu() != null && !word.getGioiTu().isEmpty()) {
                            word.getEng().add(word.getGioiTu());
                        }
                        if (word.getPhienAm() != null && !word.getPhienAm().isEmpty()) {
                            word.getEng().add(word.getPhienAm());
                        }
                    }
                    dict.loadData(word.getViet(), word.getEng());
                }
                
                br.close();
            
            } catch (IOException e) {
            }
        
    }
    
    public void ghiFile(String tenFile, DictionaryVNtoEN dict) {
        try {
            String fileName = tenFile;
            File f = new File(fileName);
            FileWriter fw = new FileWriter(f); // Mở tệp trong chế độ ghi, không phải ghi thêm
            BufferedWriter bw = new BufferedWriter(fw);
            
            for (Map.Entry<String, WordVN> entry : dict.getDict().entrySet()) {
                bw.write("@" + entry.getKey());
                bw.newLine();
                
                bw.write("- " + entry.getValue().getEng());
                bw.newLine();
                
            }
            
            bw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void readFile(String tenFile, DictionaryRecent dict) {
   
            try {
                File f = new File(tenFile);
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
//                br.readLine();
                
                Word word = new Word();
                word.setEng(br.readLine().substring(1));
                while (true) {
                    String line = br.readLine();
                    if (line == null) {
                        break;
                    } else if (line.startsWith("@")) {
                        word = new Word();
//                        String[] parts = line.split("/");
//                        if (parts.length == 2) {
//                            word.setEng(parts[0].substring(1).trim());
//                            word.setPhienAm(parts[1].trim());
//                            System.out.println("VIET :"+word.getEng());
//                        } else {
                            word.setEng(line.substring(1).trim());
                            System.out.println("VIET :"+word.getEng());

//                        }
                    }else if(word.getEng().startsWith("@@")){
                                word.setEng(line.substring(2).trim());

                            }
//                            
                    
                    else if (line.startsWith("*")) {
                        word.setGioiTu(line.trim());
//                                                    System.out.println("GIOI TU :"+word.getGioiTu());

                    } else if (line.startsWith("-") || (line.startsWith("="))) {
                        String vi = line.trim().substring(1);
                        vi = vi.replace("[", "").replace("]", "").trim();
                        if (vi != null && !vi.isEmpty()) {
                            word.getViet().add(vi);
                        }
                        
                        if (word.getGioiTu() != null && !word.getGioiTu().isEmpty()) {
                            word.getViet().add(word.getGioiTu());
                        }
                        if (word.getPhienAm() != null && !word.getPhienAm().isEmpty()) {
                            word.getViet().add(word.getPhienAm());
                        }
//                       System.out.println("eng :"+word.getViet());

                    }
                    dict.loadData(word.getEng(), word.getViet());
                }
                
                br.close();
      
            } catch (IOException e) {
            }

    }
    
    public void ghiFile(String tenFile, DictionaryRecent dict) {
        try {
            FileWriter fw = new FileWriter(tenFile); // Mở tệp trong chế độ ghi, không phải ghi thêm
            BufferedWriter bw = new BufferedWriter(fw);
            
            for (Map.Entry<String, Word> entry : dict.getDict().entrySet()) {
                bw.write("@" + entry.getKey());
                bw.newLine();
                bw.write("- " + entry.getValue().getViet());
                bw.newLine();
                
            }
            bw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    public void readFile(File tenFile, DictionaryRecent dict) {
      
            try {
                FileReader fr = new FileReader(tenFile);
                BufferedReader br = new BufferedReader(fr);
                br.readLine();
                
                Word word = new Word();
                word.setEng(br.readLine());
                while (true) {
                    String line = br.readLine();
                    if (line == null) {
                        break;
                    } else if (line.startsWith("@")) {
                        word = new Word();
                        String[] parts = line.split("/");
                        if (parts.length == 2) {
                            word.setEng(parts[0].substring(1).trim());
                            word.setPhienAm(parts[1].trim());
//                            System.out.println("VIET :"+word.getEng());
                        } else {
                            word.setEng(line.trim().substring(1));
//                            System.out.println("VIET :"+word.getEng());

                        }
                    } else if (line.startsWith("*")) {
                        word.setGioiTu(line.trim());
//                                                    System.out.println("GIOI TU :"+word.getGioiTu());

                    } else if (line.startsWith("-") || (line.startsWith("="))) {
                        String vi = line.trim().substring(1);
                        vi = vi.replace("[", "").replace("]", "").trim();
                        if (vi != null && !vi.isEmpty()) {
                            word.getViet().add(vi);
                        }
                        
                        if (word.getGioiTu() != null && !word.getGioiTu().isEmpty()) {
                            word.getViet().add(word.getGioiTu());
                        }
                        if (word.getPhienAm() != null && !word.getPhienAm().isEmpty()) {
                            word.getViet().add(word.getPhienAm());
                        }
//                       System.out.println("eng :"+word.getViet());

                    }
                    dict.loadData(word.getEng(), word.getViet());
                }
                
                br.close();
     
            } catch (IOException e) {
            }
        
    }
    
    public void ghiFile(File tenFile, DictionaryRecent dict) {
        try {
            
            FileWriter fw = new FileWriter(tenFile); // Mở tệp trong chế độ ghi, không phải ghi thêm
            BufferedWriter bw = new BufferedWriter(fw);
            
            for (Map.Entry<String, Word> entry : dict.getDict().entrySet()) {
                bw.write("@" + entry.getKey());
                bw.newLine();
                bw.write("- " + entry.getValue().getViet());
                bw.newLine();
                
            }
            bw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ghiFile(File tenFile, DictionaryFavorite dict) {
        try {
            
            FileWriter fw = new FileWriter(tenFile); // Mở tệp trong chế độ ghi, không phải ghi thêm
            BufferedWriter bw = new BufferedWriter(fw);
            
            for (Map.Entry<String, Word> entry : dict.getDict().entrySet()) {
                bw.write("@" + entry.getKey());
                bw.newLine();
                bw.write("- " + entry.getValue().getViet());
                bw.newLine();
                
            }
            
            bw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {

    }
}
