package classes.javabeans;

import javax.persistence.*;

@Entity
@Table(name = "mobile")
public class Mobile {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "marque")
    private String marque;

    @Column(name = "modele")
    private String modele;

    @Column(name = "prix")
    private double prix;

    @Column(name = "quantite")
    private int quantite;

    @Column(name = "disponibilite")
    private boolean disponibilite;

    // Constructeur vide
    public Mobile() {}

    // Constructeur avec paramètres
    public Mobile(String marque, String modele, double prix, int quantite, boolean disponibilite) {
        this.marque = marque;
        this.modele = modele;
        this.prix = prix;
        this.quantite = quantite;
        this.disponibilite = disponibilite;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getMarque() { return marque; }
    public void setMarque(String marque) { this.marque = marque; }

    public String getModele() { return modele; }
    public void setModele(String modele) { this.modele = modele; }

    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }

    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }

    public boolean isDisponibilite() { return disponibilite; }
    public void setDisponibilite(boolean disponibilite) { this.disponibilite = disponibilite; }

    @Override
    public String toString() {
        return "Mobile{id=" + id + ", marque=" + marque + ", modele=" + modele + "}";
    }
}