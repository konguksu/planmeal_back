package gdg.swu.planmeal.config.oauth2;

import lombok.Data;

@Data
public class RegisterDto {
    private String name;
    private int age;
    private int height;
    private int weight;
    private String location;
}
