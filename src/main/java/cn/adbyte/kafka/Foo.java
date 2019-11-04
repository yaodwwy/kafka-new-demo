package cn.adbyte.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Adam
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Foo implements Serializable {

    private static final long serialVersionUID = 6746751709692964455L;

    private Long id;
    private String name;
    private String description;
    private String params;
    private LocalDateTime time;
    private String createdBy;

}
