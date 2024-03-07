package com.example.ex1task.Controller;

import com.example.ex1task.Api.ApiResponse;
import com.example.ex1task.Model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    //Display all tasks .
    ArrayList<Task> tasks = new ArrayList<>();
    @GetMapping("/get")
    public ArrayList<Task> getTasks(){
        return tasks;
    }

    // Create a new task
    @PostMapping("/add")
    public ApiResponse addTask(@RequestBody Task task){
        tasks.add(task);
        return new ApiResponse("Task added successfully");
    }

    // Update a task
    @PutMapping("/update/{index}")
    public ApiResponse updateTask(@PathVariable int index, @RequestBody Task task) {
        if (index >= 0 && index < tasks.size()) {
            tasks.set(index, task);
            return new ApiResponse("Task updated successfully");
        }
        return new ApiResponse("Invalid index for task update");
    }

    // Delete a task
    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteTask(@PathVariable int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            return new ApiResponse("Task deleted successfully");
        }
        return new ApiResponse("Invalid index for task deletion");
    }

    // Change the task status as done or not done
    @PutMapping("/change/{index}")
    public ApiResponse changeTaskStatus(@PathVariable int index) {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            if (task.getStatus().equalsIgnoreCase("not done")) {
                task.setStatus("done");
                return new ApiResponse("Task status changed successfully");
            }
        }
        return new ApiResponse("Invalid index or task status cannot be changed");
    }






    // Search for a task by title
    @GetMapping("/search")
    public ApiResponse searchTaskByTitle(@RequestParam String title) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getTitle().equalsIgnoreCase(title)) {
                foundTasks.add(task);
            }
        }
        if (!foundTasks.isEmpty()) {
            return new ApiResponse("Tasks found with title: " + title); //foundTasks
        } else {
            return new ApiResponse("No tasks found with title: " + title);
        }


//    // Search for a task by title
//    @GetMapping("/search")
//    public ApiResponse searchTask(@RequestParam String title) {
//        Task foundTask = searchTaskByTitle(title);
//        if (foundTask != null) {
//            return new ApiResponse("Task found");
//        } else {
//            return new ApiResponse("Task not found");
//        }
//    }
//
//    // Helper method to search for a task by title
//    private Task searchTaskByTitle(String title) {
//        for (Task task : tasks) {
//            if (task.getTitle().equals(title)) {
//                return task;
//            }
//        }
//        return null;
//    }
}
}
