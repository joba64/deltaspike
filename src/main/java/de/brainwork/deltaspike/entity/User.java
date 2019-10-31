package de.brainwork.deltaspike.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NamedEntityGraphs({
        @NamedEntityGraph(name = User.FETCHGROUP_DEFAULT, attributeNodes = {
                @NamedAttributeNode("mitarbeiter")
        })
})

@NamedQueries({
        @NamedQuery(name = User.QUERY_USER_FINDALL, query = "select u from User u")
})
@Table(name = "User")
public class User implements Serializable {


    public static final String QUERY_USER_FINDALL = "user.findAll";
    public static final String FETCHGROUP_DEFAULT = "fetch.default";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String vorname;
    @Basic(fetch = FetchType.LAZY)
    private String nachname;

    @OneToMany(mappedBy = "chef", fetch = FetchType.LAZY)
    private List<User> mitarbeiter;

    @ManyToOne()
    private User chef;

    @Transient
    private String loginname;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public List<User> getMitarbeiter() {
        return mitarbeiter;
    }

    public void setMitarbeiter(List<User> mitarbeiter) {
        this.mitarbeiter = mitarbeiter;
    }

    public User getChef() {
        return chef;
    }

    public void setChef(User chef) {
        this.chef = chef;
    }
}
