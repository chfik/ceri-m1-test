package fr.univavignon.pokedex.imp;


import fr.univavignon.pokedex.api.IPokemonMetadataProviderTest;
import fr.univavignon.pokedex.api.PokedexException;

public class PokemonMetadataProviderTest extends IPokemonMetadataProviderTest{

	@Override
	public void setUp() throws PokedexException {
		pokemonMetadataProviderMock = new PokemonMetadataProvider () ;
	}
	

}
