package utils;

import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

import interfaces.IEntity;


public class GenericLoadingUtil {

	private CsvToBean<IEntity> beans;
	private List<IEntity> data;
	
	//Precise T in parameters with T.class, in pokemon's case : Pokemon.class
	public GenericLoadingUtil(String url, Class c) throws IOException {
		beans = new CsvToBeanBuilder<IEntity>(new FileReader(url))
                .withSeparator(',')
                .withThrowExceptions(false)
                .withType(c)
                .build();
		data = beans.parse();

	    
	}
	
	public GenericLoadingUtil(Class c){
	}
	
	public void loadData(String url, Class c) throws IOException{
		beans = new CsvToBeanBuilder<IEntity>(new FileReader(url))
                .withSeparator(',')
                .withThrowExceptions(false)
                .withType(c)
                .build();
		data = beans.parse();
	}
	
	public void loadDataFromString(String dataL, Class c) {
		beans = new CsvToBeanBuilder<IEntity>(new StringReader(dataL))
                .withSeparator(',')
                .withThrowExceptions(false)
                .withType(c)
                .build();
		data = beans.parse();
	}
	
	public List<IEntity> getData() {
		return this.data;
	}
	
	public List<CsvException> getErrors(){
		return this.beans.getCapturedExceptions();
	}
	
	public List<String> getErrorDisplay(){
		ArrayList<String> errors = new ArrayList<>();
		beans.getCapturedExceptions().stream().forEach(exception -> 
	        errors.add(exception.getLineNumber() + " -> " + exception.getMessage() + " -> " + exception.getClass())
	    );
		return errors;
	}
	
	public int getLinesN() {
		return this.data.size();
	}
	
}
