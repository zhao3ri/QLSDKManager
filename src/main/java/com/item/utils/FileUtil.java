/*
 * Copyright (c) 2010-2011 NutShell.
 * [Id:FileUtil.java  11-6-8 下午3:46 poplar.mumu ]
 */
package com.item.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


import core.module.utils.Struts2Utils;


/**
 * .
 * <br/>
 *
 * @author liuxh
 * @version 1.0 11-6-8 下午3:46
 * @since JDK 1.0
 */
public class FileUtil {
	
	public static final String _imgExt = ".gif|.jpeg|.png|.bmp|.jpg";

	public static final String _swfExt = ".swf";

	public static final String _DocExt = ".doc";

	public static final String _TxtExt = ".txt";

	public static final String _webPageExt = ".html|.htm|.jsp|.css|.asp|.php";

	public static final String _sourceCodeExt = ".java;.c";

	public enum FileTypes {
		anyType, Img, Swf, Doc, Txt, WebPage, SourceCode
	}

	public static String checkFileTypes(String ext, FileTypes types) {
		String result = "";
		switch (types) {
		case anyType:
			result = "";
			break;
		case Img: {
			if (_imgExt.indexOf(ext.toLowerCase()) < 0)
				result = _imgExt;
			break;
		}
		case Swf: {
			if (_swfExt.indexOf(ext.toLowerCase()) < 0)
				result = _swfExt;
			break;
		}
		case Doc: {
			if (_DocExt.indexOf(ext.toLowerCase()) < 0)
				result = _DocExt;
			break;
		}
		case Txt: {
			if (_TxtExt.indexOf(ext.toLowerCase()) < 0)
				result = _TxtExt;
			break;
		}
		case WebPage: {
			if (_webPageExt.indexOf(ext.toLowerCase()) < 0)
				result = _webPageExt;
			break;
		}
		case SourceCode: {
			if (_sourceCodeExt.indexOf(ext.toLowerCase()) < 0)
				result = _sourceCodeExt;
			break;
		}
		}
		return result;
	}

	public static String getFileExt(String fileName) {
		int indexPoint = fileName.lastIndexOf(".");
		if (indexPoint > 0) {
			return fileName.substring(indexPoint).toLowerCase();
		} else
			return ".eat";

	}

	public static String getFileName(String fileName) {
		File file = new File(fileName);
		return file.getName();
	}

	public static boolean mkDir(String path) {
		boolean result = false;
		File file = null;
		try {
			file = new File(path);
			if (!file.exists())
				result = file.mkdirs();
		} finally {
			// file.
		}
		return result;
	}

	public static String getTempFileName(String prex, String ext) {
		long systemTime = System.currentTimeMillis();
		return prex + String.valueOf(systemTime) + ext;

	}

	public static long writeStreamToFile(InputStream in, String fileName) {
		OutputStream out = null;
		long size = 0;
		try {
			out = new BufferedOutputStream(new FileOutputStream(fileName));
			byte[] buffer = new byte[2048];
			int count;
			while ((count = in.read(buffer)) > 0) {
				size += count;
				out.write(buffer, 0, count);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return size;
	}
	
	
	
	/**
	 * 得到文件的扩展名
	 * @param fileName
	 * @return
	 */
	public static final String getExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".")+1);
	}
	/**
	 * 查看是否是能上传的类形
	 * @param fileType
	 * @param ext
	 * @return
	 */
	public static final boolean extIsAllowed(String fileType, String ext) {
	 		
	 	ext=ext.toLowerCase();
	 		
	 	String[] strArr=fileType.split("\\|");
		 	 
	 	List<String> allowList=new ArrayList<String>();
	 	if(fileType.length()>0) {
	 		for(int i=0;i<strArr.length;++i) {
	 			allowList.add(strArr[i].toLowerCase());
	 		}
	 	}

	 	if(allowList.contains(ext))
	 		return false;
	 	else
	 		return true;
	 }
	
    /**
     * 创建类文件。
     *
     * @param content 文件内容
     * @param path    文件存放地址，不是文件夹，是这次的文件
     * @return 文件产生成功返回true，否则返回false
     */
    public static boolean createClassFile(String content, String path) {
        try {
            FileOutputStream fileoutputstream = new FileOutputStream(path);// 建立文件输出流
            byte tag_bytes[] = content.getBytes("UTF-8");
            fileoutputstream.write(tag_bytes);
            fileoutputstream.close();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("地址无法找到:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("创建文件出错:" + e.getMessage());
        }
        return false;
    }

    /**
     * 如果java文件夹不存在，则创建.
     *
     * @param folderPath  文件夹地址
     * @param packageName 包名
     * @return 路径
     */
    public static String createJavaFolder(String folderPath, String packageName) {
        folderPath = generatPackageFileFloder(folderPath + "/src/", packageName);
        File file = new File(folderPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return folderPath;
    }

    /**
     * 如果xml文件夹不存在，则创建.
     *
     * @param folderPath  文件夹地址
     * @param packageName 包名
     * @return 路径
     */
    public static String createResFolder(String folderPath, String packageName) {
        folderPath = generatPackageFileFloder(folderPath + "/resources/", packageName);
        File file = new File(folderPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return folderPath;
    }
    
    /**
     * 如果jsp文件夹不存在，则创建.
     *
     * @param folderPath  文件夹地址
     * @param packageName 包名
     * @return 路径
     */
    public static String createJspFolder(String folderPath, String packageName) {
    	folderPath = generatPackageFileFloder(folderPath + "/WebRoot/WEB-INF/content/", packageName);
    	File file = new File(folderPath);
    	if (!file.exists()) {
    		file.mkdirs();
    	}
    	return folderPath;
    }

    /**
     * 根据包名，创建包文件夹.
     *
     * @param floderPath  存放地址
     * @param packageName 包名
     * @return 包路径
     */
    public static String generatPackageFileFloder(String floderPath, String packageName) {
        String[] pgs = packageName.split("[.]");
        for (String pg : pgs) {
            floderPath += ("/" + pg);
        }
        return floderPath;
    }


    /**
     * 获取资源文件的绝对路径。
     *
     * @param resource 资源文件名称
     * @return 资源文件的文件系统路径
     */
    public static String getClassPath(String resource) {
        String result = Struts2Utils.getServletContext().getRealPath("\\")+resource;
        int location = result.indexOf("!/");
        return location != -1 ? result.substring(0, location) : result;
    }

    /**
     * 读取某个文件的内容信息。
     * @param path 文件地址
     * @return 文件内容
     */
    public static String readFileContent(String path) {
        FileInputStream in = null;
        try {
            in = new FileInputStream(path);
            byte[] bit = new byte[in.available()];
            in.read(bit);
            in.close();
            return (new String(bit));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    
    /**
     * 获取某个文件
     * @param path
     * @return
     */
    public static File getFile(String path){
    	return new File(path);
    }

    public static void rewrite(File file, String data) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(file));
            bw.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {        
            if(bw != null) {
               try { 
                   bw.close();
               } catch(IOException e) {
                   e.printStackTrace();
               }
            }            
        }
    }
    
    public static List<String> readList(File file) {
        BufferedReader br = null;
        List<String> data = new ArrayList<String>();
        try {
            br = new BufferedReader(new FileReader(file));
            for(String str = null; (str = br.readLine()) != null; ) {
                data.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(br != null) {
               try { 
                   br.close();
               } catch(IOException e) {
                   e.printStackTrace();
               }
            }
        }
        return data;
    }
}
