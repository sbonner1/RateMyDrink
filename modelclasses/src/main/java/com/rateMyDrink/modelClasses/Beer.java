package com.rateMyDrink.modelClasses;

/**
 * Created by shanembonner on 1/28/15.
 */
public class Beer extends Drink {
    //private int id;
    private double ABV;
    private int calories;
    private BeerType beerType;


    /**
     * constuctor
     *
     * @param name name of the beer
     * @param desc beer's description
     * @param abv  beer's alcohol-by-content volume
     * @param calories beer's calories
     * @param type the type of beer
     */
    public Beer(String name, String desc, double abv, int calories, BeerType type){
        this.id = 0;
        this.rating = 0;
        this.drinkName = name;
        this.description = desc;
        this.ABV = abv;
        this.calories = calories;
        this.beerType = type;
    }

    //TODO: add more fields

    public void setABV (int abv){
        this.ABV = abv;
    }

    public double getABV(){
        return this.ABV;
    }

    public void setCalories(int calories){
        this.calories = calories;
    }

    public int getCalories() { return this.calories; }

    public BeerType getBeerType()
    {
        return beerType;
    }

    /**
     * @return the specified beer type
     */
    public String getBeerTypeReadableName()
    {
        switch(this.beerType)
        {
            case LAGER:             return "Lager";
            case IPA:               return "IPA";
            case SAISON:            return "Saison";
            case IMPERIAL:          return "Imperial";
            case STOUT:             return "Stout";
            case ALE:               return "Ale";
            case PALEALE:           return "Pale Ale";
            case LAMBIC:            return "Lambic";
            case BELGIAN:           return "Belgian";
            case HEFEWEIZEN:        return "Hefeweizen";
            case PILSNER:           return "Pilsner";
            case KRISTALLWEIZEN:    return "Kristall Weizen";
            case HELLES:            return "Helles";
            case MARZEN:            return "Marzen";
            case PORTER:            return "Porter";
            case MALT:              return "Malt";
            case WEISBIER:          return "Weisbier";
            case CIDER:             return "Cider";
            default:                return null;
        }
    }
    //Set beerType
    public void setBeerType(BeerType SBeerType) { this.beerType = SBeerType; }
}
