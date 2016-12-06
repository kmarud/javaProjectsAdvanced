package lab08_JavaFX;

import javafx.application.Application; 
import javafx.collections.ObservableList; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.stage.Stage; 
import javafx.scene.text.Font; 
import javafx.scene.text.Text; 
      

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
 
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
 
public class Main extends Application {
 
    private TableView<Person> table = new TableView<Person>();
    private TableView<Concurs> concurses = new TableView<Concurs>();
    
    private final ObservableList<Person> data =
        FXCollections.observableArrayList(
            new Person("Jacob", "Smith", "jacob.smith@example.com"),
            new Person("Isabella", "Johnson", "isabella.johnson@example.com"),
            new Person("Ethan", "Williams", "ethan.williams@example.com"),
            new Person("Emma", "Jones", "emma.jones@example.com"),
            new Person("Michael", "Brown", "michael.brown@example.com")
        );
    
    private ObservableList<Concurs> concursData =
            FXCollections.observableArrayList(
                new Concurs("asd")
            );
   
    public static void main(String[] args) {
        launch(args);
    }
 
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Klasyfikacja");
        stage.setWidth(800);
        stage.setHeight(500);
 
        final Label label = new Label("Zawody narciarskie");
        label.setFont(new Font("Arial", 20));
 
        concurses.setEditable(true);
        table.setEditable(true);
 
        TableColumn firstNameCol = new TableColumn("Miejsce");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("firstName"));
 
        TableColumn lastNameCol = new TableColumn("Zawodnik");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("lastName"));
 
        TableColumn emailCol = new TableColumn("Wystepy");
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("email"));
        
        TableColumn concurs = new TableColumn("Konkursy");
        concurs.setMinWidth(200);
        concurs.setCellValueFactory(
                new PropertyValueFactory<Concurs, String>("name"));
 
        table.setItems(data);
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
 
        concurses.setItems(concursData);
        table.setRowFactory( tv -> {
            TableRow<Person> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (! row.isEmpty()) ) {
//                    MyType rowData = row.getItem();
                	System.out.println(row.getIndex());
                	concursData =  FXCollections.observableArrayList(
                            new Concurs(String.valueOf(row.getIndex()))
                        );
                	concurses.setItems(concursData);
                }
            });
            return row ;
        });
        concurses.getColumns().addAll(concurs);
        
        final VBox vbox = new VBox();
        final HBox hbox = new HBox();
        
        hbox.getChildren().addAll(table, concurses);
        hbox.setSpacing(5);
        hbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, hbox);
        
 
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
 
        stage.setScene(scene);
        stage.show();
    }
 
    public static class Concurs{
    	 private final SimpleStringProperty name;
    	 
         private Concurs(String name) {
             this.name = new SimpleStringProperty(name);
         }
  
         public String getName() {
             return name.get();
         }
  
         public void setName(String name) {
        	 this.name.set(name);
         }
    }
    
    public static class Person {
 
        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty email;
 
        private Person(String fName, String lName, String email) {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.email = new SimpleStringProperty(email);
        }
 
        public String getFirstName() {
            return firstName.get();
        }
 
        public void setFirstName(String fName) {
            firstName.set(fName);
        }
 
        public String getLastName() {
            return lastName.get();
        }
 
        public void setLastName(String fName) {
            lastName.set(fName);
        }
 
        public String getEmail() {
            return email.get();
        }
 
        public void setEmail(String fName) {
            email.set(fName);
        }
    }
} 

//public class Main extends Application { 
//   @Override 
//   public void start(Stage stage) {       
//      //Creating a Text object 
//      Text text = new Text(); 
//       
//      //Setting font to the text 
//      text.setFont(new Font(45)); 
//       
//      //setting the position of the text 
//      text.setX(50); 
//      text.setY(150);          
//      
//      //Setting the text to be added. 
//      text.setText("Welcome to Tutorialspoint"); 
//         
//      //Creating a Group object  
//      Group root = new Group(); 
//       
//      //Retrieving the observable list object 
//      ObservableList list = root.getChildren(); 
//       
//      //Setting the text object as a node to the group object 
//      list.add(text);       
//               
//      //Creating a scene object 
//      Scene scene = new Scene(root, 600, 300); 
//       
//      //Setting title to the Stage 
//      stage.setTitle("Sample Application"); 
//         
//      //Adding scene to the stage 
//      stage.setScene(scene); 
//         
//      //Displaying the contents of the stage 
//      stage.show(); 
//   }   
//   public static void main(String args[]){ 
//      launch(args); 
//   } 
//} 
