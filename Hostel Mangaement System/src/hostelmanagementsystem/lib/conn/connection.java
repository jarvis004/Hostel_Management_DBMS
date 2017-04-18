package hostelmanagementsystem.lib.conn;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.entity.mime.content.InputStreamBody;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;

import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;

import javax.imageio.ImageIO;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class connection {
    private String url=null;
    private MultipartEntity multipart=null;
    private int status;
    public connection(String url){
        this.url=url;
        multipart=new MultipartEntity();
    }
    
    public void addFormFields(String name,String val){
        try{
            multipart.addPart(name,new StringBody(val));
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
        }
    }
    public void addImageFormField(BufferedImage image,String ext,String formFieldnamem,String outputFilename){
        ByteArrayOutputStream out=new ByteArrayOutputStream();
        try{
           ImageIO.write((RenderedImage)image,ext,out);
           InputStream in=new ByteArrayInputStream(out.toByteArray());
           ContentBody cb=new InputStreamBody(in,outputFilename);
           multipart.addPart(formFieldnamem,cb);
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }
    
    public String execute(){
        HttpPost post=new HttpPost(url);
        post.setEntity(multipart);
        post.setHeader("HoSTelMaNAGeMentSyStmAPP","HoSTelMaNAGeMentSyStmAPPCustmHdr");
        HttpClient client=new DefaultHttpClient();
        try{
           HttpResponse response=client.execute(post);
           status=response.getStatusLine().getStatusCode();
           if(status==200){
              HttpEntity entity=response.getEntity();
              BufferedReader reader=new BufferedReader(new InputStreamReader(entity.getContent()));
              String line=null;
              String rs="";
              while((line=reader.readLine())!=null)
                 rs+=line;
              reader.close();
              return rs;
           }
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public int getStatus(){
       return status; 
    }
}
