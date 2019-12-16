package messages;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SetRequest {
    private final String key;
    private final String value;

    public SetRequest(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "SetRequest{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
