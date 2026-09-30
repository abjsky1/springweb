package example.day058_260930_spring;

import java.util.Optional;

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

//  [2] 로그인 = 조회 = Read = R
    public MemberDto login( MemberDto memberDto ){
    //  컨트롤러에게 로그인시 입력받은 아이디/비밀번호 받기

    //  입력받은 아이디가 존재하는지 검증 , Repository 에 생성한 findByMid 이용
        MemberEntity memberEntity = memberRepository.findByMid(memberDto.getMid());

    //  아이디 존재하지 않으면 null 반환
        if(memberEntity == null){ return null; }

    //  존재하면 *** 평문(로그인시 입력받은 비밀번호) 과 암호문(회원가입시 입력받은 비밀번호) 비교 ***
        boolean 비밀번호일치여부 = passwordEncoder.matches(memberDto.getMpwd(), memberEntity.getMpwd());
        if (비밀번호일치여부 == false) { return null; }

    //  entity --> dto 변환하여 반환 
    //  주로 로그인전용 loginDto 가 있으면 좋음
        return MemberDto.from(memberEntity);


    }


//  [3] 내 정보 조회 (PK : 회원번호 조회)
    public MemberDto getMyInfo( Long mno ){
    //  컨트롤러에게 저회할 회원번호 받는다.

    //  findById
        Optional<MemberEntity> optional = memberRepository.findById(mno);

    //  조회 결과 존재하는지 확인
        if(optional.isPresent()){
        
        //  엔티티 꺼내기
            MemberEntity memberEntity = optional.get();

        //  dto 로 변환하여 반환
            return MemberDto.from(memberEntity);
        }
        
        return null;

    }













}
