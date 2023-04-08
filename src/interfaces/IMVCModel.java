package interfaces;

import enums.Category;

public interface IMVCModel extends IDataSet{
	
	public void loadFromFile(String datafile);
	public void loadFromString(String data);
	public IColumn defaultXCol();
	public IColumn defaultYCol();
	public void switchCategory(Category classe);
	Category getCurrentCategory();

}
