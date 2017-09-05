package com.cdk.gist.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UploadController {

	private static final String UPLOADED_FOLDER = "/Users/guptak/upload/";

	@GetMapping("/upload")
	public String index() {
		return "upload";
	}

	@PostMapping(value = "/upload")
	public ModelAndView singleFileUpload(@RequestParam("myfile") MultipartFile file, RedirectAttributes redirectAttributes) {
		System.out.println("Inside file upload post method");
		if (file.isEmpty()) {
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			return new ModelAndView("redirect:postupload");
		}
		try {

			// Get the file and save it somewhere
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);

			redirectAttributes.addFlashAttribute("message",
					"You successfully uploaded '" + file.getOriginalFilename() + "'");

		} catch (IOException e) {
			e.printStackTrace();
		}

		return new ModelAndView("redirect:postupload");

	}
}
