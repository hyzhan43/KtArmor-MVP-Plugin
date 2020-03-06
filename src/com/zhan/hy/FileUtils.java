package com.zhan.hy;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

/**
 * author：  HyZhan
 * create：  2019/7/5
 * desc：    TODO
 */
public class FileUtils {

    /**
     * 读取模板文件中的字符内容
     */
    public static String readTemplateFile(String filePath) {

        /*
         * new Object(){}.getClass()
         * 通过匿名类 来获取当前类 class
         */
        try (InputStream inputStream = new Object() {
        }.getClass().getResourceAsStream(filePath);
             ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }

            return new String(outputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }


    /**
     * @param content  类中的内容
     * @param filePath 类文件路径
     * @param fileName 类文件名称
     */
    public static void writeToFile(String content, String filePath, String fileName) {
        try {
            File folder = new File(filePath);
            if (!folder.exists()) {
                boolean result = folder.mkdirs();
            }

            String pathName = filePath + "/" + fileName;
            File file = new File(pathName);

            if (file.exists()) {
                int flag = 1;
                int index = fileName.lastIndexOf(".");
                // 文件名
                String name = fileName.substring(0, index);
                // 文件后缀
                String suffix = fileName.substring(index);

                // 存在的话, 则创建一个fileName_1 文件
                while (file.exists()) {
                    file = new File(filePath + "/" + name + "_" + flag + suffix);
                    flag++;
                }
            } else {
                boolean result = file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从AndroidManifest.xml文件中获取当前app的包名
     */
    public static String getPackageName(String manifestPath) {
        String package_name = "";

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(manifestPath);

            NodeList nodeList = doc.getElementsByTagName("manifest");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                Element element = (Element) node;
                package_name = element.getAttribute("package");
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return package_name;
    }

    public static void writeToManifest(String manifestPath, String newContent) {

        String content = "";
        try (FileReader reader = new FileReader(manifestPath);
             BufferedReader bufferedReader = new BufferedReader(reader)) {
            StringBuilder sb = new StringBuilder();
            // 每一行的内容
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // 找到application节点的末尾
                if (line.contains("</application>")) {
                    // 在application节点最后插入新创建的activity节点
                    sb.append(newContent).append("\n");
                }
                sb.append(line).append("\n");
            }
            content = sb.toString();
            // 删除最后多出的一行
            content = content.substring(0, content.length() - 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (content.isEmpty())
            return;

        try (FileWriter writer = new FileWriter(manifestPath);
             BufferedWriter bw = new BufferedWriter(writer)) {

            bw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
