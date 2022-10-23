package ru.alexsolution.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AwsService {

    @Value("${aws.bucket}")
    private String bucket;

    private String path = "https://wannago.hb.bizmrg.com/";

    private final S3Client s3;

    public String uploadImage(MultipartFile file) {
            String partName = UUID.randomUUID().toString();
            try {
                PutObjectResponse putObjectResponse = s3.putObject(PutObjectRequest.builder()
                        .bucket(bucket)
                        .key(partName + file.getOriginalFilename())
                        .build(), RequestBody.fromBytes(file.getBytes()));
            } catch (Exception e){
                e.printStackTrace();
            }
            return path + partName + file.getOriginalFilename();
    }
}
