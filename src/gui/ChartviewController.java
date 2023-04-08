package gui;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import enums.Category;
import interfaces.IColumn;
import interfaces.IEntity;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.Iris;
import model.MVCModel;
import model.Pokemon;
import model.TitanicP;
import utils.FXFactory;
import utils.Observer;
import utils.Subject;

public class ChartviewController implements Observer {

	private static final String ERROR_WHILE_PARSING = "Error while parsing the given data, please try again. ";
	private static final String URLP = "res/pokemon_train.csv";
	private static final String URLI = "res/iris.csv";
	private static final String URLT = "res/titanic.csv";
	private static String[] choices = {"Pokemon","Iris","Titanic"};
	private static String[] knnPkmCol = {"name", "attack", "base_egg_steps", "capture_rate", "defense", "experience_growth",
			"hp", "sp_attack", "sp_defense", "type1", "type2", "speed"};
	private static String[] knnIrisCol = {"sLength", "sWidth", "pLength", "pWidth"};
	private static String[] knnTitanicCol = {"id", "pClass", "name", "gender", "age", "sibSp", "parch", "ticket",
			"fare", "cabin", "embarked"};
	
	
	protected HashMap<Entry<Double, Double>, IEntity> displayedEntities;
	protected HashMap<String, XYChart.Series> data;
	protected HashMap<String, IEntity> dataComputed;
	
	protected MVCModel model;
	
	@FXML
	protected MenuButton axisx;
	
	@FXML
	protected MenuButton axisy;
	
	@FXML
	protected ScatterChart<?, ?> chart;

	@FXML
	protected HBox hbox;

	@FXML
	protected MenuBar menuBar;
	
	@FXML
	protected Menu menuButton;
	protected MenuItem currentItem;

	@FXML
	protected VBox vbox;
	
	@FXML
	protected VBox vbox2;
	
	@FXML
	protected Button knnCaller;
	
	protected Stage knnStage;

	@FXML
	protected Button robustnessCompute;
	
	protected Stage robustnessStage;
	
	protected Stage entities;
	
	protected Label errorText;
	
	protected TextField tf;
	
	protected List<TextField> texts;
	
	protected List<MenuButton> buttons;
	
	@FXML
	protected Button viewEntity;
	
	protected IEntity entity;
	
	protected Label robustness;
	
	protected String lastResult;
	
	protected Label entityId;
	
	protected MenuButton kBt;
	
	protected MenuItem it;
	
	protected String oldStyle;
	
	protected Stage hoveredEntity;
	
	public void initialModel() {
		if(this.model == null) {
			this.model = new MVCModel(Category.POKEMON);
			model.loadFromFile(URLP);
			this.model.attach(this);
			this.dataComputed = new HashMap<>();
		}
	}
	
	public void initializeAll() {
		
		initialModel();
		
		initializeMenuButton();
		
		initializeAxisMenuButtons();
		
		initializeKnnButton();
		
		initializeRobustnessButton();
		
		initializeEntityButton();
		
		setAxis();
		
		displayData();
		
		addCanBeSelected();
	
	}
	
	//
	//
	//
	//	BELOW METHODS ARE PRIVATE METHODS AND CONFIGURATION METHODS
	//
	//
	//
	
	private void addCanBeSelected() {
		for(XYChart.Series<?, ?> series : chart.getData()) {
			for(XYChart.Data<?, ?> serie : series.getData()) {
			serie.getNode().setOnMouseEntered(e -> {
				oldStyle = serie.getNode().getStyle();
				serie.getNode().setStyle("-fx-background-color: blue");
				try {
					displayPoint(serie.getXValue(), serie.getYValue());
				} catch (Exception e1) {
					System.out.println("Please wait longer before choosing a Point to display : " + e1.getMessage());
				}
			});
			
			serie.getNode().setOnMouseExited(e -> {
				serie.getNode().setStyle(oldStyle);
				try {
					this.hoveredEntity.close();
				} catch (Exception e1) {
					System.out.println("Leaving the point causing an issue : " + e1.getMessage());
				}
			});
			}
		}
	}

	private void displayPoint(Object xValue, Object yValue) {
		IEntity ent = this.displayedEntities.get(Map.entry((Double) xValue, (Double) yValue));
		Label label = new Label(ent.toString());
		Scene scene = new Scene(label);
		this.hoveredEntity = new Stage();
		Rectangle2D screenBounds = Screen.getPrimary().getBounds();
		this.hoveredEntity.setX((screenBounds.getMaxX()/2)-700);
		this.hoveredEntity.setY((screenBounds.getMaxY()/2-200));
		
		this.hoveredEntity.setScene(scene);
		this.hoveredEntity.setTitle("Current Entity");
		this.hoveredEntity.show();
	}

	private void initializeEntityButton() {
		this.viewEntity.setPrefWidth(200);
		this.viewEntity.setOnAction(e ->{
			this.entityId = new Label();
			entities = createEntitiesStage();
			this.entities.show();
		});
	}

	private Stage createEntitiesStage() {
		VBox verticalLayout = new VBox();
		verticalLayout.setPrefWidth(600);
		verticalLayout.setPrefHeight(200);
		
		MenuButton mbt = new MenuButton("Entities");
		this.entityId.setMinWidth(600);
		mbt.setMinWidth(600);
		
		for(IEntity e : model.getData()) {
			it = new MenuItem();
			it.setText(e.toString());
			it.setOnAction(ex -> this.entityId.setText(it.getText()));
			mbt.getItems().add(it);
		}
		verticalLayout.getChildren().addAll(this.entityId, mbt);
		
		Scene scene = new Scene(verticalLayout);
		Stage stage = new Stage();
		
		stage.setScene(scene);
		stage.setTitle("Entities");
		return stage;
	}

	private void initializeRobustnessButton() {
		this.robustnessCompute.setPrefWidth(200);
		this.robustnessCompute.setOnAction( e -> {
			robustnessStage = createRobustStage();
			this.robustnessStage.show();
		});
	}

	private Stage createRobustStage() {
		HBox horizontalLayout = new HBox();
		horizontalLayout.setPrefWidth(400);
		
		robustness = new Label();
		robustness.setMinWidth(100);
		
		tf = new TextField();
		tf = FXFactory.generateHintTextField(tf, "url pointing the test.file");
		tf.setMinWidth(150);
		
		Button bt = new Button("Compute using URL");
		bt.setMinWidth(100);
		
		kBt = new MenuButton("k");
		kBt = FXFactory.generateKButton();
		
		horizontalLayout.setCenterShape(true);
		horizontalLayout.getChildren().add(robustness);
		horizontalLayout.getChildren().addAll(tf ,kBt);
		horizontalLayout.getChildren().add(bt);
		
		bt.setOnAction(e ->{
			try {
				double robust = this.model.computeRobustness(tf.getText(), Integer.parseInt(kBt.getText()));
				this.robustness.setText(""+robust+"%.");
			} catch(Exception ex) {
				this.robustness.setText(ERROR_WHILE_PARSING + ex.getLocalizedMessage());
			}
		});
		Scene scene = new Scene(horizontalLayout);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Robustness Computing");
		return stage;
	}

	private void initializeKnnButton() {
		this.knnCaller.setPrefWidth(200);
		this.knnCaller.setOnAction( e -> {
			knnStage = createKnnStage();
			this.knnStage.show();
		});
	}

	private Stage createKnnStage() {
		this.texts = new ArrayList<>();
		this.buttons = new ArrayList<>();
		
		errorText = new Label();
		VBox verticalLayout = new VBox();
		HBox horizontalLayout = new HBox();
		texts = createHintTexts();
		
		verticalLayout.getChildren().add(this.errorText);
		horizontalLayout.setPrefWidth(800);
		horizontalLayout.getChildren().addAll(texts);
		buttons.add(FXFactory.generateKButton());
		horizontalLayout.getChildren().addAll(buttons);
		
		Button submit = new Button("Submit");
		submit.setOnAction(e -> {
			this.entity = createNewEntityFromFields();
			if(entity != null) {
				try {
					this.model.useKnn(Integer.parseInt(buttons.get(buttons.size()-1).getText()), entity);					
					this.errorText.setText("Le résultat est " + this.lastResult + ", il a été placé sur le graphe.");
				} catch(Exception ex) {
					this.errorText.setText(ERROR_WHILE_PARSING + ex.getLocalizedMessage());
				}
			}
		});
		
		verticalLayout.getChildren().add(horizontalLayout);
		verticalLayout.getChildren().add(submit);
		Scene scene = new Scene(verticalLayout);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("KNN Input");
		return stage;
	}
	
	private IEntity createNewEntityFromFields() {
		IEntity entit = null;
		try {
			switch(model.getCurrentCategory()) {
				case POKEMON:
					entit = new Pokemon(texts, buttons);
					break;
				case IRIS:
					entit = new Iris(texts);
					break;
				case TITANICP:
					entit = new TitanicP(texts, buttons);
					break;
			}
		} catch(Exception e) {
			this.errorText.setText(ERROR_WHILE_PARSING + e.getLocalizedMessage());
			return null;
		}
			
		return entit;
	}

	private List<TextField> createHintTexts(){
		TextField text;
		switch(this.model.getCurrentCategory()) {
			case POKEMON:
				for(int i = 0; i < knnPkmCol.length; i++) {
					text = new TextField();
					handlePkmTextField(i, text);
				}
				break;
			case IRIS:
				for(String s : knnIrisCol) {
					text = new TextField();
					FXFactory.generateHintTextField(text, s);
					texts.add(text);
				}
				break;
			case TITANICP:
				for(int i = 0; i < knnTitanicCol.length; i++) {
					text = new TextField();
					handleTitanicTextField(i, text);
				}
				break;
		}
		return texts;
	}
	
	private void handleTitanicTextField(int i, TextField text) {
		if(i == 3) {
			this.buttons.add(FXFactory.generateGenderButton());
		} else {
			texts.add(FXFactory.generateHintTextField(text, knnTitanicCol[i]));
		}
	}

	private void handlePkmTextField(int i, TextField text) {
		if(i == 9 || i == 10) {
			this.buttons.add(FXFactory.generateTypeButton());
		} else {
			texts.add(FXFactory.generateHintTextField(text, knnPkmCol[i]));			
		}
	}



	private void setAxis() {
		changeAxis(true, model.defaultXCol().getName());
		changeAxis(false, model.defaultYCol().getName());
	}

	private void initializeAxisMenuButtons() {
		this.axisx.getItems().clear();
		this.axisy.getItems().clear();
		this.axisx.setText(model.defaultXCol().getName());
		this.axisy.setText(model.defaultYCol().getName());
		IColumn e;
		for(int i = 0; i < model.getColumns().size()-1; i++) {
			e = model.getColumns().get(i);
			if(!e.equals(model.defaultXCol()) && !e.equals(model.defaultYCol())) {
				MenuItem category = new MenuItem(e.getName());
				MenuItem category2 = new MenuItem(e.getName());
				this.axisx.getItems().add(handleAxisXButton(category));
				this.axisy.getItems().add(handleAxisYButton(category2));
			}
		}
	}
	
	private void changeAxis(boolean isX, String name) {
		NumberAxis axis;
		if(isX) {
			axis=(NumberAxis) chart.getXAxis();
		} else {
			axis=(NumberAxis) chart.getYAxis();
		}
		axis.setAutoRanging(false);
		//Avoid Issues Due to the High numbered values
		double maxAxis = model.computeMaxAxis(name);
		double minAxis = model.computeMinAxis(name);
		if(maxAxis > 10000 || minAxis > 10000) {
			axis.setTickUnit(0);
		} else {
			axis.setTickUnit(5);
		}
		axis.setLowerBound(minAxis);
		axis.setUpperBound(maxAxis);
	}
	
	
	private MenuItem handleAxisXButton(MenuItem axisX) {
		axisX.setOnAction(e ->{
			model.setDefaultXCol(axisX.getText());
			initializeAll();
		});
		return axisX;
	}
	
	private MenuItem handleAxisYButton(MenuItem axisY) {
		axisY.setOnAction(e ->{
			model.setDefaultYCol(axisY.getText());
			initializeAll();
		});
		return axisY;
	}
	
	private void displayData() {
		this.data = new HashMap<>();
		this.displayedEntities = new HashMap<>();
		double xVal;
		double yVal;
		String classV;
		XYChart.Series<Double, Double> serie = new XYChart.Series();
		
		for(Entry<String, IEntity> ent : this.dataComputed.entrySet()) {
			xVal = model.getNormalizedXValue(ent.getValue());
			yVal = model.getNormalizedYValue(ent.getValue());
			this.displayedEntities.put(Map.entry(xVal, yVal), ent.getValue());
			classV = ent.getKey();
			addData(serie, classV, xVal, yVal);
		}
		
		for(IEntity e : model.getData()) {
			xVal = model.getNormalizedXValue(e);
			yVal = model.getNormalizedYValue(e);
			
			this.displayedEntities.put(Map.entry(xVal, yVal), e);
			
			classV = model.returnDataClass(e);
			
			addData(serie, classV, xVal, yVal);
		}
		
		drawData();
	}

	private void addData(XYChart.Series<Double, Double> serie, String classV, double xVal, double yVal) {
		if(!data.containsKey(classV)) {
			serie = new XYChart.Series();
			serie.setName(classV);
			serie.getData().add(new XYChart.Data(xVal, yVal));
			data.put(classV, serie);
		} else {
			data.get(classV).getData().add(new XYChart.Data(xVal, yVal));
		}
	}
	
	private void drawData() {
		this.chart.getData().clear();
		for(Entry<String, XYChart.Series> serieSet : this.data.entrySet()) {
			this.chart.getData().add(serieSet.getValue());
		}
	}
	
	private void initializeMenuButton() {
		this.menuButton.getItems().clear();
		for(String modl : ChartviewController.choices) {
			if(!modl.equals(this.menuButton.getText())) {
				MenuItem menu = new MenuItem(modl);
				this.menuButton.getItems().add(handleMenuButton(menu));
			}
		}
	}
	
	private MenuItem handleMenuButton(MenuItem menuBut) {
		menuBut.setOnAction(e -> {
			String wantedCategory = menuBut.getText();
			if (wantedCategory.equals(ChartviewController.choices[0])) {
				model.switchCategory(Category.POKEMON);
				model.loadFromFile(URLP);
				this.menuButton.setText("Modele Pokemon");
			} else if (wantedCategory.equals(ChartviewController.choices[1])) {
				model.switchCategory(Category.IRIS);
				model.loadFromFile(URLI);
				this.menuButton.setText("Modele Iris");
			} else {
				model.switchCategory(Category.TITANICP);
				model.loadFromFile(URLT);
				this.menuButton.setText("Modele Titanic");
			}
			this.dataComputed = new HashMap<>();
			this.initializeAll();
		});
		return menuBut;
	}

	@Override
	public void update(Subject subj) {
		//METHOD EMPTY DUE TO THE LACK OF USAGE
	}

	@Override
	public void update(Subject subj, Object data) {
		Entry<String, IEntity> entit = (Entry<String, IEntity>) data;
		this.lastResult = entit.getKey();
		this.dataComputed.put("NCC:"+entit.getKey(), entit.getValue());
		initializeAll();
	}
}
