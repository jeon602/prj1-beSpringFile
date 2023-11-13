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
    public ResponseEntity signup(@RequestBody Member member) {
        if (service.validate(member)) {
            if (service.add(member)) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.internalServerError().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(value = "check", params = "id")
    public ResponseEntity checkId(String id) {
        if (service.getId(id) == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().build();
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
    public List<Member> list() {
        return service.list();
    }

    @GetMapping
    public ResponseEntity<Member> view(String id) {
        // todo : 로그인 유무 : 안했으면 401
        // todo : 회원 본인의 계정 정보인가? 아니면 403

        Member member = service.getMember(id);

        return ResponseEntity.ok(member);
    }

    @DeleteMapping
    public ResponseEntity delete(String id) {
        // TODO : 로그인 했는지? 안 했으면, 401

        //TODO: 자신의 정보인지? 자신의 정보가 아니면 403

        if (service.deleteMember(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.internalServerError().build();
    }
    @PutMapping("edit")
    public ResponseEntity edit(@RequestBody Member member){
//       //로그인했는지? : 회원 본인의 계정 정보인가?

        if (service.update(member)){
            return  ResponseEntity.ok().build();
        }else{
            return ResponseEntity.internalServerError().build();
        }

    }
}