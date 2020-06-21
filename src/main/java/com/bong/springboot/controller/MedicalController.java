package com.bong.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.bong.springboot.bean.Board;
import com.bong.springboot.bean.IntensiveCareUnit;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.BufferedReader;
import java.io.IOException;

@RestController
public class MedicalController {

    @Autowired 
    MongoTemplate mongoTemplate;
	  
	@CrossOrigin(origins="http://localhost:8080")
	@RequestMapping("/getMedical")
	public List<IntensiveCareUnit> getMedical() throws Exception
	{
		 StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B551182/hospAsmRstInfoService/getGnhpSprmAsmRstList"); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=kRwvwxVemUXeueaIqbeCxYpkL86gX7eUfBtIhlzy%2BZ5npjNVu6m2f%2FqZsnjl354Hzn4a5lN9L2QmTNKfSJ5Zvw%3D%3D"); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1000", "UTF-8")); /*파라미터설명*/
	        URL url = new URL(urlBuilder.toString());
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-type", "application/json");
	        System.out.println("Response code: " + conn.getResponseCode());
	        BufferedReader rd;
	        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
	            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        } else {
	            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
	        }
	        StringBuilder sb = new StringBuilder();
	        String line;
	        while ((line = rd.readLine()) != null) {
	            sb.append(line);
	        }
	        rd.close();
	        conn.disconnect();
	        System.out.println(sb.toString());
	        
	        DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
	        Document doc = dBuilder.parse(url.toString());

	        // root tag 
	        doc.getDocumentElement().normalize();
	        System.out.println("Root element: " + doc.getDocumentElement().getNodeName()); // Root element: result
	        
	     // 파싱할 tag
	        NodeList nList = doc.getElementsByTagName("item");
	        System.out.println("파싱할 리스트 수 : "+ nList.getLength());  // 파싱할 리스트 수 :  
	        
	        List<Map<String, String>> mList = new ArrayList<Map<String,String>>(); 
	        Map<String, String> mMap = new HashMap<String,String>();
	        for(int temp = 0; temp < nList.getLength(); temp++){		
	        	Node nNode = nList.item(temp);
	        	if(nNode.getNodeType() == Node.ELEMENT_NODE){
	        		mMap.clear();
	        		Element eElement = (Element) nNode;
	        		System.out.println("######################");
	        		//System.out.println(eElement.getTextContent());
	        		mMap.put("addr",getTagValue("addr", eElement));
	        		mMap.put("asmGrd",getTagValue("asmGrd", eElement));
	        		mMap.put("clCd",getTagValue("clCd", eElement));
	        		mMap.put("asmItmCd",getTagValue("asmItmCd", eElement));
	        		mMap.put("sgguCd",getTagValue("sgguCd", eElement));
	        		mMap.put("sgguCdNm",getTagValue("sgguCdNm", eElement));
	        		mMap.put("sidoCd",getTagValue("sidoCd", eElement));
	        		mMap.put("sidoCdNm",getTagValue("sidoCdNm", eElement));
	        		mMap.put("yadmNm",getTagValue("yadmNm", eElement));
	        		mMap.put("ykiho",getTagValue("ykiho", eElement));
	        		mList.add(mMap);
	        		
	        		System.out.println("주소  : " + getTagValue("addr", eElement));
	        		System.out.println("평가등급  : " + getTagValue("asmGrd", eElement));
	        		System.out.println("평가항목코드 : " + getTagValue("asmItmCd", eElement));
	        		System.out.println("종별코드  : " + getTagValue("clCd", eElement));
	        		System.out.println("종별코드명  : " + getTagValue("clCdNm", eElement));
	        		System.out.println("시군구코드  : " + getTagValue("sgguCd", eElement));
	        		System.out.println("시군구코드명  : " + getTagValue("sgguCdNm", eElement));
	        		System.out.println("시도코드  : " + getTagValue("sidoCd", eElement));
	        		System.out.println("시도코드명  : " + getTagValue("sidoCdNm", eElement));
	        		System.out.println("요양기관명  : " + getTagValue("yadmNm", eElement));
	        		System.out.println("암호화된요양기호  : " + getTagValue("ykiho", eElement));
	        	}	// for end
	        }	// if end
// IntensiveCareUnit icu = new IntensiveCareUnit();
	        
//	        mongoTemplate.createCollection("INTENSIVE_CARE_UNIT");
//	        mongoTemplate.insert(mList, "INTENSIVE_CARE_UNIT");
//	        System.out.println(mongoTemplate.findAll(IntensiveCareUnit.class, "INTENSIVE_CARE_UNIT"));
	        List<IntensiveCareUnit> resultList = mongoTemplate.findAll(IntensiveCareUnit.class, "INTENSIVE_CARE_UNIT");
	        return resultList;
	        
	}
	private static String getTagValue(String tag, Element eElement) {
	    NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
	    Node nValue = (Node) nlList.item(0);
	    if(nValue == null) 
	        return null;
	    return nValue.getNodeValue();
	}

}
