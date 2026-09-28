package example.day056_260928_spring;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
@Transactional 
public class MemberService {

    private final MemberRepository memberRepository;

//  비크립트(단방향 암호화 사용) 라이브러리 객체 주입
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//  [1] 회원가입 = 등록 = Create = C 
    public boolean signup(MemberDto memberDto){
    
    //  회원가입/등록 할 정보들(memberDto)을 컨트롤러에게 받아서 Entity 로 변환
        MemberEntity memberEntity = memberDto.toEntity();

    //  entity save
    //  *** [*] 저자ㅇ하기 전에 평문(원본 비밀번호) --> 암호문으로 변환
    //  passwordEncoder.encode("평문");
        String 암호문 = passwordEncoder.encode( memberDto.getMpwd() );
        
        memberEntity.setMpwd(암호문);

        MemberEntity savedEntity = memberRepository.save(memberEntity);

        if (savedEntity.getMno() >= 1) { return true; }
        return false;

    }

}
