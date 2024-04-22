package tn.enicarthage.internshipsmanagement.restControllers;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tn.enicarthage.internshipsmanagement.entities.FileDB;
import tn.enicarthage.internshipsmanagement.response.JSONResponse;
import tn.enicarthage.internshipsmanagement.response.ResponseFile;
import tn.enicarthage.internshipsmanagement.response.ResponseMessage;
import tn.enicarthage.internshipsmanagement.services.FileStorageService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



@RestController
@RequestMapping("/api/v1/file")
@CrossOrigin
public class FileController {

  @Autowired
  private FileStorageService storageService;

  @PostMapping("/upload/{id}")
  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable("id") int id) {
    String message = "";
    try {
      storageService.store(file,id);

      message = "Uploaded the file successfully: " + file.getOriginalFilename();
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    } catch (Exception e) {
      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
    }
  }

  @GetMapping("/files")
  public ResponseEntity<List<ResponseFile>> getListFiles() {
    List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
      String fileDownloadUri = ServletUriComponentsBuilder
          .fromCurrentContextPath()
          .path("/files/")
          .path(dbFile.getId())
          .toUriString();

      return new ResponseFile(  
    	  dbFile.getId(),
          dbFile.getName(),
          fileDownloadUri,
          dbFile.getType(),
          dbFile.getData().length, dbFile.getSfe().getSujet(), dbFile.getSfe().getEtudiant().getNom() + " " + dbFile.getSfe().getEtudiant().getPrenom());
    }).collect(Collectors.toList());

    return ResponseEntity.status(HttpStatus.OK).body(files);
  }

  
  @GetMapping("/etudiant/{id}")
  public  ResponseEntity<ResponseFile>  getFileByEtud(@PathVariable("id") Long id) {
           FileDB f  = storageService.getByEtudiant(id);
           String fileDownloadUri = ServletUriComponentsBuilder
              .fromCurrentContextPath()
              .path("/files/")
              .path(f.getId())
              .toUriString();
	      return ResponseEntity.status(HttpStatus.OK).body(new ResponseFile(
              f.getId(),
              f.getName(),
              fileDownloadUri,
              f.getType(),
              f.getData().length, f.getSfe().getSujet(), f.getSfe().getEtudiant().getNom() + " " + f.getSfe().getEtudiant().getPrenom()));
  }

    @GetMapping("/sfe/{id}")
    public ResponseEntity<List<ResponseFile>> getFileBySFE(@PathVariable("id") int id) {
        List<FileDB> files = storageService.getBySfe(id);
        List<ResponseFile> responseFiles = files.stream()
                .map(f -> {
                    String fileDownloadUri = ServletUriComponentsBuilder
                            .fromCurrentContextPath()
                            .path("/files/")
                            .path(f.getId())
                            .toUriString();
                    return new ResponseFile(
                            f.getId(),
                            f.getName(),
                            fileDownloadUri,
                            f.getType(),
                            f.getData().length,
                            f.getSfe().getSujet(),
                            f.getSfe().getEtudiant().getNom() + " " + f.getSfe().getEtudiant().getPrenom());
                })
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(responseFiles);
    }


    @GetMapping("")
    public ResponseEntity<List<ResponseFile>> getFileBySFE() {
        List<FileDB> files = storageService.getAllFilesOrdered();
        List<ResponseFile> responseFiles = files.stream()
                .map(f -> {
                    String fileDownloadUri = ServletUriComponentsBuilder
                            .fromCurrentContextPath()
                            .path("/files/")
                            .path(f.getId())
                            .toUriString();
                    return new ResponseFile(
                            f.getId(),
                            f.getName(),
                            fileDownloadUri,
                            f.getType(),
                            f.getData().length,
                            f.getSfe().getSujet(),
                            f.getSfe().getEtudiant().getNom() + " " + f.getSfe().getEtudiant().getPrenom());
                })
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(responseFiles);
    }

  @GetMapping("/{id}")
  public ResponseEntity<byte[]> getFile(@PathVariable String id) {
    FileDB fileDB = storageService.getFile(id);
    System.out.println(fileDB);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
        .body(fileDB.getData());
  }
  
  
  @GetMapping("/download/{id}")
	public void downloadFile(@PathVariable String id, HttpServletResponse res) throws Exception {
	    FileDB fileDB = storageService.getFile(id);
		res.setHeader("Content-Disposition", "attachment; filename=" + fileDB.getName());
		res.getOutputStream().write(fileDB.getData());
	}
  
  @GetMapping("/getBySfe/{id}")
  public ResponseEntity<?> getBySfe(@PathVariable int id) {
    List<FileDB> fileDB = storageService.getBySfe(id);
	JSONResponse res = new JSONResponse();
	if (fileDB != null)
		res.setMsg("Fichier trouvé");
	else
		res.setMsg("Fichier non trouvé");
	
	 
	return  ResponseEntity.ok(res)  ;

  }
	
}