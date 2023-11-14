package com.example.prj1be1109.Controller;

import com.example.prj1be1109.domain.Member;
import com.example.prj1be1109.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

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

    @GetMapping(value = "check", params = "nickName")
    public ResponseEntity checkNickName(String nickName) {
        if (service.getNickName(nickName) == null) {
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
        // TODO : 로그인 했는 지? -> 안했으면 401
        // TODO : 자기 정보인지? -> 아니면 403

        Member member = service.getMember(id);

        return ResponseEntity.ok(member);
    }

    @DeleteMapping
    public ResponseEntity delete(String id) {
        // TODO : 로그인 했는 지? -> 안했으면 401
        // TODO : 자기 정보인지? -> 아니면 403

        if (service.deleteMember(id)) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.internalServerError().build();

    }

    @PutMapping("edit")
    public ResponseEntity edit(@RequestBody Member member) {
        // TODO: 로그인 했는지? 자기정보인지?

        if (service.update(member)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

    //403 forbidden : (클라이언트는 누군지 아는데, )로그인을 했는데, 권한이 없는 것. 401은 로그인에 실패
    @PostMapping("login")
    public ResponseEntity login(@RequestBody Member member, WebRequest request) {

        if (service.login(member, request)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}