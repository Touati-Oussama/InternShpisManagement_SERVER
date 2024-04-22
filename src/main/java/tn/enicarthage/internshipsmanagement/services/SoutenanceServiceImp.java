package tn.enicarthage.internshipsmanagement.services;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tn.enicarthage.internshipsmanagement.entities.FileDB;
import tn.enicarthage.internshipsmanagement.entities.Soutenance;
import tn.enicarthage.internshipsmanagement.models.SoutenanceModel;
import tn.enicarthage.internshipsmanagement.repos.SoutenanceRepository;
import tn.enicarthage.internshipsmanagement.response.File;
import tn.enicarthage.internshipsmanagement.response.SoutenanceDTO;
import tn.enicarthage.internshipsmanagement.response.SoutenanceDTO1;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SoutenanceServiceImp implements SoutenanceService{
	

	private final SoutenanceRepository soutenanceRepository;

	private final SfeService sfeService;

	private final FileStorageService fileStorageService;
	
	@Override
	public Soutenance saveSoutenance(Soutenance e) {
		return this.soutenanceRepository.save(e);
	}

	@Override
	public Soutenance updateSoutenance(Soutenance e) {
		return this.soutenanceRepository.save(e);
	}

	@Override
	public void deleteSoutenance(Soutenance e) {
		this.soutenanceRepository.delete(e);
	}

	@Override
	public void deleteSoutenanceById(int id) {
		this.soutenanceRepository.deleteById(id);
	}

	@Override
	public Soutenance getSoutenance(int id) {
		return this.soutenanceRepository.findById(id).get();
	}

	@Override
	public List<SoutenanceDTO> getAll() {
		List<Soutenance> tmp = this.soutenanceRepository.findAll();
		ArrayList<SoutenanceDTO> list = new ArrayList();
		for (int i = 0; i < tmp.size();i++) {
			SoutenanceDTO s = new SoutenanceDTO();
			s.setId(tmp.get(i).getId());
			s.setSfe(tmp.get(i).getSfe().getSujet());
			s.setEncadreur(tmp.get(i).getSfe().getEncadreur().getNom() + " " +tmp.get(i).getSfe().getEncadreur().getPrenom() );
			s.setPresident(tmp.get(i).getPresident().getNom() + " " +tmp.get(i).getPresident().getPrenom());
			s.setRapporteur(tmp.get(i).getRapporteur().getNom() + " " +tmp.get(i).getRapporteur().getPrenom());
			s.setDate(tmp.get(i).getDate());
			s.setSalle(tmp.get(i).getSalle().getNom());
			list.add(s);
		}
		return list;
	}
	
	@Override
	public List<SoutenanceDTO> test( LocalDateTime date,String salle){
		List<Soutenance> tmp = this.soutenanceRepository.findByDateSalle(date,salle);
		ArrayList<SoutenanceDTO> list = new ArrayList();
		for (int i = 0; i < tmp.size();i++) {
			SoutenanceDTO s = new SoutenanceDTO();
			s.setId(tmp.get(i).getId());
			s.setSfe(tmp.get(i).getSfe().getSujet());
			s.setEncadreur(tmp.get(i).getSfe().getEncadreur().getNom() + " " +tmp.get(i).getSfe().getEncadreur().getPrenom() );
			s.setPresident(tmp.get(i).getPresident().getNom() + " " +tmp.get(i).getPresident().getPrenom());
			s.setRapporteur(tmp.get(i).getRapporteur().getNom() + " " +tmp.get(i).getRapporteur().getPrenom());
			s.setDate(tmp.get(i).getDate());
			s.setSalle(tmp.get(i).getSalle().getNom());
			list.add(s);
		}
		return list;
	}
	
	@Override
	 public List<SoutenanceDTO> getBySalleDate(LocalDateTime date,int id){
		List<Soutenance> tmp = this.soutenanceRepository.getByDateSalle(date,id);
		ArrayList<SoutenanceDTO> list = new ArrayList();
		for (int i = 0; i < tmp.size();i++) {
			SoutenanceDTO s = new SoutenanceDTO();
			s.setId(tmp.get(i).getId());
			s.setSfe(tmp.get(i).getSfe().getSujet());
			s.setEncadreur(tmp.get(i).getSfe().getEncadreur().getNom() + " " +tmp.get(i).getSfe().getEncadreur().getPrenom() );
			s.setPresident(tmp.get(i).getPresident().getNom() + " " +tmp.get(i).getPresident().getPrenom());
			s.setRapporteur(tmp.get(i).getRapporteur().getNom() + " " +tmp.get(i).getRapporteur().getPrenom());
			s.setDate(tmp.get(i).getDate());
			s.setSalle(tmp.get(i).getSalle().getNom());
			list.add(s);
		}
		return list;
	}
	
	@Override
	 public List<SoutenanceDTO> getBySalle(int id){
		List<Soutenance> tmp = this.soutenanceRepository.getBySalle(id);
		ArrayList<SoutenanceDTO> list = new ArrayList();
		for (int i = 0; i < tmp.size();i++) {
			SoutenanceDTO s = new SoutenanceDTO();
			s.setId(tmp.get(i).getId());
			s.setSfe(tmp.get(i).getSfe().getSujet());
			s.setEncadreur(tmp.get(i).getSfe().getEncadreur().getNom() + " " +tmp.get(i).getSfe().getEncadreur().getPrenom() );
			s.setPresident(tmp.get(i).getPresident().getNom() + " " +tmp.get(i).getPresident().getPrenom());
			s.setRapporteur(tmp.get(i).getRapporteur().getNom() + " " +tmp.get(i).getRapporteur().getPrenom());
			s.setDate(tmp.get(i).getDate());
			s.setSalle(tmp.get(i).getSalle().getNom());
			list.add(s);
		}
		return list;
	}

	@Override
	public String verifierSoutenance(SoutenanceModel sou) {
		String msg = "";
		if (getBySfeId(sou.getSfe()) != null)
			msg = "Ce projet est déja planifié !\n";
		if (getBySalleDate(sou.getDate(),sou.getSalle()).size() != 0)
			msg += "Le choix de la salle est incorrect\n";
		if ((sfeService.getSFE(sou.getSfe()).getEncadreur().getUserId()) == sou.getRapporteur())
			msg += "Le rapporteur et l'encadreur ne doivent pas étre le meme !\n";
		if ((sfeService.getSFE(sou.getSfe()).getEncadreur().getUserId()) == sou.getPresident())
			msg += "Le président et l'encadreur ne doivent pas étre le meme !\n";
		if (sou.getPresident() == sou.getRapporteur())
			msg += "Le rapporteur et le président ne doivent pas étre le meme !\n";
		if (findByDateJury(sou.getDate(),sou.getRapporteur()).size() != 0)
			msg += "Le rapporteur a déja une soutenance à cette date !\n";
		if (findByDateJury(sou.getDate(),sou.getPresident()).size() != 0)
			msg += "Le président a déja une soutenance à cette date !\n";
		return msg;
	}

	@Override
	public List<SoutenanceDTO> findByDateJury(LocalDateTime date, Long id) {
		// TODO Auto-generated method stub
		List<Soutenance> tmp = this.soutenanceRepository.findByDateJury(date, id);
		ArrayList<SoutenanceDTO> list = new ArrayList();
		for (int i = 0; i < tmp.size();i++) {
			SoutenanceDTO s = new SoutenanceDTO();
			s.setId(tmp.get(i).getId());
			s.setSfe(tmp.get(i).getSfe().getSujet());
			s.setEncadreur(tmp.get(i).getSfe().getEncadreur().getNom() + " " +tmp.get(i).getSfe().getEncadreur().getPrenom() );
			s.setPresident(tmp.get(i).getPresident().getNom() + " " +tmp.get(i).getPresident().getPrenom());
			s.setRapporteur(tmp.get(i).getRapporteur().getNom() + " " +tmp.get(i).getRapporteur().getPrenom());
			s.setDate(tmp.get(i).getDate());
			s.setSalle(tmp.get(i).getSalle().getNom());
			list.add(s);
		}
		return list;
	}

	@Override
	public SoutenanceDTO getBySfeId(int id) {
		// TODO Auto-generated method stub
		Soutenance tmp = this.soutenanceRepository.findBySfeId(id);
		SoutenanceDTO s = new SoutenanceDTO();
		if (tmp != null) {
			s.setId(tmp.getId());
			s.setSfe(tmp.getSfe().getSujet());
			s.setEncadreur(tmp.getSfe().getEncadreur().getNom() + " " +tmp.getSfe().getEncadreur().getPrenom() );
			s.setPresident(tmp.getPresident().getNom() + " " +tmp.getPresident().getPrenom());
			s.setRapporteur(tmp.getRapporteur().getNom() + " " +tmp.getRapporteur().getPrenom());
			s.setDate(tmp.getDate());
			s.setSalle(tmp.getSalle().getNom());
			return s;
		}
		return null;

	}

	@Override
	public SoutenanceDTO getByEtudId(int id) {
		// TODO Auto-generated method stub
		Soutenance tmp = this.soutenanceRepository.findByEtudId(id);
		SoutenanceDTO s = new SoutenanceDTO();
		if (tmp != null) {
			s.setId(tmp.getId());
			s.setSfe(tmp.getSfe().getSujet());
			s.setEncadreur(tmp.getSfe().getEncadreur().getNom() + " " +tmp.getSfe().getEncadreur().getPrenom() );
			s.setPresident(tmp.getPresident().getNom() + " " +tmp.getPresident().getPrenom());
			s.setRapporteur(tmp.getRapporteur().getNom() + " " +tmp.getRapporteur().getPrenom());
			s.setDate(tmp.getDate());
			s.setSalle(tmp.getSalle().getNom());
			return s;
		}
		return null;
	}
	
	
	@Override
	public List<SoutenanceDTO1> getByEnId(int id) {
				List<Soutenance> tmp = this.soutenanceRepository.findByEnId(id);
				ArrayList<SoutenanceDTO1> list = new ArrayList();
				for (int i = 0; i < tmp.size();i++) {
					SoutenanceDTO1 s = new SoutenanceDTO1();
					s.setId(tmp.get(i).getId());
					s.setSfe(tmp.get(i).getSfe().getSujet());
					Long idEtud =  tmp.get(i).getSfe().getEtudiant().getUserId();
					List<FileDB> files = this.fileStorageService.get(idEtud);
					List<File> ff = new ArrayList<>();
					for(FileDB f:files){
						File fi = new File();
						String fileDownloadUri =  ServletUriComponentsBuilder
								.fromCurrentContextPath()
								.path("/files/")
								.path(f.getId())
								.toUriString();
						fi.setFileDownloadUri(fileDownloadUri);
						fi.setRapport(f.getName());
						fi.setIdFile(f.getId());
						ff.add(fi);
					}
					s.setFiles(ff);
					s.setEncadreur(tmp.get(i).getSfe().getEncadreur().getNom() + " " +tmp.get(i).getSfe().getEncadreur().getPrenom() );
					s.setPresident(tmp.get(i).getPresident().getNom() + " " +tmp.get(i).getPresident().getPrenom());
					s.setRapporteur(tmp.get(i).getRapporteur().getNom() + " " +tmp.get(i).getRapporteur().getPrenom());
					s.setDate(tmp.get(i).getDate());
					s.setSalle(tmp.get(i).getSalle().getNom());
					list.add(s);
				}
				return list;

	}
}
	
