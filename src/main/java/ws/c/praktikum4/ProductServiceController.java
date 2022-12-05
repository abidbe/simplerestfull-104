/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.c.praktikum4;


import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ws.c.praktikum4.model.Product;

/**
 *
 * @author asus
 */
@RestController
public class ProductServiceController {
private static Map<String, Product> productRepo = new HashMap<>();
   static {
      Product honey = new Product();
      honey.setId("1");
      honey.setName("Honey");
      productRepo.put(honey.getId(), honey);
      
      Product almond = new Product();
      almond.setId("2");
      almond.setName("Almond");
      productRepo.put(almond.getId(), almond);
      
      Product susu = new Product();
      susu.setId("3");
      susu.setName("MILK");
      productRepo.put(susu.getId(), susu);
      
      Product sayur = new Product();
      sayur.setId("4");
      sayur.setName("Retri Sawi");
      productRepo.put(sayur.getId(), sayur);
      
   }
   
   @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
   public ResponseEntity<Object> delete(@PathVariable("id") String id, @RequestBody Product product) { 
      productRepo.remove(id);
      return new ResponseEntity<>("produk berhasil dihapus", HttpStatus.OK);
   }
    
    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product){
        
        if(!productRepo.containsKey(id)){
            return new ResponseEntity<>("produk tidak ditemukan, silahkan cek", HttpStatus.NOT_FOUND);
        }
        else{
            productRepo.remove(id);
            product.setId(id);
            productRepo.put(id, product);
            return new  ResponseEntity<>("produk berhasil perbarui",HttpStatus.OK);
        } 
    }
    
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<Object> createProduct(@RequestBody Product product){
        
        if(productRepo.containsKey(product.getId())){
            return new ResponseEntity<>("gagal ditambahkan, produk id tidak boleh sama ", HttpStatus.OK);
        }
        else{
            productRepo.put(product.getId(), product);
            return new ResponseEntity<>("produk berhasil ditambahkan", HttpStatus.CREATED);
        }
    }
   
   @RequestMapping(value = "/products")
   public ResponseEntity<Object> getProduct() {
      return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
   }
    
}
        






