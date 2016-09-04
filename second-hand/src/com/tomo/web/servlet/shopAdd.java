package com.tomo.web.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.tomo.common.DaoFactory;
import com.tomo.dao.ShopDao;
import com.tomo.entity.Shop;

/**
 * @author show-mark
 * @date： 日期：2016-5-25 时间：下午1:25:11
 */
public class shopAdd extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		DecimalFormat df  = new DecimalFormat("#.00元");
		Shop shop = new Shop();
		// 处理文件上传
		if (ServletFileUpload.isMultipartContent(request)) { // 检查是否为multipart表单数据
			try {
				ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());

				List<FileItem> items = upload.parseRequest(request); // 解析请求

				int size = items == null ? 0 : items.size();
				for (int i = 0; i < size; i++) {
					FileItem item = (FileItem) items.get(i); // 获取方法消息体中的每一段内容。
					if (item.isFormField()) {// 如果是普通表单项目
						if (item.getFieldName().equals("shopname")) {
							shop.setShopname(new String(item.getString().getBytes("ISO8859-1"), "UTF-8"));
						} else if (item.getFieldName().equals("userName")) {
							shop.setUserName(new String(item.getString().getBytes("ISO8859-1"), "UTF-8"));
						} else if (item.getFieldName().equals("description")) {
							shop.setDescription(new String(item.getString().getBytes("ISO8859-1"), "UTF-8"));
						} else if (item.getFieldName().equals("price")) {
							shop.setPrice((df.format(Double.valueOf(item.getString()))));
						} else if (item.getFieldName().equals("userPhone")) {
							shop.setUserPhone(item.getString());
						} else if (item.getFieldName().equals("category")) {
							shop.setCategory(new String(item.getString().getBytes("ISO8859-1"), "UTF-8"));
						}else if (item.getFieldName().equals("picture")) {
							shop.setPicture(new String(item.getString().getBytes("ISO8859-1"), "UTF-8"));
						}
					} else { // 文件
						String fileName = item.getName();// 获得上传的文件全路径名
						// 截取后缀名"aaa.bbb.png"
						System.out.println(fileName);
//						String str = fileName.substring(fileName
//								.lastIndexOf("/"+1));

						String contentType = item.getContentType();
						long sizeInBytes = item.getSize();

						InputStream uploadedStream = null;
						OutputStream os = null;
						try {
							//这句话是重点,它是如何从客户端获得输入流的呢？
							uploadedStream = item.getInputStream();
							// 使用IO流操作。。。

							// 获取Web程序根目录下指定目录的全路径名
							File basePath = new File(this.getServletContext()
									.getRealPath("/images"));
							File dest = new File(basePath, fileName);
							os = new FileOutputStream(dest);

							IOUtils.copy(uploadedStream, os);
						} catch (Exception e) {
							response.getWriter().print("发布失败");
							e.printStackTrace();
						} finally {
							IOUtils.closeQuietly(uploadedStream);
							IOUtils.closeQuietly(os);
						}
					}
				}
				Date date = new Date();
				shop.setPut_time(date);
				ShopDao dao = DaoFactory.getInstance("shopDao",
						ShopDao.class);
				dao.save(shop);
				response.getWriter().print("发布成功");
			} catch (Exception e) {
				response.getWriter().print("发布失败");
				e.printStackTrace();
			}
		} else {
			response.getWriter().print("发布失败");
		}

	}
}
