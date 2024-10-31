package com.huysor.projectschool.services.implement;

import com.huysor.projectschool.entity.FileAttachments;
import com.huysor.projectschool.repo.FileAttachmentRepo;
import com.huysor.projectschool.services.FileAttachmentServices;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileAttachmentImpl implements FileAttachmentServices {
    private static final Logger log = LoggerFactory.getLogger(FileAttachmentImpl.class);
    private  final FileAttachmentRepo fileAttachmentRepo;

    @Override
    public FileAttachments toUpload(MultipartFile file) throws Exception  {
        String directoryName = System.getProperty("user.dir")+"/upload/file/";
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
      try{

          File dir= new File(directoryName);
          if(!dir.exists()){
              dir.mkdirs();
          }
          String fileDir = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
          Path uploadPath= Paths.get(directoryName, fileDir);
          Files.write(uploadPath,file.getBytes());
          if(fileName.contains("..")){
              throw  new IllegalArgumentException("File name contain invalid path sequence"+fileName);
          }
          FileAttachments fileAttachments=new FileAttachments();
          fileAttachments.setFileName(fileDir);
          fileAttachments.setFileSize(file.getSize());
          fileAttachments.setFileType(file.getContentType());
          fileAttachments.setFilePath(uploadPath.toString());
          return  fileAttachmentRepo.save(fileAttachments);
      }
      catch (IOException ioException){
          log.error(ioException.getMessage());
          throw new Exception("Could not save file"+fileName);
      }
    }


    @Override
    public String toGetFileByFileName(String fileName) {
        return fileAttachmentRepo.findByFileName(fileName).getFileName();
    }
}
