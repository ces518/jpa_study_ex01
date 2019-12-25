package hello;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 25/12/2019
 * Time: 11:07 오후
 **/
@Entity
@DiscriminatorValue("A")
public class Album extends Item {

    private String artist;
}
