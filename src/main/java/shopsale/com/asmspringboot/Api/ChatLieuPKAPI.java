package shopsale.com.asmspringboot.Api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import shopsale.com.asmspringboot.Model.ChatLieuPhuKien;
import shopsale.com.asmspringboot.Reponsitory.ChatLieuPKReponsitory;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class ChatLieuPKAPI {

    @Autowired
    ChatLieuPKReponsitory chatLieuPKReponsitory;

    @GetMapping("/api/chatlieupk")
    public List<ChatLieuPhuKien> list() {
        return chatLieuPKReponsitory.findAll();
    }

    @GetMapping("/api/chatlieupk/{id}")
    public ChatLieuPhuKien getById(@PathVariable("id") int id) {
        return chatLieuPKReponsitory.findById(id).orElse(null);
    }

    @PostMapping(value = "/api/chatlieupk")
    public ChatLieuPhuKien insert(@RequestBody @Validated ChatLieuPhuKien chatlieu) {
        return chatLieuPKReponsitory.save(chatlieu);
    }

    @PutMapping(value = "/api/chatlieupk/{id}")
    public ChatLieuPhuKien update(@PathVariable("id") int id, @RequestBody ChatLieuPhuKien chatlieu) {
        return chatLieuPKReponsitory.save(chatlieu);
    }

    @DeleteMapping("/api/chatlieupk/{id}")
    public void delete(@PathVariable("id") int id) {
        chatLieuPKReponsitory.deleteById(id);
    }

}
