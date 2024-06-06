package com.huysor.projectschool.mapping;

import com.huysor.projectschool.dto.files.FileAttachmentResponse;
import com.huysor.projectschool.entity.FileAttachments;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;



@Mapper
public interface FileMapper {
    FileMapper INSTANCE= Mappers.getMapper(FileMapper.class);

    FileAttachmentResponse toAttachmentResponse(FileAttachments fileAttachments);


}
