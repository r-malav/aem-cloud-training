package com.aem.cloud.training.core.models;

import com.aem.cloud.training.core.beans.Card;
import org.apache.commons.lang.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ListofCard {

    @ChildResource
    private Resource cards;

    private List<Card> cardList;

    @PostConstruct
    protected void init() {
        cardList = new ArrayList<>();
        if (cards != null) {
            cards.listChildren().forEachRemaining(item -> {
                ValueMap valueMap = item.getValueMap();
                String image = valueMap.get("cardimage", StringUtils.EMPTY);
                String fullname = valueMap.get("fullname", StringUtils.EMPTY);
                String discription = valueMap.get("discription", StringUtils.EMPTY);

                List<String>foodItemsList= new ArrayList<>();
                Resource foodItemResource =item.getChild("foodItems");
                  if(foodItemResource !=null)
                  {
                      foodItemResource.listChildren().forEachRemaining(child ->{
                          ValueMap childValueMap = child.getValueMap();
                        String foodItem = childValueMap.get("fooditem",StringUtils.EMPTY);
                          foodItemsList.add(foodItem);
                      });
                  }

                Card card = new Card(image,fullname,discription,foodItemsList);
                cardList.add(card);
            });
        }
    }

    public List<Card> getCardList() {
        return cardList;
    }
}
