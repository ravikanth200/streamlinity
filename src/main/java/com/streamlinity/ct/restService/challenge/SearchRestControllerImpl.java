package com.streamlinity.ct.restService.challenge;

import com.streamlinity.ct.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
 * This controller needs to expose the following rest endpoints.  You need to fill in the implementation here
 *
 * Required REST Endpoints
 *
 *      /item                       Get all items
 *      /item?category=C            Get all items in category specified by Category shortName
 *      /item/{itemShortName}       Get item that matches the specified Item shortName
 */

@Profile("default")
@RestController
public class SearchRestControllerImpl {

    @Autowired
    SearchSvcInterface searchSvc;

    @GetMapping("/item")
    public List<Item> getItems(@RequestParam(required = false) String category) {
        if(null == category || category.trim().isEmpty()) {
            return searchSvc.getItems();
        }
        return searchSvc.getItems(category.trim());
    }

    @GetMapping("/item/{itemShortName}")
    public List<Item> getItemsByShortName(@PathVariable String itemShortName) {
        return searchSvc.getItem(itemShortName);
    }
}
