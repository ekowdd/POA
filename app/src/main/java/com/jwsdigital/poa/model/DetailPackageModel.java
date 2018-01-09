package com.jwsdigital.poa.model;

/**
 * Created by eko on 11/3/17.
 */

public class DetailPackageModel {
private String nama, alamat;
private int age;

public DetailPackageModel ( String nama, String alamat, int age ) {
	this.nama = nama;
	this.alamat = alamat;
	this.age = age;
}

public String getNama () {
	return nama;
}

public void setNama ( String nama ) {
	this.nama = nama;
}

public String getAlamat () {
	return alamat;
}

public void setAlamat ( String alamat ) {
	this.alamat = alamat;
}

public int getAge () {
	return age;
}

public void setAge ( int age ) {
	this.age = age;
}
}
