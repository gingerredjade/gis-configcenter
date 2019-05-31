package com.gis.client;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class BuildConfigFile {

	private static Map<String, String> cfgmap = new HashMap<String, String>();

	public static void main(String[] args) {
		getConfigInfo();
		changeFile();
	}
	
	private static void getConfigInfo() {
		String tempurl = "http://192.168.56.211:7070/config/getfileinfo?key=mswss-146-dev.properties";
		tempurl = System.getProperty("url");
		System.out.println(tempurl);
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(tempurl);
		try {
			HttpResponse response = client.execute(httpGet);
			if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
				HttpEntity entity = response.getEntity();
				if (entity!=null) {
					String result = EntityUtils.toString(entity);
					JSONArray jsonarray = JSONArray.fromObject(result);
					for (int j = 0; j < jsonarray.size(); j++) {
						JSONObject jsonObject = jsonarray.getJSONObject(j);
						String cfgkey = jsonObject.get("key").toString();
						String cfgvalue = jsonObject.get("value").toString();
						cfgmap.put(cfgkey, cfgvalue);
					}
				}
			}else {
				System.out.println("httpStatus:"+response.getStatusLine().getStatusCode());
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
    private static void changeFile() {
        Map<String, List<String[]>> configitemFileMap = ConfigItemFileImpl.CONFIGITEM_FILE_MAP;
        for(String key : configitemFileMap.keySet()){
            List<String[]> list  = configitemFileMap.get(key);
            try {
                Document doc = new SAXReader().read(new File(key));
                Element elm = doc.getRootElement();
                for (int i=0;i<list.size();i++){
                    String[] info = list.get(i);
                    Element node = (Element) elm.selectSingleNode(info[0]);
                    if (node == null && cfgmap.get(info[2])!=null) {
                    	System.out.println("配置文件未找到路径或未找到配置信息:"+info[0]);
					}else {
	                    if (info[1].equals(ConfigItemFileImpl.Attribute)){
	                       node.addAttribute("value", cfgmap.get(info[2]));
	                    }
	                    if (info[1].equals(ConfigItemFileImpl.Node)){
	                       node.setText(cfgmap.get(info[2]));
	                    }
	                    System.out.println("更新成功:"+info[2]+"|||"+cfgmap.get(info[2]));
					}
                }
                FileOutputStream outputStream = new FileOutputStream(key);
                OutputFormat format = org.dom4j.io.OutputFormat.createPrettyPrint();
                format.setEncoding("UTF-8");
                XMLWriter writer = new XMLWriter(outputStream,format);
                writer.write(doc);
                writer.close();
            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	} 
	

}
