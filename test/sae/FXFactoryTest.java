package sae;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.After;
import org.junit.jupiter.api.Test;

import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import utils.FXFactory;

public class FXFactoryTest {
	
	@Test
	void kButtonTester() {
		MenuButton k = FXFactory.generateKButton();
		assertNotEquals(null, k);
	}
	
	@Test
	void generateTypeButton() {
		MenuButton typeB = FXFactory.generateTypeButton();
		assertNotEquals(null, typeB);
	}
	
	@Test
	void testHiddenTextField() {
		TextField old = new TextField();
		TextField field = FXFactory.generateHintTextField(old, "ThisIsAHiddenMessage");
		assertEquals("ThisIsAHiddenMessage", field.getPromptText());
	}
	
	@Test
	void genderButtonTester() {
		javafx.application.Platform.startup(()->{});
		MenuButton genderButton = FXFactory.generateGenderButton();
		boolean genderSize = false;
		genderSize = !genderButton.getItems().isEmpty();
		assertTrue(genderSize);
	}
	
	@After
	void endSession() {
		javafx.application.Platform.exit();
	}
}
