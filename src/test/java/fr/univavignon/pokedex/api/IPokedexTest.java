package fr.univavignon.pokedex.api;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class IPokedexTest {
	@Mock private IPokedex pokedexMock;
	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	Pokemon pokemon = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);
	PokemonMetadata PokemonMetadata = new PokemonMetadata(0,"Bulbizarre",126,126,90);
	List<Pokemon> pokemons = new ArrayList<Pokemon>();
	
	@Before
	public void setUp() throws PokedexException {
		pokemons.add(pokemon);
		Mockito.when(pokedexMock.size()).thenReturn(0);
		Mockito.when(pokedexMock.addPokemon(pokemon)).thenReturn(0);
		Mockito.when(pokedexMock.getPokemon(0)).thenReturn(pokemon);
		Mockito.when(pokedexMock.getPokemons()).thenReturn(pokemons);
	}
	
	@Test(expected=PokedexException.class)
	public void testPokemonException() throws PokedexException{
		pokedexMock.getPokemon(-1);
	}
	
	@Test 
	public void testPokedex() throws PokedexException{
		
		assertEquals(0, pokedexMock.size());
		assertEquals(0, pokedexMock.addPokemon(pokemon));
		assertEquals(pokemon, pokedexMock.getPokemon(0));
		assertEquals(1, pokedexMock.getPokemons().size());
		
	}
}
