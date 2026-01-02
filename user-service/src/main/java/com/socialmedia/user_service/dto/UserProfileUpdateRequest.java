package  com.socialmedia.user_service.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileUpdateRequest {

    @Size(max = 100)
    private String name;

    @Size(max = 255)
    private String bio;

    private String profileImage;
    private String coverImage;
}
