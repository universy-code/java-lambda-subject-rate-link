package app.universy.course.rate.model.institution.tag;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;

@DynamoDBDocument
public class TagRate {

    @DynamoDBAttribute
    @DynamoDBTypeConvertedEnum
    private Tag tag;

    @DynamoDBAttribute
    private int rates;

    public TagRate() {
    }

    public TagRate(Tag tag, int rates) {
        this.tag = tag;
        this.rates = rates;
    }

    public void increment() {
        this.rates++;
    }

    public void decrement() {
        this.rates--;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public int getRates() {
        return rates;
    }

    public void setRates(int rates) {
        this.rates = rates;
    }
}
