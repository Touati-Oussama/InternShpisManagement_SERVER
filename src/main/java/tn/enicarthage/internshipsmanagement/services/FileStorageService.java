package tn.enicarthage.internshipsmanagement.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import tn.enicarthage.internshipsmanagement.entities.FileDB;
import tn.enicarthage.internshipsmanagement.repos.FileDBRepository;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class FileStorageService {

  @Autowired
  private  FileDBRepository fileDBRepository;

  @Autowired 
  private SfeService sfeService;
  
  public FileDB store(MultipartFile file, int id) throws IOException {
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    FileDB FileDB = new FileDB();
    FileDB.setName(fileName);
    FileDB.setType(file.getContentType());
    FileDB.setData(file.getBytes());
    FileDB.setSfe(this.sfeService.getSFE(id));
    return fileDBRepository.save(FileDB);
  }

  public FileDB getFile(String id) {
    return fileDBRepository.findById(id).get();
  }
  
  public FileDB getByEtudiant(Long id) {
	    return this.fileDBRepository.findBySfeEtudiantUserId(id).get(0);
  }
  /*public FileDB  get(Long id)
  {
	  return this.fileDBRepository.findBySfeEtudiantUserId(id);
  }*/

  public List<FileDB>  get(Long id)
  {
    return this.fileDBRepository.findBySfeEtudiantUserId(id);
  }
  public Stream<FileDB> getAllFiles() {
    return fileDBRepository.findAll().stream();
  }

  public List<FileDB> getAllFilesOrdered() {
    List<Object[]> results = fileDBRepository.findAllOrderedByDepartmentAndUser();
    return results.stream()
            .map(this::mapToFileDB)
            .collect(Collectors.toList());
  }

  private FileDB mapToFileDB(Object[] result) {
    FileDB fileDB = (FileDB) result[3]; // Assuming FileDB is at index 3
    return fileDB;
  }

  public List<FileDB> getBySfe(int id) {
    return this.fileDBRepository.findBySfeId(id);
  }

  /*public FileDB getBySfe(int id) {
	  return this.fileDBRepository.findBySfeId(id);
  }*/
}
