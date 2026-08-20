package java8;

import java8.Player;

import java.util.ArrayList;
import java.util.List;

public class Team {
	
	List<Player> players = new ArrayList<>();

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	@Override
	public String toString() {
		return "Team [players=" + players + "]";
	}
	
	

}
