package siolacol;

import java.util.ArrayList;

public class Caserne {
	//	L'ensemble des pompiers de garde pour la caserne
	ArrayList<Pompier> lesPompiers;

	/**
	 * Constructeur
	 * @param lesPompiers
	 */
	public Caserne(ArrayList<Pompier> lesPompiers) {
		this.lesPompiers = lesPompiers;
	}

	//	Appelle automatiquement le numéro de bip passé en paramêtre
	public void appeler(String numeroBip){
		//non codable
	}

	/**
	 * Appelle une équipe de pompiers pour une intervention concernant la période passée en
	 * paramètre.
	 * Si un nombre suffisant de pompiers ne peut pas être sollicité, une équipe incomplète sera
	 * appelée.
	 * Cette fonction modifie le statut des pompiers sélectionnés, les appelle sur leur bip et retourne
	 * le nombre de pompiers appelés.
	 * @param unePeriode
	 * @param nbPompiers : représente le nombre de pompiers à solliciter.
	 * @return
	 */
	public int appelEquipe(Periode unePeriode, int nbPompiers){
		ArrayList<Pompier> uneEquipe;
		uneEquipe=unePeriode.selectEquipe(nbPompiers);
		for(Pompier unPompier : uneEquipe) {
			unPompier.missionner(unePeriode);
			appeler(unPompier.getNumeroBip());
		}
		return uneEquipe.size();
	}
}
