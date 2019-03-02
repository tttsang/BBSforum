package com.jking.website.utils;

import com.jking.website.enums.UploadEnums;
import com.jking.website.exceptions.MyException;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class FileUtil {
    public static boolean isNUll (MultipartFile file)
    {
        if (file!=null&&!file.isEmpty())
        {
            return false;
        }
        return true;
    }
   public  static String getExtension(MultipartFile file)

   {    String extension;
       int i=file.getOriginalFilename().lastIndexOf(".");

     if (i>0)
     {
          extension=file.getOriginalFilename().substring(i+1);

     }else
     {
         throw new MyException(UploadEnums.NOT_EXTENSION);
     }
     return extension;
   }
   public static  void isImage(MultipartFile file)
   {    String extension=getExtension(file);
       if (!extension.equalsIgnoreCase("JPEG")&&
           !extension.equalsIgnoreCase("JPG")&&
           !extension.equalsIgnoreCase("PNG")&&
           !extension.equalsIgnoreCase("GIF")&&
           !extension.equalsIgnoreCase("ICO")&&
           !extension.equalsIgnoreCase("BMP"))
       {
           throw new MyException(UploadEnums.ONLY_PICTURE);
       }
   }

   public  static void saveFile(MultipartFile file,String filepath)
   {   try {
//       File path = new File(ResourceUtils.getURL("classpath:").getPath());
//       if(!path.exists()) path = new File("");
       FileOutputStream fileOutputStream=new FileOutputStream(new File(filepath));
      // System.out.println(path.getAbsolutePath()+filepath);
       BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(fileOutputStream);
       bufferedOutputStream.write(file.getBytes());
       bufferedOutputStream.flush();
       bufferedOutputStream.close();

   }
      catch (IOException e)
      {
          e.printStackTrace();
      }
   }

}
