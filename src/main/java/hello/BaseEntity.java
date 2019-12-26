package hello;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 26/12/2019
 * Time: 9:31 오후
 **/
@MappedSuperclass
public abstract class BaseEntity {

    private String createdBy;
    private String lastModifiedBy;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

}
