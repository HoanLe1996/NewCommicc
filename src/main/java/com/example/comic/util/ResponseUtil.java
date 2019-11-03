package com.example.comic.util;

import java.util.List;

import com.example.comic.constant.StatusCode;
import com.example.comic.model.TheLoai;
import com.example.comic.model.Truyen;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ResponseUtil {

	private static ObjectMapper mapper = new ObjectMapper();


	
	public static String success(JsonNode body) {
		ObjectNode node = mapper.createObjectNode();
		node.put(StatusCode.class.getSimpleName(), StatusCode.SUCCESS.getValue());
		node.put("Message", StatusCode.SUCCESS.name());
		node.set("Response", body);
		return node.toString();
	}
	

	public static String NotFound() {
		ObjectNode node = mapper.createObjectNode();
		node.put(StatusCode.class.getSimpleName(), StatusCode.NOT_FOUND.getValue());
		node.put("Message", StatusCode.NOT_FOUND.name());
		node.put("Response", "");
		return node.toString();
	}
	public static String SeverError() {
		ObjectNode node = mapper.createObjectNode();
		node.put(StatusCode.class.getSimpleName(), StatusCode.SERVER_ERROR.getValue());
		node.put("Message", StatusCode.SERVER_ERROR.name());
		node.put("Response", "");
		return node.toString();
	}
	
	 public static ArrayNode returnListTruyen(List<Truyen> truyen) {
	        ArrayNode node = mapper.createArrayNode();
	        for (Truyen note : truyen) {
	            node.add((returnTruyen(note)));
	        }
	        return node;
	    }

	
	 public static ObjectNode returnTruyen(Truyen truyen) {
	        ObjectNode node = mapper.createObjectNode();
	        node.put("id", truyen.getId());
	        node.put("name", truyen.getTenTruyen());
	        node.put("description", truyen.getMoTa());
	        return node;
	    }
	 
	 public static ObjectNode returnTheLoai(TheLoai tl) {
	        ObjectNode node = mapper.createObjectNode();
	        node.put("id", tl.getId());
	        node.put("name", tl.getTen_theloai());
	        return node;
	    }
	 
	 public static ArrayNode returnListTL(List<TheLoai> tl) {
	        ArrayNode node = mapper.createArrayNode();
	        for (TheLoai note : tl) {
	            node.add((returnTheLoai(note)));
	        }
	        return node;
	    }
}
