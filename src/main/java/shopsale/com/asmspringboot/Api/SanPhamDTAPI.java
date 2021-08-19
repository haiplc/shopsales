package shopsale.com.asmspringboot.Api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import shopsale.com.asmspringboot.Model.Property;
import shopsale.com.asmspringboot.Model.ProppertyModel;
import shopsale.com.asmspringboot.Model.SanPhamDT;
import shopsale.com.asmspringboot.Model.SearchForm;
import shopsale.com.asmspringboot.Reponsitory.BoNhoDTReponsitory;
import shopsale.com.asmspringboot.Reponsitory.ChipDTReponsitory;
import shopsale.com.asmspringboot.Reponsitory.HangDTReponsitory;
import shopsale.com.asmspringboot.Reponsitory.LoaiDTReponsitory;
import shopsale.com.asmspringboot.Reponsitory.ManHinhDTReponsitory;
import shopsale.com.asmspringboot.Reponsitory.MauSacDTReponsitory;
import shopsale.com.asmspringboot.Reponsitory.RamDTReponsitory;
import shopsale.com.asmspringboot.Reponsitory.SanPhamDTReponsitory;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class SanPhamDTAPI {

    @Autowired
    SanPhamDTReponsitory sanPhamDTReponsitory;

    @GetMapping("/api/sanphamdt")
    public List<SanPhamDT> list() {
        return sanPhamDTReponsitory.findAll();
    }

    @GetMapping("/api/sanphamdt/{id}")
    public SanPhamDT getById(@PathVariable("id") int id) {
        return sanPhamDTReponsitory.findById(id).orElse(null);
    }

    @PostMapping(value = "/api/sanphamdt")
    public HashMap<String, Object> insert(@RequestBody @Validated SanPhamDT dienthoai, BindingResult result) {

        HashMap<String, Object> ResponseData = new HashMap<>();
        ResponseData.put("status", true);

        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();

            HashMap<String, String> ListValid = new HashMap<>();

            for (FieldError error : fieldErrors) {
                ListValid.put(error.getField(), error.getDefaultMessage());
            }

            ResponseData.put("status", false);
            ResponseData.put("data", ListValid);

            return ResponseData;
        }
        ResponseData.put("data", sanPhamDTReponsitory.save(dienthoai));

        return ResponseData;
    }

    @PutMapping(value = "/api/sanphamdt/{id}")
    public SanPhamDT update(@Validated @PathVariable("id") int id, @RequestBody SanPhamDT dienthoai) {
        return sanPhamDTReponsitory.save(dienthoai);
    }

    @DeleteMapping("/api/sanphamdt/{id}")
    public void delete(@PathVariable("id") int id) {
        sanPhamDTReponsitory.deleteById(id);
    }

    // số sản phẩm tối đa
    private static final int TOI_DA_SAN_PHAM = 4;

    // list danh sách và map danh sách ra trang chính
    @PostMapping("/api/sanphamdt/search")
    public Page<SanPhamDT> search(@RequestBody SearchForm sf) {
        Pageable phanTrang = PageRequest.of(sf.getTrang(), TOI_DA_SAN_PHAM,
                sf.getThuTu() ? Direction.ASC : Direction.DESC, sf.getXepTheo());

        Page<SanPhamDT> sanphamPage = sanPhamDTReponsitory.findByNameContaining(sf.getTen(), phanTrang);

        return sanphamPage;
    }

    @Autowired
    BoNhoDTReponsitory boNhoDTReponsitory;
    @Autowired
    HangDTReponsitory hangDTReponsitory;
    @Autowired
    MauSacDTReponsitory mauSacDTReponsitory;
    @Autowired
    ChipDTReponsitory chipDTReponsitory;
    @Autowired
    RamDTReponsitory ramDTReponsitory;
    @Autowired
    ManHinhDTReponsitory manHinhDTReponsitory;
    @Autowired
    LoaiDTReponsitory loaiDTReponsitory;

    @GetMapping("/api/sanphamdt/allproperties")
    public List<Object> getListProperties() {
        List<Object> returnList = new ArrayList<>();
        List<Property> boNhos = boNhoDTReponsitory.findAll().stream()
                .map((q) -> new Property(q.getBonho_id(), q.getBonho_name())).collect(Collectors.toList());

        List<Property> hangdt = hangDTReponsitory.findAll().stream()
                .map((q) -> new Property(q.getHang_id(), q.getHang_name())).collect(Collectors.toList());

        List<Property> loaidt = loaiDTReponsitory.findAll().stream()
                .map((q) -> new Property(q.getLoai_id(), q.getLoai_name())).collect(Collectors.toList());
        List<Property> chipdt = chipDTReponsitory.findAll().stream()
                .map((q) -> new Property(q.getChip_id(), q.getChip_name())).collect(Collectors.toList());

        List<Property> ramdt = ramDTReponsitory.findAll().stream()
                .map((q) -> new Property(q.getRam_id(), q.getRam_name())).collect(Collectors.toList());

        List<Property> manhinh = manHinhDTReponsitory.findAll().stream()
                .map((q) -> new Property(q.getManhinh_id(), q.getManhinh_name())).collect(Collectors.toList());

        List<Property> mausac = mauSacDTReponsitory.findAll().stream()
                .map((q) -> new Property(q.getMausac_id(), q.getMausac_name())).collect(Collectors.toList());

        returnList.add(new ProppertyModel("Bộ nhớ", "bonho.bonho_id", boNhos));
        returnList.add(new ProppertyModel("Hãng điện thoại", "hang.hang_id", hangdt));
        returnList.add(new ProppertyModel("Loại sản phẩm", "loai.loai_id", loaidt));
        returnList.add(new ProppertyModel("Chip sản phẩm", "chip.chip_id", chipdt));
        returnList.add(new ProppertyModel("Ram sản phẩm", "ram.ram_id", ramdt));
        returnList.add(new ProppertyModel("Màn hình", "manhinh.manhinh_id", manhinh));
        returnList.add(new ProppertyModel("Màu sắc", "mausac.mausac_id", mausac));

        return returnList;
    }

}
