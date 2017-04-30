package fr.univavignon.pokedex.imp;

import org.apache.http.client.fluent.Request;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import fr.univavignon.pokedex.api.IPokemonMetadataProvider;
import fr.univavignon.pokedex.api.PokedexException;
import fr.univavignon.pokedex.api.PokemonMetadata;

public class PokemonMetadataProvider implements IPokemonMetadataProvider {



	@Override
	public PokemonMetadata getPokemonMetadata(int index) throws PokedexException {
		
		if(index < 0 || index > 150)
			throw new PokedexException("Invalid index !");
		
		try {
			String pokemonsDatas = Request.Get("https://github.com/chfik/ceri-m1-test/blob/master/data.json").execute().returnContent().asString();
			JSONArray pokemons = new JSONArray(pokemonsDatas);	
			JSONObject our_pok = pokemons.getJSONObject(index-1);
			PokemonMetadata pokemonMetadata = getPokemonMetadataFromJSONObject(our_pok);
			return pokemonMetadata;
		} catch (Exception e) {
			throw new PokedexException("error");
		}
		
	}
	
	public PokemonMetadata getPokemonMetadataFromJSONObject(JSONObject data) throws JSONException{
		PokemonMetadata pokemonMetadata;
		pokemonMetadata = new PokemonMetadata(	data.getInt("PkMn")-1,
												data.getString("Identifier"), 
												data.getInt("BaseAttack"), 
												data.getInt("BaseDefense"), 
												data.getInt("BaseStamina"));
		return pokemonMetadata;
		
	}

}