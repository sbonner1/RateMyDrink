package Model;

import java.io.Serializable;

/**
 * Created by shanembonner on 2/5/15.
 */
public enum BeerType implements Serializable {
    LAGER, //("Lager"),
    IPA, //("IPA"),
    SAISON, //("Saison"),
    IMPERIAL, //("Imperial"),
    STOUT, //("Stout"),
    ALE, //("Ale"),
    PALEALE, //("Pale Ale"),
    LAMBIC, //("Lambic"),
    BELGIAN, //("Belgian"),
    HEFEWEIZEN, //("Hefeweizen"),
    PILSNER, //("Pilsner"),
    KRISTALLWEIZEN, //("Kristalweizen"),
    HELLES, //("Helles"),
    MARZEN, //("Marzen"),
    PORTER, //("Porter"),
    MALT, //("Malt"),
    WEISBIER, //("Weisbier"),
    CIDER; //("Cider");

    //Override toString method so that calls to this enum can return string values directly
    //This will be useful for custom display adapters which multiple ones may need these values.
    private String typeNames;

//    BeerType, //(String names) {
//        this.typeNames = names;
//    }

//    @Override public String toString, //(){
//        return typeNames;
//    }
}
