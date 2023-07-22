package com.rdouda.core.library;

public class Patron {
    private int patronId;
    private String name;
    private String contactNumber;
    private String memberShipId;

    public Patron(int patronId, String name, String contactNumber, String memberShipId) {
        this.patronId = patronId;
        this.name = name;
        this.contactNumber = contactNumber;
        this.memberShipId = memberShipId;
    }
    public Patron(String name, String contactNumber, String memberShipId) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.memberShipId = memberShipId;
    }
    public Patron(){

    }

    @Override
    public String toString(){
        return this.name;
    }
    public int getPatronId() {
        return patronId;
    }

    public void setPatronId(int patronId) {
        this.patronId = patronId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getMemberShipId() {
        return memberShipId;
    }

    public void setMemberShipId(String memberShipId) {
        this.memberShipId = memberShipId;
    }
}
