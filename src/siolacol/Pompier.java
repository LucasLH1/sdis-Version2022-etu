package siolacol;

public class Pompier {
	private String nom;
	private String prenom;
	private String numeroBip;

	/**
	 * Constructeur
	 * @param nom
	 * @param prenom
	 * @param numeroBip
	 */
	public Pompier(String nom, String prenom, String numeroBip) {
		this.nom = nom;
		this.prenom = prenom;
		this.numeroBip = numeroBip;
	}

	/**
	 * Retourne le numéro de bip du pompier
	 * @return
	 */
	public String getNumeroBip(){
		return this.numeroBip;
	}

	/**
	 * Retourne le statut du pompier courant pour la période passée en paramètre :
	 * 'm' pour en mission, 't' pour au travail, ou 'd' pour disponible
	 * @param unePeriode
	 * @return
	 */
	public String getStatut(Periode unePeriode){

		return unePeriode.getStatut(this);
	}

	/**
	 * Modifie le statut du pompier courant pour la période passée en paramètre
	 * @param unePeriode
	 */
	public void missionner(Periode unePeriode){
		unePeriode.missionner(this);
	}
}
