package com.streamlinity.ct.restService.challenge;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.streamlinity.ct.model.Item;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
 * Provide your implementation of the SearchSvcImpl here.
 * Also annotate your methods with Rest end point wrappers as required in the problem statement
 *
 * You can create any auxiliary classes or interfaces in this package if you need them.
 *
 * Do NOT add annotations as a Bean or Component or Service.   This is being handled in the custom Config class
 * PriceAdjustConfiguration
 */

public class SearchSvcImpl implements SearchSvcInterface {

    private List<Item> items;

    @Override
    public void init(String itemPriceJsonFileName) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            items = Arrays.asList(mapper.readValue(new File(itemPriceJsonFileName), Item[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init(File itemPriceJsonFile) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            items = Arrays.asList(mapper.readValue(itemPriceJsonFile, Item[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Item> getItems() {
        return items;
    }

    @Override
    public List<Item> getItems(String category) {
        return items.stream()
                .filter(i -> i.getCategory_short_name().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    @Override
    public List<Item> getItem(String itemShortName) {
        return items.stream()
                .filter(i -> i.getShort_name().equalsIgnoreCase(itemShortName))
                .collect(Collectors.toList());
    }
}
