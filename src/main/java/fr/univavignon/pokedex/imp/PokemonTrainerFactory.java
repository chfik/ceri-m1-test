package fr.univavignon.pokedex.imp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import fr.univavignon.pokedex.api.IPokedex;
import fr.univavignon.pokedex.api.IPokedexFactory;
import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.IPokemonMetadataProvider;
import fr.univavignon.pokedex.api.IPokemonTrainerFactory;
import fr.univavignon.pokedex.api.PokemonTrainer;
import fr.univavignon.pokedex.api.Team;

public class PokemonTrainerFactory implements IPokemonTrainerFactory {

	@Override
	public PokemonTrainer createTrainer(String name, Team team, IPokedexFactory pokedexFactory) {
		ObjectInputStream ois = null;

		try {
			
			final FileInputStream fichier = new FileInputStream("./trainers/"+name+".ser");
			ois = new ObjectInputStream(fichier);
			PokemonTrainer pokemonTrainer = (PokemonTrainer) ois.readObject();
			return pokemonTrainer;
			
		} catch (Exception e) {
			
			IPokemonMetadataProvider metadataProvider = new PokemonMetadataProvider(); 
			IPokemonFactory pokemonFactory = new PokemonFactory();
			
			IPokedex pokedex = pokedexFactory.createPokedex(metadataProvider, pokemonFactory);
			PokemonTrainer pokemonTrainer = new PokemonTrainer(name, team, pokedex);
			pokedex.setPokemonTrainer(pokemonTrainer);
			return new PokemonTrainer(name, team, pokedex);
			
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();        
			}
		}
	}
}
