package lab08_JavaFX;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Tabela")
public class Tabela {

	List<Player> players = new ArrayList<>();

	public void add(Player... player) {
		players = new ArrayList<>();
		this.players.addAll(Arrays.asList(player));
	}

	@XmlElements(@XmlElement(name = "zawodnik"))
	public List<Player> getPlayer() {
		return players;
	}
}
