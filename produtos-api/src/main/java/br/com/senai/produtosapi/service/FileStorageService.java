package br.com.senai.produtosapi.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {

    private final Path pasta = Paths.get("uploads").toAbsolutePath().normalize();

    public String salvar(MultipartFile arquivo) throws IOException {
        if (arquivo == null || arquivo.isEmpty()) {
            throw new IllegalArgumentException("Arquivo de imagem obrigatório.");
        }

        Files.createDirectories(pasta);

        String nomeArquivo = UUID.randomUUID() + "_" + removerCaracteresInvalidos(arquivo.getOriginalFilename());
        Path destino = pasta.resolve(nomeArquivo);

        try (InputStream inputStream = arquivo.getInputStream()) {
            Files.copy(inputStream, destino, StandardCopyOption.REPLACE_EXISTING);
        }

        return nomeArquivo;
    }

    private String removerCaracteresInvalidos(String nomeArquivo) {
        if (nomeArquivo == null || nomeArquivo.isBlank()) {
            return "imagem";
        }

        return nomeArquivo.replaceAll("[\\\\/:*?\"<>|]", "_");
    }
}