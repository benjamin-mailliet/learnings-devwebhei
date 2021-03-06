package learnings.model;

import learnings.enums.RessourceCategorie;
import learnings.enums.TypeSeance;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

public class Seance implements Serializable {
	private static final long serialVersionUID = 4961204598561923877L;

	private Long id;
	private String titre;
	private String description;
	private List<Ressource> ressources;

	private Boolean isNote;
	private Date dateLimiteRendu;
	private Date date;
	private TypeSeance type;
	private Integer nbMaxElevesParRendu;

	private List<RenduTp> travauxRendus;
	private BigDecimal sommeNotes;
	private int nbNotes;
	private BigDecimal moyenne;

	public Seance(Long id, String titre, String description, java.util.Date date) {
		this.id = id;
		this.titre = titre;
		this.description = description;
		this.date = date;
	}

	public Seance(Long id, String titre, String description, Date date, Boolean isNote, Date dateLimiteRendu, TypeSeance type, Integer nbMaxElevesParRendu) {
		this.id = id;
		this.titre = titre;
		this.description = description;
		this.isNote = isNote;
		this.dateLimiteRendu = dateLimiteRendu;
		this.date = date;
		this.type = type;
		this.nbMaxElevesParRendu = nbMaxElevesParRendu;
		this.nbNotes = 0;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Ressource> getRessources() {
		return ressources;
	}

	public void setRessources(List<Ressource> ressources) {
		this.ressources = ressources;
	}

	public Boolean getIsNote() {
		return isNote;
	}

	public void setIsNote(Boolean note) {
		isNote = note;
	}

	public Date getDateLimiteRendu() {
		return dateLimiteRendu;
	}

	public void setDateLimiteRendu(Date dateLimiteRendu) {
		this.dateLimiteRendu = dateLimiteRendu;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public TypeSeance getType() {
		return type;
	}

	public void setType(TypeSeance type) {
		this.type = type;
	}

	public Integer getNbMaxElevesParRendu() {
		return nbMaxElevesParRendu;
	}

	public void setNbMaxElevesParRendu(Integer nbMaxElevesParRendu) {
		this.nbMaxElevesParRendu = nbMaxElevesParRendu;
	}

	public List<RenduTp> getTravauxRendus() {
		return travauxRendus;
	}

	public void setTravauxRendus(List<RenduTp> travauxRendus) {
		this.travauxRendus = travauxRendus;
	}

	public BigDecimal getSommeNotes() {
		return sommeNotes;
	}

	public void setSommeNotes(BigDecimal sommeNotes) {
		this.sommeNotes = sommeNotes;
	}

	public int getNbNotes() {
		return nbNotes;
	}

	public void setNbNotes(int nbNotes) {
		this.nbNotes = nbNotes;
	}

	public BigDecimal getMoyenne() {
		return moyenne;
	}

	public void setMoyenne(BigDecimal moyenne) {
		this.moyenne = moyenne;
	}

	public boolean isDatePassee() {
		return this.date.before(new Date());
	}

	public void addNote(BigDecimal note){
		this.nbNotes++;
		if(this.sommeNotes!=null) {
			this.sommeNotes = this.sommeNotes.add(note);
		}else{
			this.sommeNotes=note;
		}
		this.calculerMoyenne();
	}

	public boolean isCorrige() {
		if (this.ressources == null) {
			return false;
		}
		return this.ressources.stream().anyMatch(r -> RessourceCategorie.CORRECTION.equals(r.getCategorie()));
	}

	private void calculerMoyenne() {
		if(this.sommeNotes!=null && this.nbNotes>0){
			this.moyenne = this.sommeNotes.divide(new BigDecimal(this.nbNotes),2, RoundingMode.HALF_EVEN);
		}
	}
}
