package pl.tendan.kafkademo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private String content;

    @Builder.Default
    private Date creationDate = new Date(System.currentTimeMillis());
}
