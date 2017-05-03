package fr.univavignon.pokedex.imp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import fr.univavignon.pokedex.api.IPokedex;
import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.IPokemonMetadataProvider;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.Pokemon;
import fr.univavignon.pokedex.api.PokemonMetadata;
import fr.univavignon.pokedex.api.PokemonTrainer;

public class Pokedex implements IPokedex, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3119629640291676519L;
	private IPokemonMetadataProvider pokemonMetadataProvider; 
	private IPokemonFactory pokemonFactory;
	
	private Map<Integer,Pokemon> pokemonsMap; 
	private PokemonTrainer pokemonTrainer;
	
	public Pokedex(IPokemonMetadataProvider pokemonMetadataProvider, IPokemonFactory pokemonFactory) {
		super();
		this.pokemonMetadataProvider = pokemonMetadataProvider;
		this.pokemonFactory = pokemonFactory;
		this.pokemonsMap = new LinkedHashMap<Integer,Pokemon>();
	}
	
	@Override
	public int size() {
		return pokemonsMap.size();
	}

	@Override
	public int addPokemon(Pokemon pokemon) {
		Integer place = size();
		pokemonsMap.put(size(), pokemon);
		notifyPokemonTrainer();
		return place;
	}

	@Override
	public Pokemon getPokemon(int id) throws PokedexException {
		if(!pokemonsMap.containsKey(id))
			throw new PokedexException("Le Pokemon n'est pas disponible dans le pokedex.");
		else 
			return pokemonsMap.get(id);
		
	}

	@Override
	public List<Pokemon> getPokemons() {
	    List<Pokemon> pokemons = new ArrayList<>(pokemonsMap.values());
		return pokemons;
	}

	@Override
	public List<Pokemon> getPokemons(Comparator<Pokemon> order) {
		List<Pokemon> pokemons = new ArrayList<>(pokemonsMap.values());
		Collections.sort(pokemons, order);
		return pokemons;
	}

	@Override
	public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
		return pokemonMetadataProvider.getPokemonMetadata(index);
	}
	
	@Override
	public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
		return pokemonFactory.createPokemon(index, cp, hp, dust, candy);
	}

	@Override
	public void notifyPokemonTrainer() {
		pokemonTrainer.saveAsFile();
		
	}

	@Override
	public void setPokemonTrainer(PokemonTrainer pokemonTrainer) {
		this.pokemonTrainer = pokemonTrainer;
		
	}


}
