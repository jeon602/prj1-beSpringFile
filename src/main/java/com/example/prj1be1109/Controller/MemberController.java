package com.example.prj1be1109.Controller;

import com.example.prj1be1109.domain.Member;
import com.example.prj1be1109.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService service;

    @PostMapping("signup")
    public void signup(@RequestBody Member member) {
        service.add(member);
    }
    @GetMapping(value = "Check", params = "id")
    public ResponseEntity checkId(String id){
        if (service.getId(id) == null){
            return ResponseEntity.notFound().build();
        }else {return ResponseEntity.ok().build();
        }
    }
    @GetMapping(value = "check", params = "email")
    public ResponseEntity checkEmail(String email) {
        if (service.getEmail(email) == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }
    @GetMapping("list")
    public List<Member> list(){
        return service.list();
    }
    @GetMapping
    public void view(String id){
        System.out.println("id = " +  id );
    }

}
