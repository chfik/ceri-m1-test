package fr.univavignon.pokedex.imp;

import org.junit.Before;

import fr.univavignon.pokedex.api.IPokemonTrainerFactoryTest;
import fr.univavignon.pokedex.api.PokedexException;

public class PokemonTrainerFactoryTest extends IPokemonTrainerFactoryTest {
	
	@Before
	public void setUp() throws PokedexException {
		pokemonTrainerFactoryMock = new PokemonTrainerFactory();
		pokedexFactoryMock = new PokedexFactory();
	}
}
