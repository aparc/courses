package relate_entity;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCER_INFO")
public class ProducerInfo {

    @Id
    @OneToOne
    @JoinColumn(name = "producerId")
    private Producer producer;

    private String type;

    private String phoneNumber;

    private String email;
}
