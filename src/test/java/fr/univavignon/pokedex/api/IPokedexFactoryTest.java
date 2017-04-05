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

public class IPokedexFactoryTest {
	@Mock private IPokedexFactory pokedexFactoryMock;
	@Mock private IPokemonMetadataProvider pokemonMetadataProviderMock;
	@Mock private IPokemonFactory pokemonFactoryMock;
	@Mock private IPokedex pokedexMock;
	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	Pokemon pokemon = new Pokemon(0, "Bulbizarre", 126, 126, 90, 613, 64, 4000, 4, 56);
	List<Pokemon> pokemons = new ArrayList<Pokemon>();
	
	@Before
	public void setUp() throws PokedexException {
		pokemons.add(pokemon);
		Mockito.when(pokedexFactoryMock.createPokedex(pokemonMetadataProviderMock, pokemonFactoryMock)).thenReturn(pokedexMock);
		
		Mockito.when(pokemonMetadataProviderMock.getPokemonMetadata(0)).thenReturn(new PokemonMetadata(0,"Bulbizarre",126,126,90));
		Mockito.when(pokemonFactoryMock.createPokemon(0, 613, 64, 4000, 4)).thenReturn(pokemon);
	
		Mockito.when(pokedexMock.size()).thenReturn(0);
		Mockito.when(pokedexMock.addPokemon(pokemon)).thenReturn(0);
		Mockito.when(pokedexMock.getPokemon(0)).thenReturn(pokemon);
		Mockito.when(pokedexMock.getPokemons()).thenReturn(pokemons);
	}
	
	@Test 
	public void testCreatePokedex() throws PokedexException{
		
		IPokedex pokedex = pokedexFactoryMock.createPokedex(pokemonMetadataProviderMock, pokemonFactoryMock);
		assertEquals(0, pokedex.size());
		assertEquals(0, pokedex.addPokemon(pokemon));
		assertEquals(pokemon, pokedex.getPokemon(0));
		assertEquals(1, pokedexMock.getPokemons().size());
		
	}
}
