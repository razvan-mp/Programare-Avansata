package compulsory.entities;

import org.dom4j.tree.AbstractEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQuery(name="BonusCity.findByName", query = "SELECT e from BonusCityEntity e WHERE e.name =: name")
@Table(name = "bonus_cities", schema = "public", catalog = "lab8")
public class BonusCityEntity extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "country")
    private String country;

    @Basic
    @Column(name = "name")
    private String name;

    @Basic
    @Column(name = "sister_name")
    private String sisterName;

    @Basic
    @Column(name = "sister_country")
    private String sisterCountry;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSisterName() {
        return sisterName;
    }

    public void setSisterName(String sisterName) {
        this.sisterName = sisterName;
    }

    public String getSisterCountry() {
        return sisterCountry;
    }

    public void setSisterCountry(String sisterCountry) {
        this.sisterCountry = sisterCountry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BonusCityEntity bonusCityEntity = (BonusCityEntity) o;
        return Objects.equals(id, bonusCityEntity.id) && Objects.equals(country, bonusCityEntity.country) && Objects.equals(name, bonusCityEntity.name) && Objects.equals(sisterName, bonusCityEntity.sisterName) && Objects.equals(sisterCountry, bonusCityEntity.sisterCountry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, name, sisterName, sisterCountry);
    }

    @Override
    public String toString() {
        return "BonusCity{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", name='" + name + '\'' +
                ", sisterName='" + sisterName + '\'' +
                ", sisterCountry='" + sisterCountry + '\'' +
                '}';
    }

    @ManyToMany
    private List<BonusCityEntity> manyToMany;

    public List<BonusCityEntity> getManyToMany() {
        return manyToMany;
    }

    public void setManyToMany(List<BonusCityEntity> manyToMany) {
        this.manyToMany = manyToMany;
    }
}