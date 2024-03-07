package com.example.ex1task.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Task {

   // private int id;
    private  String ID;
    private String title ;
    private  String description ;
    private String status;
  //  private TaskStatus status;
}



