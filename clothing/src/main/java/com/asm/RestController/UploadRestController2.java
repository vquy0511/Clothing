package com.asm.RestController;

import java.io.File;

import javax.servlet.ServletContext;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@CrossOrigin("*")
@RestController
public class UploadRestController2 {
	
	@Autowired
	ServletContext app;

	@PostMapping("/rest/upload/{folder}")
	public JsonNode upload(@PathParam("file") MultipartFile file,
			@PathVariable("folder") String folder) {
		File dir = new File(app.getRealPath(folder));
		if (!dir.exists()) {
			dir.mkdirs();
		}
		String s = System.currentTimeMillis() + file.getOriginalFilename();
		String name = Integer.toHexString(s.hashCode()) + s.substring(s.lastIndexOf("."));
		try {
			File saveFile = new File(dir, name);
			file.transferTo(saveFile);
			System.out.println(saveFile.getAbsolutePath());
			File savedFile = saveFile;
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode node = mapper.createObjectNode();
			node.put("name", savedFile.getName());
			node.put("size", savedFile.length());
			return node;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
