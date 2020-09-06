package pl.coderslab.springbootdemo.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import pl.coderslab.springbootdemo.entity.Image;
import pl.coderslab.springbootdemo.exception.ImageReadException;
import pl.coderslab.springbootdemo.service.ImageService;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Controller
public class ImageController {

    private ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping(path = "/image/add")
    String showAddImageForm() {
        return "image/add";
    }

    @PostMapping(path = "/image/add")
    String processAddImageForm(@RequestParam("title") String title,
                               @RequestParam("author") String author,
                               @RequestParam("image") MultipartFile[] images
                               ) {

        for (MultipartFile mf : images) {
            Image uploadedImage = uploadImage(author, title, mf);
        }

        return "redirect:image/list";
    }

    @GetMapping(path = "image/list")
    String showAllImages(Model model) {

        List<Image> images = imageService.findAll();
        model.addAttribute("images", images);

        return "image/list";
    }

    /*@GetMapping(path = "image/show")
    void showImage(@RequestParam("id") long id, OutputStream out) {
        Image image = imageService.findById(id);
        byte[] bytes = image.getImage();

        try {

            //ServletOutputStream out = response.getOutputStream(); HttpServletResponse
            out.write(resize(bytes));

        } catch (IOException e) {

        }
    }*/

    @GetMapping(path = "image/show", produces = MediaType.IMAGE_JPEG_VALUE)
    StreamingResponseBody showImage(@RequestParam("id") long id) {
        Image image = imageService.findById(id);
        byte[] bytes = image.getImage();

        return out -> {
                out.write(resize(bytes));
        };
    }

    private byte[] resize(byte[] input) throws IOException {

        BufferedImage inputImage = ImageIO.read(new ByteArrayInputStream(input));

        // creates output image
        BufferedImage outputImage = new BufferedImage(100,
                100, inputImage.getType());

        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, 100, 100, null);
        g2d.dispose();

        // writes to output file
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(outputImage, "jpeg", out);

        return out.toByteArray();
    }

    @ExceptionHandler//({ImageReadException.class})
    String handleException(ImageReadException ex, Model model) {
        model.addAttribute("fileName", ex.getFileName());
        ex.printStackTrace();
        return "image/exception";
    }

    private Image uploadImage(String author, String title, MultipartFile image) {

        String fileName = image.getOriginalFilename();
        byte[] bytes = null;

        try {

            bytes = image.getBytes();

        } catch (IOException ex) {
            throw new ImageReadException(fileName);
        }
        Image imageCandidate = new Image();
        imageCandidate.setAuthor(author);
        imageCandidate.setTitle(title);
        imageCandidate.setImage(bytes);

        Image imageUploaded = imageService.addImage(imageCandidate);

        return imageUploaded;
    }
}
