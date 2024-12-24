package com.fhunhospital.patient_management.entites;

import lombok.Data;

@Data
public class Contact {
    private TypeContact type ;
    private String valeur;

    public Contact() {
    }

    public Contact(TypeContact type, String valeur) {
        this.type = type;
        this.valeur = valeur;
    }

    public TypeContact getType() {
        return type;
    }

    public void setType(TypeContact type) {
        this.type = type;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public enum TypeContact{
        EMAIL("Email"),
        MOBILE("Mobile"),
        FIXE("Fixe");

        public final String value;

        TypeContact(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static TypeContact fromValue(String value) {
            for(TypeContact type : TypeContact.values()) {
                if(type.value.equals(value)) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Valeur invalide pour le type de contact " + value);
        }
    }
}
