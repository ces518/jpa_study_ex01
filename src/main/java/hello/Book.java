package hello;

import javax.persistence.Entity;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 25/12/2019
 * Time: 11:08 오후
 **/
@Entity
public class Book extends Item {

    private String author;

    private String isbn;
}
