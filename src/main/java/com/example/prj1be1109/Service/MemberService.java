package com.example.prj1be1109.Service;


import com.example.prj1be1109.domain.Member;
import com.example.prj1be1109.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper mapper;

    public void add(Member member) {
        mapper.insert(member);
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

        if (member.getId().isBlank()) {
            return false;
        }
        return true;
    }
    public List<Member> list() {
        return mapper.selectAll();
    }
}
