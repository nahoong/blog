package blog.ajax;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class Test
 */
@WebServlet("/UploadPic")
public class UploadPic extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//è¿”å›?š„json
		JSONObject jo =  new JSONObject();
		
		 // ?–‡ä»¶çš„ä¿å­˜?›®å½?
        String savePath = this.getServletContext().getRealPath("/upload");
        File saveFileDir = new File(savePath);
        if (!saveFileDir.exists()) {
              saveFileDir.mkdirs();
        }        
        // ä¸´æ—¶?–‡ä»¶ä¿å­˜ç›®å½?
        String tmpPath = this.getServletContext().getRealPath("/upload/tem");
        File tmpFile = new File(tmpPath);
        if (!tmpFile.exists()) {           
            tmpFile.mkdirs();
        }        
      
        String message="";             
        try {                   
            DiskFileItemFactory factory = new DiskFileItemFactory();          
            factory.setSizeThreshold(1024 * 10);
            factory.setRepository(tmpFile);        
            ServletFileUpload upload = new ServletFileUpload(factory);                 
            upload.setFileSizeMax(1024 * 1024 * 3);

            List items = upload.parseRequest(request);          
            if (items.size()!=0) {            	
            	 FileItem item = (FileItem) items.get(0);                      
                String fileName = item.getName();
                 fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
                 if(item.getSize() > 1024 * 1024 * 3){                        
                        message = message + "?–‡ä»¶ï¼š" + fileName + "ï¼Œä¸Šä¼ æ–‡ä»¶å¤§å°è¶…è¿‡é™?ˆ¶å¤§å°ï¼?" + upload.getFileSizeMax();
                        jo.put("success", 0);
                        jo.put("message",message);                        
                 }else{
				String saveFileName = makeFileName(fileName);		
				InputStream is = item.getInputStream();				
				FileOutputStream out = new FileOutputStream(savePath + "\\" + saveFileName);			
				byte buffer[] = new byte[1024];
				int len = 0;
				while ((len = is.read(buffer)) > 0) {
					out.write(buffer, 0, len);
				}				
				
				out.close();				
				is.close();				
				item.delete();
                message = message + "?–‡ä»¶ï¼š" + fileName + "ä¸Šä¼ ?ˆ?ŠŸ";
                
                String url= "/Blog/upload/"+saveFileName;
                jo.put("success", 1);
                jo.put("message",message);
                jo.put("url", url);                
                 }
                 
            }            
        } catch (FileSizeLimitExceededException e) {
            message = message + "ä¸Šä¼ ?–‡ä»¶å¤§å°è¶…è¿‡é™?ˆ¶";
            jo.put("success", 0);
            jo.put("message", message);
            e.printStackTrace();
        } catch (Exception e) {          
            e.printStackTrace();
        }
      
        response.getWriter().print(jo);
		
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	private String makeFileName(String fileName) {
        // ä¸ºé˜²æ­¢æ–‡ä»¶è¦†?›–?š„?°è±¡å‘?”Ÿï¼Œè¦ä¸ºä¸Šä¼ æ–‡ä»¶äº§?”Ÿä¸?ä¸ªå”¯ä¸??š„?–‡ä»¶å
        return UUID.randomUUID().toString().replaceAll("-", "") + "_" + fileName;

    }

}
