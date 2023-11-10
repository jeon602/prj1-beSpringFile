package com.example.prj1be1109.Service;


import com.example.prj1be1109.domain.Member;
import com.example.prj1be1109.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper mapper;

    public void add(Member member) {
        mapper.insert(member);
    }
}