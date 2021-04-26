package com.example.service;

import com.example.entity.Face;
import com.example.repository.FaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FaceService {

    @Autowired
    private FaceRepository faceRepository;

    public Face save(Face face){
        Face save = faceRepository.save(face);
        return save;
    }
}
