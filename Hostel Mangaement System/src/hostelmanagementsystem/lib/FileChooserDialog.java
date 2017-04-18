package hostelmanagementsystem.lib;

import javax.swing.Timer;

import java.io.File;

import java.awt.FileDialog;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class FileChooserDialog{
    File selectedFile=null,tmp;
    public String filename="*.*",pathName="nothing";
    Timer timer;
    JTextField disp=new JTextField();
    FileChooserDialog(String flnm,JTextField fl){
        this(flnm);
        disp=fl;
    }
    public FileChooserDialog(String flnm){
        filename=flnm;
        final FileDialog fd= new FileDialog(Globals.mainApp, "Choose a file", FileDialog.LOAD);
        fd.setDirectory("C:\\Users\\Indrajeet\\Desktop");
        fd.setFile(filename);
        fd.setVisible(true);
        timer=new Timer(10,new ActionListener(){
            public void actionPerformed(ActionEvent acev){
                if(fd.getFile()!=null){
                    if(!fd.getFile().equals(filename)){
                        pathName=fd.getDirectory()+fd.getFile();
                        disp.setText(pathName);
                        System.out.println(pathName);
                        tmp=new File(pathName);
                        if(tmp.isFile()){
                            selectedFile=tmp;
                            System.out.println(fd.getDirectory()+fd.getFile());
                        }
                        timer.stop();
                    }
                }
           }
       });
       timer.start();
    }
    public File getSelectedFile(){
        return selectedFile;
    }
    String getChoosedFileExtension(){
        int i=pathName.lastIndexOf(".");
        return(pathName.substring(i+1));
    }
}        
        
