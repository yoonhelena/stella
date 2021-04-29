package com.example.stella;

import com.example.entity.Face;
import com.example.service.FaceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class FaceServiceTest extends StellaApplicationTests{

    @Autowired
    FaceService faceService;

    @Test
    public void createFace(){
        Face face = Face.builder()
                .age(20)
                .gender("female")
                .build();
        this.faceService.save(face);
        List<Face> faceList = this.faceService.findAll();
        assertThat(faceList.get(0).getGender().equals("female"));

    }
}
