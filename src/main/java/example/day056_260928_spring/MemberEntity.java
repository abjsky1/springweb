package example.day056_260928_spring;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Table (name = "member")
@NoArgsConstructor 
@AllArgsConstructor 
@Data 
@Builder 
public class MemberEntity extends BaseTime {

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long mno; // 회원번호

    @Column (nullable = false, unique = true, length = 100)
    private String mid; // 회원아이디

    @Column(nullable = false)
    private String mpwd; // 회원비밀번호

    @Column(nullable = false, length = 30)
    private String mname; // 회원닉네임

    @Builder.Default
    @ColumnDefault ("'user'")
    @Column(nullable = false, length = 20)
    private String role = "user"; // 권한 (기본값: user)

} // class end
