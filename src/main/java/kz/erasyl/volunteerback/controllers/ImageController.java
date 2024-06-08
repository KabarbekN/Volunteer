//package kz.erasyl.volunteerback.controllers;
//
//import kz.erasyl.volunteerback.models.Image;
//import kz.erasyl.volunteerback.repos.ImageRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//import java.util.Optional;
//
//@RestController("api/v1/image")
//@RequiredArgsConstructor
//
//public class ImageController {
//
//
//    private final ImageRepository imageRepository;
//
//    @PostMapping("/upload/image")
//    public ResponseEntity<ImageUploadResponse> uplaodImage(@RequestParam("image") MultipartFile file)
//            throws IOException {
//
//        imageRepository.save(Image.builder()
//                .name(file.getOriginalFilename())
//                .type(file.getContentType())
//                .image(ImageUtility.compressImage(file.getBytes())).build());
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(new ImageUploadResponse("Image uploaded successfully: " +
//                        file.getOriginalFilename()));
//    }
//
//    @GetMapping(path = {"/get/image/info/{name}"})
//    public Image getImageDetails(@PathVariable("name") String name) throws IOException {
//
//        final Optional<Image> dbImage = imageRepository.findByName(name);
//
//        return Image.builder()
//                .imageName(dbImage.get().getImageName())
//                .type(dbImage.get().getType())
//                .image(ImageUtility.decompressImage(dbImage.get().getImage())).build();
//    }
//
//    @GetMapping(path = {"/get/image/{name}"})
//    public ResponseEntity<byte[]> getImage(@PathVariable("name") String name) throws IOException {
//
//        final Optional<Image> dbImage = imageRepository.findByName(name);
//
//        return ResponseEntity
//                .ok()
//                .contentType(MediaType.valueOf(dbImage.get().getType()))
//                .body(ImageUtility.decompressImage(dbImage.get().getImage()));
//    }
