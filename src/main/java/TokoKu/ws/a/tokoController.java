/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TokoKu.ws.a;

import TokoKu.ws.a.exceptions.NonexistentEntityException;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Asus
 */
@Controller
//@RequestMapping("/barang")
@ResponseBody
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
    
    @PostMapping ("/create")
    public String createBarang(@RequestBody Barang barangID){
        try {
            barangCtrl.create(barangID);
            return "Barang Ditambahkan.";
        }
        catch (Exception error){
            return "Barang dengan id tersebut telah ada.";
        }
    }
    
    @RequestMapping ("/update/{id}")
    public String updateBarang(@PathVariable("id") int id, @RequestBody Barang barangID) {
        try {
            barang = barangCtrl.findBarang(id);
            barangID.setId(id);
            Barang barangBaru = new Barang();
            
            if(barang.getId() != id){
                return "Barang dengan id tersebut tidak ditemukan.";
            } else if(barangID.getNama() == null) {
                barangID.setNama(barang.getNama());
                barangCtrl.edit(barangID);
                barangBaru = barangCtrl.findBarang(id);
                return "Barang :\n id : " + barang.getId() + "\nnama : " + barangBaru.getNama() + "\njumlah : " + barangBaru.getJumlah() + "\n\nTelah berhasil diperbaharui.";
            } else if (barangID.getJumlah() == null) {
                barangID.setJumlah(barang.getJumlah());
                barangCtrl.edit(barangID);
                barangBaru = barangCtrl.findBarang(id);
                return "Barang :\n id : " + barang.getId() + "\nnama : " + barangBaru.getNama() + "\njumlah : " + barangBaru.getJumlah() + "\n\nTelah berhasil diperbaharui.";
            } else if (barangID.getNama() != null && barangID.getJumlah() != null ){
                barangCtrl.edit(barangID);
                barangBaru = barangCtrl.findBarang(id);
                return "Barang :\nid : " + barang.getId() + "\nnama : " + barangBaru.getNama() + "\njumlah : " + barangBaru.getJumlah() + "\n\nTelah berhasil diperbaharui.";
            } else {
                return "error.";
            }
        }
        catch (Exception error){
            return "Barang dengan id tersebut tidak ada";
        }
    }
}
