package sae;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.jupiter.api.Test;

import javafx.scene.control.TextField;
import model.Iris;

class IrisTest {
	
	private static final String SETOSA = "Setosa";

	@Test
	void testCreateToString() {
		Iris i = new Iris(5.1,3.5,1.4,.2);
		
		assertEquals("Sepal Length : 5.1 | Sepal Width : 3.5\n" + "Petal Length : 1.4 | Petal Width : 0.2", i.toString());
		
		assertEquals(5.1, i.getsLength());
		assertEquals(3.5, i.getsWidth());
		assertEquals(1.4, i.getpLength());
		assertEquals(0.2, i.getpWidth());
		assertEquals(null, i.getVariety());
	}
	
	@Test
	void testCreateNull() {
		Iris i = new Iris();
		assertEquals(0.0, i.getsLength());
		assertEquals(0.0, i.getsWidth());
		assertEquals(0.0, i.getpLength());
		assertEquals(0.0, i.getpWidth());
		assertEquals(null, i.getVariety());
		
		i.setsLength(5.1);
		assertEquals(5.1, i.getsLength());
		i.setsWidth(3.5);
		assertEquals(3.5, i.getsWidth());
		i.setpLength(1.4);
		assertEquals(1.4, i.getpLength());
		i.setpWidth(0.2);
		assertEquals(0.2, i.getpWidth());
		i.setVariety(SETOSA);
		assertEquals(SETOSA, i.getVariety());
	}
	
	@Test
	void testColumnsValue() {
		Iris i = new Iris(5.1,3.5,1.4,.2);
		i.setVariety(SETOSA);
		List<Object> list = i.columnsValue();
		assertEquals(5.1, list.get(0));
		assertEquals(3.5, list.get(1));
		assertEquals(1.4, list.get(2));
		assertEquals(0.2, list.get(3));
		assertEquals(SETOSA, list.get(4));
	}
	
	@Test
	void testConstructorTextFields() {
		
		List<TextField> texts = new ArrayList<>();
		texts.add(new TextField("5.1"));
		texts.add(new TextField("3.5"));
		texts.add(new TextField("1.4"));
		texts.add(new TextField("0.2"));
		try {
			Iris i = new Iris(texts);
			assertEquals(5.1, i.getsLength());
			assertEquals(3.5, i.getsWidth());
			assertEquals(1.4, i.getpLength());
			assertEquals(0.2, i.getpWidth());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void textConstructorFields() {
		javafx.application.Platform.startup(()->{});
		List<TextField> texts = new ArrayList<>();
		texts.add(new TextField("5"));
		texts.add(new TextField("3"));
		texts.add(new TextField("1"));
		texts.add(new TextField("0"));
		try {
			Iris i = new Iris(texts);
			assertEquals(5.0, i.getsLength());
			assertEquals(3.0, i.getsWidth());
			assertEquals(1.0, i.getpLength());
			assertEquals(0.0, i.getpWidth());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@After
	void endSession() {
		javafx.application.Platform.exit();
	}
}
