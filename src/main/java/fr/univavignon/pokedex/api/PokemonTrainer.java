package fr.univavignon.pokedex.api;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

/**
 * Trainer POJO.
 * 
 * @author fv
 */
public class PokemonTrainer implements Observer, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1004403075637584605L;

	/** Trainer name. **/
	private final String name;

	/** Trainer team. **/
	private final Team team;
	
	/** Trainer pokedex. **/
	private final IPokedex pokedex;
	
	/**
	 * Default constructor.
	 * 
	 * @param name Trainer name.
	 * @param team Trainer team.
	 * @param pokedex Trainer pokedex.
	 */
	public PokemonTrainer(final String name, final Team team, final IPokedex pokedex) {
		this.name = name;
		this.team = team;
		this.pokedex = pokedex;
		this.saveAsFile();
	}

	
	/** Name getter. **/
	public String getName() {
		return name;
	}

	/** Team getter. **/
	public Team getTeam() {
		return team;
	}
	
	/** Pokedex getter. **/
	public IPokedex getPokedex() {
		return pokedex;
	}

	@Override
	public void update(Observable o, Object arg) {
		saveAsFile();
	}
	
	public void saveAsFile(){
		ObjectOutputStream oos = null;
		
		try {
			final FileOutputStream fichier = new FileOutputStream(".\\trainers\\"+name+".ser");
			oos = new ObjectOutputStream(fichier);
			oos.writeObject(this);
			oos.flush();
		} catch (Exception e) {
			e.printStackTrace();  
		} finally {
			try {
				if (oos != null) {
					oos.flush();
					oos.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();	
			}
		}
	}

	
}
