package com.example.apirestfulcidu2021.modelos;

import com.google.gson.annotations.SerializedName;

public class Usuarios {
  private String id;
  private String name;
  private String email;
  private String gender;
  private String status;

  //Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getStatus() {
        return status;
    }
}
