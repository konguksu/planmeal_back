package gdg.swu.planmeal.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Builder
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member {
    @Id @Column(name = "member_id")
    @GeneratedValue
    private Long id;

    private String name;
    private int age;
    private int height;
    private String location;

    @CreatedDate
    private String joinedAt;
    @LastModifiedDate
    private String updatedAt;
}
