package siolacol;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Periode {
	//	date correspondant à la période de garde
	private GregorianCalendar laDate;
	//	tranche de la période (1, 2, 3 ou 4)
	private int tranche;

	//	pompiers déjà en intervention
	private ArrayList<Pompier> enMission;
	//	pompiers au travail chez leur employeur
	public ArrayList<Pompier> getEnMission() {
		return enMission;
	}
	private ArrayList<Pompier> auTravail;
	//	pompiers disponibles à leur domicile
	private ArrayList<Pompier> disponible;

	/**
	 * 	Ce constructeur valorise les attributs privés de l'objet Période
	 * @param laDate
	 * @param tranche
	 */
	public Periode(GregorianCalendar laDate, int tranche) {

		this.laDate = laDate;
		this.tranche = tranche;
		enMission = new ArrayList<Pompier>();
		auTravail = new ArrayList<Pompier>();
		disponible = new ArrayList<Pompier>();

	}

	public int getTranche(){
		return this.tranche;
	}

	/**
	 * Affecte le pompier passé en paramètre à l'une des listes : autravail ou disponible
	 * @param statut
	 * @param unPompier
	 */
	public void setStatutPompier(String statut, Pompier unPompier){
		if (statut.equals("t")){
			this.auTravail.add(unPompier);
		}else {
			this.disponible.add(unPompier);
		}
	}

	/**
	 * Retourne 'm' si le pompier "unPompier" est en mission pour la période courante
	 * Retourne 't' si le pompier "unPompier" est au travail pour la période courante
	 * Retourne 'd' si le pompier "unPompier" est disponible pour la période courante
	 * @param unPompier
	 * @return
	 */

	public String getStatut(Pompier unPompier){
		String retour="";
		if(this.auTravail.contains(unPompier))
			retour = "t";
		if(this.enMission.contains(unPompier))
			retour = "m";
		if(this.disponible.contains(unPompier))
			retour = "d";
		return retour;
	}

	/**
	 * Modifie le statut du pompier passé en paramètre : de "disponible" à "enMission"
	 * ou de "auTravail" à "enMission".
	 * @param unPompier
	 */
	public void missionner(Pompier unPompier) {
		if(auTravail.contains(unPompier)) {
			auTravail.remove(unPompier);
		} else {
			disponible.remove(unPompier);
		}
		if (!enMission.contains(unPompier)) {
			enMission.add(unPompier);
		}
	}

	/**
	 * Retourne une collection d'au maximum "nbPompiers" pompiers pouvant être mobilisés pour
	 * une intervention lors de la période courante. Si un nombre suffisant de pompiers ne peut pas
	 * tre sélectionné, une équipe incomplète sera constituée.
	 * Cette fonction mobilise en priorité les pompiers disponibles, puis ceux qui
	 * sont au travail si nécessaire. Le statut des pompiers n'est pas modifié par cette méthode.
	 * @param nbPompiers
	 * @return
	 */
	public ArrayList<Pompier> selectEquipe(int nbPompiers){
		ArrayList<Pompier> uneEquipe = new ArrayList<Pompier>();
		Integer i,nb,nbDispo,nbAuTravail;
		nbDispo = disponible.size();
		if(nbDispo<nbPompiers) {
			nb=0;
			for(i=0 ; i < nbDispo ; i++) {
				uneEquipe.add(disponible.get(i));
				nb+=1;
			}
			i=0;
			nbAuTravail = auTravail.size();
			while ((i<nbAuTravail)&&(nb<nbPompiers)) {
				uneEquipe.add(auTravail.get(i));
				nb+=1;
				i+=1;
			}
		} else {
			for(i=0;i<nbPompiers;i++) {
				uneEquipe.add(disponible.get(i));
			}
		}

		return uneEquipe;
	}

}
