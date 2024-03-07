package com.example.exercisecontrollerlayerrestcrud.Controller;

import com.example.exercisecontrollerlayerrestcrud.Api.ApiResponse;
import com.example.exercisecontrollerlayerrestcrud.Model.Customer;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    ArrayList<Customer> customers = new ArrayList<>();
    private int nextId = 1;
    //Get all the customers
    @GetMapping("/get")
    public List<Customer> getAllCustomers() {
        return customers;
    }
    //Add new customers
    @PostMapping("/add")
    public Customer addCustomer(@RequestBody Customer customer) {
        customer.setID(nextId++);
        customers.add(customer);
        return customer;
    }
     //Update customers
    @PutMapping("/update/{id}")
    public Customer updateCustomer(@PathVariable int id, @RequestBody Customer updatedCustomer) {
        for (Customer customer : customers) {
            if (customer.getID() == id) {
                customer.setUsername(updatedCustomer.getUsername());
                customer.setBalance(updatedCustomer.getBalance());
                return customer;
            }
        }
        return null;
    }
   //Delete customers
    @DeleteMapping("/delete/{id}")
    public boolean deleteCustomer(@PathVariable int id) {
        for (Customer customer : customers) {
            if (customer.getID() == id) {
                customers.remove(customer);
                return true;
            }
        }
        return false;
    }
    // Deposit money to customer
    @PostMapping("/deposit/{id}")
    public Customer depositMoney(@PathVariable int id, @RequestParam double amount) {
        for (Customer customer : customers) {
            if (customer.getID() == id) {
                customer.setBalance(customer.getBalance() + amount);
                return customer;
            }
        }
        return null;
    }

    //Withdraw money from customers
    @PutMapping("/withdraw/{id}")
    public Customer withdrawMoney(@PathVariable int id, @RequestParam double amount) {
        for (Customer customer : customers) {
            if (customer.getID() == id) {
                if (customer.getBalance() >= amount) {
                    customer.setBalance(customer.getBalance() - amount);
                    return customer;
                } else {
                    return null;
                }
            }
        }
        return null;
    }
}

