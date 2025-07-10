package br.com.mubook.mubook.restcontroller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("/api/upload/")
public class UploadController {

    @Value("${app.upload.dir}")
    private String uploadDir;

    @PreAuthorize("hasAnyRole('ADMINISTRADOR', 'ASSOCIADO')")
    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            if(file.isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("É preciso enviar alguma imagem!");
            }

            if (file.getSize() > 5 * 1024 * 1024) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Arquivo muito grande. O tamanho máximo permitido é 2MB.");
            }

            File uploadDirectory = new File(uploadDir);
            if (!uploadDirectory.isAbsolute()) {
                uploadDirectory = new File(System.getProperty("user.dir"), uploadDir);
            }

            if (!uploadDirectory.exists()) {
                boolean created = uploadDirectory.mkdirs();
                if (!created) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Não foi possível criar o diretório de upload.");
                }
            }

            String fileName = file.getOriginalFilename();
            if (fileName == null || fileName.isBlank()) {
                return ResponseEntity.badRequest().body("Nome do arquivo inválido.");
            }

            Path filePath = new File(uploadDirectory, fileName).toPath();
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            String publicPath = "http://localhost:8080/uploads/" + fileName;

            return ResponseEntity.ok(publicPath);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao fazer upload: " + e.getMessage());
        }
    }

}
