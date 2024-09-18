package ebook.nelsEBook.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Author extends BaseModel{

    @Column(name = "first_name", nullable = false)
    public String firstName;

    @Column(name = "last_name")
    public String lastName;

    @Column(name = "book")
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    public List<Book> books;

}
