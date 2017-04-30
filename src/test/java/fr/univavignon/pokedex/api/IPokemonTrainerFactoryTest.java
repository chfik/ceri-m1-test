package fr.univavignon.pokedex.api;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class IPokemonTrainerFactoryTest {
	@Mock private IPokemonTrainerFactory pokemonTrainerFactoryMock;
	@Mock private IPokedexFactory pokedexFactoryMock;
	@Mock private IPokedex pokedexMock;
	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	@Before
	public void setUp() throws PokedexException {
		PokemonTrainer pokemonTrainer = new PokemonTrainer("trainer", Team.INSTINCT, pokedexMock);
		Mockito.when(pokemonTrainerFactoryMock.createTrainer("trainer", Team.INSTINCT, pokedexFactoryMock)).thenReturn(pokemonTrainer);
	}

	@Test 
	public void testPokemonMetadata() {
		PokemonTrainer pokemonTrainer = pokemonTrainerFactoryMock.createTrainer("trainer", Team.INSTINCT, pokedexFactoryMock);
		assertEquals("trainer",pokemonTrainer.getName());
		assertEquals(Team.INSTINCT,pokemonTrainer.getTeam());
		
	}
}
