package com.rateMyDrink.modelClasses;

import java.io.Serializable;

/**
 * Created by Aaron on 2/17/2015.
 */
public enum LiquorType implements Serializable {

    RUM, //("Rum"),
    WHISKEY, //("Whiskey"),
    VODKA, //("Vodka"),
    CHOCOLATELIQUOR, //("Chocolate Liquor"),
    ABSINTHE, //("Absinthe"),
    BRANDY, //("Brandy"),
    GIN, //("Gin"),
    TEQUILA, //("Tequila"),
    BOURBON, //("Bourbon"),
    SCOTCH, //("Scotch"),
    COGNAC; //("Cognac");
    
    //Override toString method so that calls to this enum can return string values directly
    //This will be useful for custom display adapters which multiple ones may need these values.
    private String typeNames;

//    LiquorType(String names) {
//        this.typeNames = names;
//    }
//
//    @Override public String toString(){
//        return typeNames;
//    }
}

