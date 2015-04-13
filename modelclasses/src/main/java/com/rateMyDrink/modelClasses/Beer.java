package com.rateMyDrink.modelClasses;

/**
 * Created by shanembonner on 1/28/15.
 */
public class Beer extends Drink {
    private double ABV;
    private int calories;
    private BeerType beerType;

    public Beer(){

    }

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
            case LAGER:             return "LAGER";
            case IPA:               return "IPA";
            case SAISON:            return "SAISON";
            case IMPERIAL:          return "IMPERIAL";
            case STOUT:             return "STOUT";
            case ALE:               return "ALE";
            case PALEALE:           return "PALEALE";
            case LAMBIC:            return "LAMBIC";
            case BELGIAN:           return "BELGIAN";
            case HEFEWEIZEN:        return "HEFEWEIZEN";
            case PILSNER:           return "PILSNER";
            case KRISTALLWEIZEN:    return "KRISTALLWEIZEN";
            case HELLES:            return "HELLES";
            case MARZEN:            return "MARZEN";
            case PORTER:            return "PORTER";
            case MALT:              return "MALT";
            case WEISBIER:          return "WEISBIER";
            case CIDER:             return "CIDER";
            default:                return null;
        }
    }
    //Set beerType
    public void setBeerType(BeerType SBeerType) { this.beerType = SBeerType; }

    /**
     * sets a Beer's beerType via the string name of the BeerType.
     *
     * @param type the type of beer as a string
     */
    public void setBeerTypewithString(String type) {
        switch(type) {
            case "LAGER":             this.beerType = BeerType.LAGER;          return;
            case "IPA":               this.beerType = BeerType.IPA;            return;
            case "SAISON":            this.beerType = BeerType.SAISON;         return;
            case "IMPERIAL":          this.beerType = BeerType.IMPERIAL;       return;
            case "STOUT":             this.beerType = BeerType.STOUT;          return;
            case "ALE":               this.beerType = BeerType.ALE;            return;
            case "PALEALE":           this.beerType = BeerType.PALEALE;        return;
            case "LAMBIC":            this.beerType = BeerType.LAMBIC;         return;
            case "BELGIAN":           this.beerType = BeerType.BELGIAN;        return;
            case "HEFEWEIZEN":        this.beerType = BeerType.HEFEWEIZEN;     return;
            case "PILSNER":           this.beerType = BeerType.PILSNER;        return;
            case "KRISTALLWEIZEN":    this.beerType = BeerType.KRISTALLWEIZEN; return;
            case "HELLES":            this.beerType = BeerType.HELLES;         return;
            case "MARZEN":            this.beerType = BeerType.MARZEN;         return;
            case "PORTER":            this.beerType = BeerType.PORTER;         return;
            case "MALT":              this.beerType = BeerType.MALT;           return;
            case "WEISBIER":          this.beerType = BeerType.WEISBIER;       return;
            case "CIDER":             this.beerType = BeerType.CIDER;          return;
            default:                  this.beerType = null;
        }
    }

}
