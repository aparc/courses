package relate_entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PRODUCER")
public class Producer implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "producer_id_gen" )
    @SequenceGenerator(name = "producer_id__gen", sequenceName = "PRODUCER_ID_SEQ")
    private int producerId;

    @Column(nullable = false, unique = true)
    private String name;

    private String address;

    @OneToOne(mappedBy = "producer")
    private ProducerInfo producerInfo;


}
