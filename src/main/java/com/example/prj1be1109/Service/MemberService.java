package com.example.prj1be1109.Service;


import com.example.prj1be1109.domain.Member;
import com.example.prj1be1109.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper mapper;

    public boolean add(Member member) {
        mapper.insert(member);
        return false;
    }

    public String getId(String id) {
        return mapper.selectId(id);
    }

    public String getEmail(String email) {
        return mapper.selectEmail(email);
    }

    public boolean validate(Member member) {
        if (member == null) {
            return false;
        }

        if (member.getEmail().isBlank()) {
            return false;
        }

        if (member.getPassword().isBlank()) {
            return false;
        }

        return !member.getId().isBlank();
    }

    public List<Member> list() {
        return mapper.selectAll();
    }

    public Member getMember(String id) {

        return mapper.selectById(id);
    }

    public boolean deleteMember(String id) {
        return mapper.deleteById(id) == 1;
    }

    public boolean update(Member member) {

        return mapper.update(member) == 1;
    }
}
