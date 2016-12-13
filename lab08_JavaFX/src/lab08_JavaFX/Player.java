package lab08_JavaFX;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.bind.annotation.*;

public class Player {

	// public Player(){}
	public Player(int place, String name, int shows, int points, Competition... list) {
		this.place = place;
		this.name = name;
		this.shows = shows;
		this.points = points;
		this.setList(list);
	}

	private int place;
	private String name;
	private int shows;
	private int points;
	private List<Competition> list;

	@XmlElement(name = "pozycja")
	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
	}

	@XmlElement(name = "imie")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name = "występy")
	public int getShows() {
		return shows;
	}

	public void setShows(int shows) {
		this.shows = shows;
	}

	@XmlElement(name = "punkty")
	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	@XmlElement(name = "konkursy")
	public List<Competition> getList() {
		return list;
	}

	public void setList(Competition... list) {
		this.list = new ArrayList<>();
		this.list.addAll(Arrays.asList(list));
	}
}