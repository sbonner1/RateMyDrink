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
            case RUM:               return "Rum";
            case WHISKEY:           return "Whiskey";
            case VODKA:             return "Vodka";
            case CHOCOLATELIQUOR:   return "Chocolate Liquor";
            case ABSINTHE:          return "Absinthe";
            case BRANDY:            return "Brandy";
            case GIN:               return "Gin";
            case TEQUILA:           return "Tequila";
            case BOURBON:           return "Bourbon";
            case SCOTCH:            return "Scotch";
            case COGNAC:            return "Cognac";
            default:                return null;
        }
    }

    //Set liquorType
    public void setLiquorType(LiquorType SLiquorType) { this.liquorType = SLiquorType; }

}
