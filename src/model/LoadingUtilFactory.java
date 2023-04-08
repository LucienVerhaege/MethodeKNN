package model;

import enums.Category;
import utils.GenericLoadingUtil;

public class LoadingUtilFactory {
    public static GenericLoadingUtil createLoadingUtil(Category cat){
        if(cat.equals(Category.POKEMON)){
            return new GenericLoadingUtil(Pokemon.class);
        } else if(cat.equals(Category.IRIS)){
            return new GenericLoadingUtil(Iris.class);
        } else {
            return new GenericLoadingUtil(TitanicP.class);
        }
    }
}
