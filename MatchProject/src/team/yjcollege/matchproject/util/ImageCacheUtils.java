package team.yjcollege.matchproject.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;

public class ImageCacheUtils {   //图片缓存类
	/*
	 * 从缓存读取图片
	 * imageName--读取图片的名称
	 * Context--上下文
	 * return  位图
	 */
	public static Bitmap getBitmapFromCache(String imageName,Context context){
		File file=getFileFromFullPath(imageName, context);   //获取到缓存中的图片文件
		try {
			InputStream is=new FileInputStream(file);   //把file转换成输入流
			Bitmap bmp=BitmapFactory.decodeStream(is);  //把输入流转换成图片
			is.close();
			return bmp;
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			return null;
		}   
	}
	
	/**
	 * 将网络上的图片保存到缓存
	 * @param imageName   图片名称
	 * @param imageStream   图片的输入流
	 * @param context 
	 */
	public static void saveImageToCache(String imageName,InputStream imageStream,Context context){
		File file=getFileFromFullPath(imageName, context);
		
		try {
			byte[] buffer=inputStreamToByte(imageStream);   //将输入的图片流转换成字节数组，存放于buffer
			imageStream.close();
			FileOutputStream fos=new FileOutputStream(file);   //创建文件输出流
			fos.write(buffer);     //将buffer中的内容输出(写入到存储卡)
			fos.close();
	    } catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 将输入流转换成字节数组
	 * @param inStream   输入流
	 * @return  字节数组
	 */
	private static byte[] inputStreamToByte(InputStream inStream){
		try {
			byte[] buffer=new byte[inStream.available()];   //创建一个缓冲区，大小为输入流的有效长度
			ByteArrayOutputStream outStream =new ByteArrayOutputStream(inStream.available()); //输出流数组
			int read;
			while(true){
				read=inStream.read(buffer);   //将输入流读入到缓冲区
				if(read==-1)    //read==-1，说明输入流读取完毕
					break;
				
				outStream.write(buffer);    //将缓冲区写入到输出流
			}
			return outStream.toByteArray();
			
		} catch (IOException e) {
			return null;
		}
	}
	/**
	 * 从完整的路径中返回一个文件对象
	 * @param imageName  图片名称
	 * @param context
	 * @return 文件对象
	 */
	public static File getFileFromFullPath(String imageName,Context context){
		File file=context.getCacheDir();     //应用程序下的缓存目录
		String fullPath=file.getPath()+"/"+imageName;    //缓存图像的完整路径
		File fullFile=new File(fullPath);
		return fullFile;
	}
	
	/**
	 * 判断图片是否存在于本地缓存中。
	 * @param imageName  图片名称
	 * @param context
	 * @return  true--缓存中有该图片,false--缓存中无该图片
	 */
	public static boolean isImageExistInLocalCache(String imageName,Context context){
		File file=getFileFromFullPath(imageName, context);
		return file.exists();
	}
	
	public static boolean saveBitmapTofile(Bitmap bmp, String filename ,Context context) {
        if (bmp == null || filename == null)
            return false;
        CompressFormat format = Bitmap.CompressFormat.JPEG;
        int quality = 100;
        OutputStream stream = null;
        
        File file=context.getCacheDir();     //应用程序下的缓存目录
		String fullPath=file.getPath()+"/"+filename;    //缓存图像的完整路径
		
        try {
            stream = new FileOutputStream(fullPath);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return bmp.compress(format, quality, stream);
    }

}
