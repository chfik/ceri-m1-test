package fr.univavignon.pokedex.imp;

import java.io.Serializable;

import fr.univavignon.pokedex.api.IPokemonFactory;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.Pokemon;
import fr.univavignon.pokedex.api.PokemonMetadata;

public class PokemonFactory implements IPokemonFactory, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3318314959394346304L;
	
	/**
	 * {@inheritDoc}
	 **/
	public double calculIV(String name, int cp, int dust){
		return 56.0;
	}
	
	@Override
	public Pokemon createPokemon(int index, int cp, int hp, int dust, int candy) {
		PokemonMetadataProvider pokMetadaProvider = new PokemonMetadataProvider();
		PokemonMetadata pokMetadata;
		try {
			pokMetadata = pokMetadaProvider.getPokemonMetadata(index);
		} catch (PokedexException e) {
			return null;
		}
		String name = pokMetadata.getName();
		int attack = pokMetadata.getAttack();
		int defense = pokMetadata.getDefense();
		int stamina = pokMetadata.getStamina();
		double iv = calculIV(name, cp, dust);
		Pokemon pok = new Pokemon(index, name, attack, defense, stamina, cp, hp, dust, candy, iv);
		return pok;
	}
	

}
