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
	@Mock protected IPokedex pokedexMock;
	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

	private Pokemon bulbizarre = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);
	private Pokemon aquali = new Pokemon(133, "Aquali", 186, 168, 260, 2729, 202, 5000, 4, 100);
	
	private List<Pokemon> pokemons = new ArrayList<Pokemon>();
	
	@Before
	public void setUp() throws PokedexException {
		pokemons.add(bulbizarre);
		Mockito.when(pokedexMock.size()).thenReturn(0);
		Mockito.when(pokedexMock.addPokemon(bulbizarre)).thenReturn(0);
		Mockito.when(pokedexMock.addPokemon(aquali)).thenReturn(1);
		Mockito.when(pokedexMock.getPokemon(0)).thenReturn(bulbizarre);
		Mockito.when(pokedexMock.getPokemons()).thenReturn(pokemons);
		Mockito.when(pokedexMock.getPokemon(-1)).thenThrow(new PokedexException(""));
		
		List<Pokemon> pokemonsSortedByName = new ArrayList<Pokemon>();
		pokemonsSortedByName.add(aquali);
		pokemonsSortedByName.add(bulbizarre);
		List<Pokemon> pokemonsSortedByCP = new ArrayList<Pokemon>();
		pokemonsSortedByCP.add(bulbizarre);
		pokemonsSortedByCP.add(aquali);
		List<Pokemon> pokemonsSortedByIndex = new ArrayList<Pokemon>();
		pokemonsSortedByIndex.add(bulbizarre);
		pokemonsSortedByIndex.add(aquali);
		Mockito.when(pokedexMock.getPokemons(PokemonComparators.NAME)).thenReturn(pokemonsSortedByName);
		Mockito.when(pokedexMock.getPokemons(PokemonComparators.CP)).thenReturn(pokemonsSortedByCP);
		Mockito.when(pokedexMock.getPokemons(PokemonComparators.INDEX)).thenReturn(pokemonsSortedByIndex);
	}
	
	@Test(expected=PokedexException.class)
	public void testPokemonException() throws PokedexException{
		pokedexMock.getPokemon(-1);
	}
	
	@Test 
	public void testPokedex() throws PokedexException{
		
		assertEquals(0, pokedexMock.size());
		assertEquals(0, pokedexMock.addPokemon(bulbizarre));
		assertEquals(bulbizarre, pokedexMock.getPokemon(0));
		assertEquals(1, pokedexMock.getPokemons().size());
		
	}
	
	@Test 
	public void testCPComparatorPokedex() {
		
		assertEquals(0, pokedexMock.size());
		assertEquals(0, pokedexMock.addPokemon(bulbizarre));
		assertEquals(1, pokedexMock.addPokemon(aquali));
		
		List<Pokemon> pokemonsSortedByCP = pokedexMock.getPokemons(PokemonComparators.CP);
		assertEquals(0, pokemonsSortedByCP.get(0).getIndex());
		assertEquals(133, pokemonsSortedByCP.get(1).getIndex());
		
	}
	
	
	@Test 
	public void testIndexComparatorPokedex() {
		
		assertEquals(0, pokedexMock.size());
		assertEquals(0, pokedexMock.addPokemon(bulbizarre));
		assertEquals(1, pokedexMock.addPokemon(aquali));
		
		List<Pokemon> pokemonsSortedByIndex = pokedexMock.getPokemons(PokemonComparators.INDEX);
		assertEquals(0, pokemonsSortedByIndex.get(0).getIndex());
		assertEquals(133, pokemonsSortedByIndex.get(1).getIndex());
		
	}
}
