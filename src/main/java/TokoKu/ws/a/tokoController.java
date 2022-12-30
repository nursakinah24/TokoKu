/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TokoKu.ws.a;

import TokoKu.ws.a.exceptions.NonexistentEntityException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Asus
 */
@RestController
//@ResponseBody
public class tokoController {
    Barang barang = new Barang();
    BarangJpaController barangCtrl = new BarangJpaController();
    
     
    @RequestMapping ("/getBarang")
    public List<Barang> getAll(){
    
        return barangCtrl.findBarangEntities();
    
    }
    
    @RequestMapping ("/delete/{id}")
    public String deleteBarang(@PathVariable("id") int id){
        try {
            barangCtrl.destroy(id);
            return id + " Dihapus.";
        }
        catch (NonexistentEntityException error){
            return "Barang dengan id: " + id + "tidak ditemukan.";
        }
    }
//    private static Map<String, Barang> barangRepo = new HashMap<>();
//    static {
//        Barang barang = new Barang();
//        
//        barangRepo.keySet();
//    }   
//    
//     @RequestMapping(value = "/barang")
//    //getProduct method
//    public ResponseEntity<Object> getBarang(){
//        //return response entity represent the HttpStatus
//        return new ResponseEntity<>(barangRepo.values(), HttpStatus.OK);
//    }
    
//    @RequestMapping ("/getBarang/{id}")
//    public String getBarang(@PathVariable("id") int id){
//        try{
//            barang = barangCtrl.findBarang(id);
//            return barang.getNama()+"<br>"+barang.getJumlah();
//        }
//        catch (Exception error) {
//            return "Barang Tidak Ditemukan.";
//        }
//    }
   
//
//     @RequestMapping ("/update/{id}")
//    public String updateBarang(@PathVariable("id") int id){
//        try {
//           barangCtrl.destroy(id);
//           barang.setId(id);
//            return id + " Dihapus.";
//        }
//        catch (NonexistentEntityException error){
//            return id + " Barang tidak ditemukan.";
//        }
//    }
}
