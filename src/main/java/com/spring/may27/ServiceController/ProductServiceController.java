package com.spring.may27.ServiceController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller Class
 * @author ashish
 */
@RestController
public class ProductServiceController {

    private static Map<String,Product> productMap=new HashMap<String, Product>();
    static {
        Product dryfruit=new Product();
        dryfruit.setName("Almond");
        dryfruit.setId("one");
        productMap.put("dryfruit",dryfruit);
        Product fruit=new Product();
        fruit.setName("Banana");
        fruit.setId("two");
        productMap.put("fruit",fruit);

    }

    @RequestMapping(value ="/product")
    public ResponseEntity getProducts(){
        return new ResponseEntity(productMap.values(), HttpStatus.OK);
    }

    @RequestMapping(value ="/addproduct",method = RequestMethod.POST)
    public ResponseEntity addProduct(@RequestBody Product product){
        productMap.put(product.getId(),product);
        return new ResponseEntity("Product is added successfully", HttpStatus.CREATED);
    }
    @RequestMapping(value ="/updateproduct/{id}",method = RequestMethod.PUT)
    public ResponseEntity updateProduct(@PathVariable("id")String id, @RequestBody Product product){
        productMap.remove(id);
        product.setId(id);
        productMap.put(id,product);
        return new ResponseEntity("Product is updated successfully", HttpStatus.OK);
    }

    @RequestMapping(value ="/deleteproduct/{id}",method = RequestMethod.DELETE)
    public ResponseEntity deleteProduct(@PathVariable("id")String id){
        productMap.remove(id);
        return new ResponseEntity("Product is deleted successfully", HttpStatus.OK);
    }


}
