package com.rateMyDrink.modelClasses;

/**
 * Created by shanembonner on 2/5/15.
 */
public class Liquor extends Drink {
    private float content;
    private LiquorType liquorType;
    private double ABV;

    /**
     * default empty constructor
     */
    public Liquor(){

    }

    /**
     * constuctor
     *
     * @param name name of the liquor
     * @param desc liquor's description
     * @param abv  liquor's alcohol-by-content volume
     * @param type the type of liquor
     */
    public Liquor(String name, String desc, double abv, LiquorType type){
        this.id = 0;
        this.rating = 0;
        this.drinkName = name;
        this.description = desc;
        this.ABV = abv;
        this.liquorType = type;
    }
    public void setId(int value){
        this.id = value;
    }

    public int getId(){
        return this.id;
    }

    public void setAlcoholContent(float content){
        this.content = content;
    }

    public float getAlcoholContent(){
        return this.content;
    }

    public LiquorType getLiquorType()
    {
        return liquorType;
    }
    /**
     * @return the specified liquor type
     */
    public String getLiquorTypeReadableType() {
        switch(this.liquorType)
        {
            case RUM:               return "RUM";
            case WHISKEY:           return "WHISKEY";
            case VODKA:             return "VODKA";
            case CHOCOLATELIQUOR:   return "CHOCOLATE LIQUOR";
            case ABSINTHE:          return "ABSINTHE";
            case BRANDY:            return "BRANDY";
            case GIN:               return "GIN";
            case TEQUILA:           return "TEQUILA";
            case BOURBON:           return "BOURBON";
            case SCOTCH:            return "SCOTCH";
            case COGNAC:            return "COGNAC";
            default:                return null;
        }
    }

    //Set liquorType
    public void setLiquorType(LiquorType SLiquorType) { this.liquorType = SLiquorType; }

    /**
     * set the Liquor object liquorType with the string name of the LiquorType
     *
     * @param type the string name of the LiquorType
     */
    public void setLiquorTypeWithString(String type){
        switch(type) {
            case "RUM":               this.liquorType = LiquorType.RUM;             return;
            case "WHISKEY":           this.liquorType = LiquorType.WHISKEY;         return;
            case "VODKA":             this.liquorType = LiquorType.VODKA;           return;
            case "CHOCOLATELIQUOR":   this.liquorType = LiquorType.CHOCOLATELIQUOR; return;
            case "ABSINTHE":          this.liquorType = LiquorType.ABSINTHE;        return;
            case "BRANDY":            this.liquorType = LiquorType.BRANDY;          return;
            case "GIN":               this.liquorType = LiquorType.GIN;             return;
            case "TEQUILA":           this.liquorType = LiquorType.TEQUILA;         return;
            case "BOURBON":           this.liquorType = LiquorType.BOURBON;         return;
            case "SCOTCH":            this.liquorType = LiquorType.SCOTCH;          return;
            case "COGNAC":            this.liquorType = LiquorType.COGNAC;          return;
            default:                  this.liquorType =  null;
        }
    }

}
