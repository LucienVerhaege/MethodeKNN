package utils;

import enums.Gender;
import enums.Type;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

public class FXFactory {

	public static MenuButton generateKButton() {
		MenuButton kButton = new MenuButton("k");
		for(int i = 1; i < 10; i++) {
			MenuItem newItem = new MenuItem(""+i);
			newItem.setOnAction(e -> kButton.setText(newItem.getText()));
			kButton.getItems().add(newItem);
		}
		return kButton;
	}

	public static MenuButton generateGenderButton() {
		MenuButton genderButton = new MenuButton("Null");
		genderButton.setMinWidth(100);
		for(Gender t : Gender.values()) {
			MenuItem newItem = new MenuItem(t.getName());
			newItem.setOnAction(e -> genderButton.setText(newItem.getText()));
			genderButton.getItems().add(newItem);
		}

		return genderButton;
	}

	public static MenuButton generateTypeButton() {
		MenuButton typeButton = new MenuButton("Null");
		typeButton.setMinWidth(100);
		for(Type t : Type.values()) {
			MenuItem newItem = new MenuItem(t.getName());
			newItem.setOnAction(e -> typeButton.setText(newItem.getText()));
			typeButton.getItems().add(newItem);
		}

		return typeButton;
	}

	public static TextField generateHintTextField(TextField text, String s) {
		text.setPromptText(s);
		return text;
	}

}
