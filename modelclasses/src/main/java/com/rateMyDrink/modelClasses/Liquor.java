package com.rateMyDrink.modelClasses;

/**
 * Created by shanembonner on 2/5/15.
 */
public class Liquor extends Drink {
    private int id;
    private float content;
    private LiquorType liquorType;

    public Liquor(){

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
