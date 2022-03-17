package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "destination")
public class Destination {

    @Id
    @Column(name = "destination_id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long destinationId;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "destination", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<VacationPackage> vacationPackages = new HashSet<>();

    public Destination(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Destination() {

    }

    public Long getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(Long destinationId) {
        this.destinationId = destinationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<VacationPackage> getVacationPackages() {
        return vacationPackages;
    }

    public void setVacationPackages(Set<VacationPackage> vacationPackages) {
        this.vacationPackages = vacationPackages;
    }
}
