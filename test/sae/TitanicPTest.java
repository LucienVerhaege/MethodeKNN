package sae;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.jupiter.api.Test;

import enums.Gender;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import model.TitanicP;

class TitanicPTest {

	@Test
	void testCreateToString() {
		TitanicP t = new TitanicP(2,1,"Cumings, Mrs. John Bradley (Florence Briggs Thayer)",Gender.FEMALE,38,1,0,"PC 17599",71.2833,"C85",'C');
		
		assertEquals("Id : 2 | Passenger Class : 1\n"
				+ "Name : Cumings, Mrs. John Bradley (Florence Briggs Thayer) | Gender : FEMALE | Age : 38\n"
				+ "Sibling / Spouses : 1 | Parents Childrens : 0 | Ticket : PC 17599\n"
				+ "Fare : 71.2833 | Cabin : C85 | Embarked : C", t.toString());
		
		assertEquals(2, t.getId());
		assertEquals(1, t.getpClass());
		assertEquals("Cumings, Mrs. John Bradley (Florence Briggs Thayer)", t.getName());
		assertEquals(Gender.FEMALE, t.getGender());
		assertEquals(38, t.getAge());
		assertEquals(1, t.getSibSp());
		assertEquals(0, t.getParch());
		assertEquals("PC 17599", t.getTicket());
		assertEquals(71.2833, t.getFare());
		assertEquals("C85", t.getCabin());
		assertEquals('C', t.getEmbarked());
	}
	
	@Test
	void testCreateNull() {
		TitanicP t = new TitanicP();
		assertEquals(0, t.getId());
		assertEquals(0, t.getpClass());
		assertEquals(null, t.getName());
		assertEquals(null, t.getGender());
		assertEquals(0, t.getAge());
		assertEquals(0, t.getSibSp());
		assertEquals(0, t.getParch());
		assertEquals(null, t.getTicket());
		assertEquals(0.0, t.getFare());
		assertEquals(null, t.getCabin());
		assertEquals(0, t.getEmbarked());
		
		t.setId(2);
		assertEquals(2, t.getId());
		t.setpClass(1);
		assertEquals(1, t.getpClass());
		t.setName("Cumings, Mrs. John Bradley (Florence Briggs Thayer)");
		assertEquals("Cumings, Mrs. John Bradley (Florence Briggs Thayer)", t.getName());
		t.setGender(Gender.FEMALE);
		assertEquals(Gender.FEMALE, t.getGender());
		t.setAge(38);
		assertEquals(38, t.getAge());
		t.setSibSp(1);
		assertEquals(1, t.getSibSp());
		t.setParch(0);
		assertEquals(0, t.getParch());
		t.setTicket("PC 17599");
		assertEquals("PC 17599", t.getTicket());
		t.setFare(71.2833);
		assertEquals(71.2833, t.getFare());
		t.setCabin("C85");
		assertEquals("C85", t.getCabin());
		t.setEmbarked('C');
		assertEquals('C', t.getEmbarked());
	}
	
	@Test
	void testColumnValues() {
		TitanicP t = new TitanicP(2,1,"Cumings, Mrs. John Bradley (Florence Briggs Thayer)",Gender.FEMALE,38,1,0,"PC 17599",71.2833,"C85",'C');
		t.setSurvived(1);
		List<Object> list = t.columnsValue();
		assertEquals(1, list.get(0));
		assertEquals(Gender.FEMALE, list.get(1));
		assertEquals(38, list.get(2));
		assertEquals(1, list.get(3));
		assertEquals(0, list.get(4));
		assertEquals(1, list.get(5));
	}
	
	@Test
	void testConstructorTextField() {
		javafx.application.Platform.startup(()->{});
		List<TextField> texts = new ArrayList<>();
		texts.add(new TextField("2"));
		texts.add(new TextField("1"));
		texts.add(new TextField("Cumings, Mrs. John Bradley (Florence Briggs Thayer)"));
		texts.add(new TextField("38"));
		texts.add(new TextField("1"));
		texts.add(new TextField("0"));
		texts.add(new TextField("PC 17599"));
		texts.add(new TextField("71.2833"));
		texts.add(new TextField("C85"));
		texts.add(new TextField("C"));
		
		List<MenuButton> buttons = new ArrayList<>();
		buttons.add(new MenuButton("FEMALE"));
		try {
			TitanicP t = new TitanicP(texts, buttons);
			assertEquals(2, t.getId());
			assertEquals(1, t.getpClass());
			assertEquals("Cumings, Mrs. John Bradley (Florence Briggs Thayer)", t.getName());
			assertEquals(Gender.FEMALE, t.getGender());
			assertEquals(38, t.getAge());
			assertEquals(1, t.getSibSp());
			assertEquals(0, t.getParch());
			assertEquals("PC 17599", t.getTicket());
			assertEquals(71.2833, t.getFare());
			assertEquals("C85", t.getCabin());
			assertEquals('C', t.getEmbarked());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testConstructorTextFieldWithoutComma() {
		List<TextField> texts = new ArrayList<>();
		texts.add(new TextField("2"));
		texts.add(new TextField("1"));
		texts.add(new TextField("Cumings, Mrs. John Bradley (Florence Briggs Thayer)"));
		texts.add(new TextField("38"));
		texts.add(new TextField("1"));
		texts.add(new TextField("0"));
		texts.add(new TextField("PC 17599"));
		texts.add(new TextField("71"));
		texts.add(new TextField("C85"));
		texts.add(new TextField("C"));
		
		List<MenuButton> buttons = new ArrayList<>();
		buttons.add(new MenuButton("FEMALE"));
		try {
			TitanicP t = new TitanicP(texts, buttons);
			assertEquals(2, t.getId());
			assertEquals(1, t.getpClass());
			assertEquals("Cumings, Mrs. John Bradley (Florence Briggs Thayer)", t.getName());
			assertEquals(Gender.FEMALE, t.getGender());
			assertEquals(38, t.getAge());
			assertEquals(1, t.getSibSp());
			assertEquals(0, t.getParch());
			assertEquals("PC 17599", t.getTicket());
			assertEquals(71.0, t.getFare());
			assertEquals("C85", t.getCabin());
			assertEquals('C', t.getEmbarked());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@After
	void endSession() {
		javafx.application.Platform.exit();
	}

}
