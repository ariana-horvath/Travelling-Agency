package model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vacation_package")
public class VacationPackage {

    @Id
    @Column(name = "package_id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long packageId;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "price", nullable = false)
    private Float price;

    @Column(name = "details")
    private String details;

    @Column(name = "nb_of_places", nullable = false)
    private int nbOfPlaces;

    @Column(name = "available_places")
    private int availablePlaces;

    @Column(name = "status")
    private Status status;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_id")
    private Destination destination;

    @ManyToMany(mappedBy = "bookedVacations", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<User> users = new HashSet<>();

    public VacationPackage(String name, Float price, String details, int nbOfPlaces, Status status, LocalDate startDate, LocalDate endDate, Destination destination, int availablePlaces) {
        this.name = name;
        this.price = price;
        this.details = details;
        this.nbOfPlaces = nbOfPlaces;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.destination = destination;
        this.availablePlaces = availablePlaces;
    }

    public VacationPackage() {

    }

    public Long getPackageId() {
        return packageId;
    }

    public void setPackageId(Long packageId) {
        this.packageId = packageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getNbOfPlaces() {
        return nbOfPlaces;
    }

    public void setNbOfPlaces(int nbOfPlaces) {
        this.nbOfPlaces = nbOfPlaces;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public int getAvailablePlaces() {
        return availablePlaces;
    }

    public void setAvailablePlaces(int availablePlaces) {
        this.availablePlaces = availablePlaces;
    }
}
