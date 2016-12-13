package lab08_JavaFX;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

	private TableView<Player> table = new TableView<Player>();
	private TableView<Competition> tableComp = new TableView<Competition>();
	private Tabela tabela = new Tabela();

	private static ObservableList<Player> data;

	private static ObservableList<Competition> dataCompetition;

	public static void main(String[] args) throws JAXBException {
		Main main = new Main();
		main.addPlayers();
		launch(args);
		main.showXml();
	}

	public void addPlayers(){
		Competition comp1 = new Competition("Letni Puchar Kontynentalny");
		Competition comp2 = new Competition("Letnie Grand Prix");
		Competition comp3 = new Competition("Turniej Czterech Skoczni");
		Competition comp4 = new Competition("Olimpijski festiwal młodzieży Europy");
		Competition comp5 = new Competition("Mistrzostwa świata juniorów");

		tabela.add(new Player(1, "Domen Prevc", 5, 360, comp1, comp2, comp3, comp4, comp5),
				new Player(2, "Daniel-André Tande", 5, 308, comp2, comp4, comp5, comp1, comp3),
				new Player(3, "Maciej Kot", 5, 247, comp3, comp1, comp5, comp4, comp2),
				new Player(4, "Severin Freund", 5, 233, comp1, comp2, comp3, comp4, comp5),
				new Player(5, "Stefan Kraft", 5, 231, comp1, comp2, comp3, comp4, comp5),
				new Player(23, "Anders Fannemel", 4, 44, comp4, comp3, comp2, comp1),
				new Player(30, "Robert Johansson", 3, 24, comp1, comp2, comp5),
				new Player(40, "Jarkko Määttä", 2, 5, comp1, comp4));
		data = FXCollections.observableArrayList(tabela.getPlayer());
	}
	
	public void showXml() throws JAXBException {
//
//		Competition comp1 = new Competition("Letni Puchar Kontynentalny");
//		Competition comp2 = new Competition("Letnie Grand Prix");
//		Competition comp3 = new Competition("Turniej Czterech Skoczni");
//		Competition comp4 = new Competition("Olimpijski festiwal młodzieży Europy");
//		Competition comp5 = new Competition("Mistrzostwa świata juniorów");
//
//		tabela.add(new Player(1, "Domen Prevc", 5, 360, comp1, comp2, comp3, comp4, comp5),
//				new Player(2, "Daniel-André Tande", 5, 308, comp2, comp4, comp5, comp1, comp3),
//				new Player(3, "Maciej Kot", 5, 247, comp3, comp1, comp5, comp4, comp2),
//				new Player(4, "Severin Freund", 5, 233, comp1, comp2, comp3, comp4, comp5),
//				new Player(5, "Stefan Kraft", 5, 231, comp1, comp2, comp3, comp4, comp5),
//				new Player(23, "Anders Fannemel", 4, 44, comp4, comp3, comp2, comp1),
//				new Player(30, "Robert Johansson", 3, 24, comp1, comp2, comp5),
//				new Player(40, "Jarkko Määttä", 2, 5, comp1, comp4));

		JAXBContext jaxbContext = JAXBContext.newInstance(Tabela.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		jaxbMarshaller.marshal(tabela, System.out);
		
		jaxbMarshaller.marshal(tabela, new File("skoki.xml"));
		
		

	}

	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage stage) {
		Scene scene = new Scene(new Group());
		stage.setTitle("Skoki narciarskie");
		stage.setWidth(800);
		stage.setHeight(600);

		final Label label = new Label("Puchar Świata w skokach narciarskich 2016/2017");
		label.setFont(new Font("Arial", 20));

		table.setEditable(true);
		tableComp.setEditable(true);
		table.setRowFactory(tv -> {
			TableRow<Player> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 1 && (!row.isEmpty())) {
					Player rowData = row.getItem();
					dataCompetition = FXCollections.observableArrayList(rowData.getList());
					tableComp.setItems(dataCompetition);
					tableComp.refresh();
				}
			});
			return row;
		});

		TableColumn<Player, Integer> placeColumn = new TableColumn<>("Miejsce");
		placeColumn.setMinWidth(100);
		placeColumn.setCellValueFactory(new PropertyValueFactory<Player, Integer>("place"));

		TableColumn<Player, String> nameColumn = new TableColumn<>("Zawodnik");
		nameColumn.setMinWidth(100);
		nameColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));

		TableColumn<Player, Integer> showsColumn = new TableColumn<>("Występy");
		showsColumn.setMinWidth(100);
		showsColumn.setCellValueFactory(new PropertyValueFactory<Player, Integer>("shows"));

		TableColumn<Player, Integer> pointsColumn = new TableColumn<>("Punkty");
		pointsColumn.setMinWidth(100);
		pointsColumn.setCellValueFactory(new PropertyValueFactory<Player, Integer>("points"));

		TableColumn<Competition, String> competitionColumn = new TableColumn<>("Konkursy");
		competitionColumn.setMinWidth(250);
		competitionColumn.setCellValueFactory(new PropertyValueFactory<Competition, String>("name"));

		table.setItems(data);
		table.getColumns().addAll(placeColumn, nameColumn, showsColumn, pointsColumn);
		tableComp.getColumns().addAll(competitionColumn);
		
		
		
		//*******************dodanie nowego zawodnika
		
		 final TextField fieldPlace = new TextField();
		 fieldPlace.setPromptText("Miejsce");
		 fieldPlace.setMaxWidth(placeColumn.getPrefWidth());
		 
	        final TextField fieldName = new TextField();
	        fieldName.setMaxWidth(200);
	        fieldName.setPromptText("Imię i nazwisko");
	        
	        final TextField fieldShows = new TextField();
	        fieldShows.setMaxWidth(showsColumn.getPrefWidth());
	        fieldShows.setPromptText("Występy");
	 
	        final TextField fieldPoints = new TextField();
	        fieldPoints.setMaxWidth(pointsColumn.getPrefWidth());
	        fieldPoints.setPromptText("Punkty");
		        
	        final Button addButton = new Button("Dodaj");
	        addButton.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent e) {
	            	Player newPlayer = new Player(Integer.parseInt(fieldPlace.getText()), fieldName.getText(), 
	                		Integer.parseInt(fieldShows.getText()), Integer.parseInt(fieldPoints.getText()));
	            	
	            	tabela.add(newPlayer);
	                data.add(newPlayer);

	                fieldPlace.clear();
	                fieldName.clear();
	                fieldShows.clear();
	                fieldPoints.clear();
	            }
	        });
		
		//***********
		
		
		final VBox vbox = new VBox();
		final HBox hbox = new HBox();
		final HBox hb = new HBox();
		
		 hb.getChildren().addAll(fieldPlace, fieldName, fieldShows, fieldPoints, addButton);
	        hb.setSpacing(3);
	        
		hbox.getChildren().addAll(table, tableComp);
		hbox.setSpacing(5);
		hbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(label, hbox,hb);

		((Group) scene.getRoot()).getChildren().addAll(vbox);

		stage.setScene(scene);
		stage.show();
	}
}