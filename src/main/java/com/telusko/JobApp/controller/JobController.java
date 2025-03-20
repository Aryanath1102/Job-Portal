package com.telusko.JobApp.controller;

import com.telusko.JobApp.model.JobPost;
import com.telusko.JobApp.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class JobController {

    @Autowired
    private JobService  service;

    @GetMapping("jobPosts")
    public List<JobPost> getAllJob(){
        return service.getAllJobs();
    }

    @GetMapping("jobPost/{postId}")
    public JobPost getJob(@PathVariable  int postId){
        return service.getJob(postId);

    }

    @GetMapping("jobPosts/keyword/{keyword}")
    public List<JobPost> searchByKeyword(@PathVariable("keyword")String keyword){
        return service.searchByKeyword(keyword);
    }

    @GetMapping("load")
    public String loadJob(){
        service.load();
        return "success";
    }


    @PostMapping("jobPost")
    public JobPost addJob(@RequestBody JobPost jobPost){
        service.addJobs(jobPost);
        return  service.getJob(jobPost.getPostId());
    }

    @PutMapping("jobPost")
    public JobPost updateJob(@RequestBody JobPost jobPost){
        service.updateJob(jobPost);
        return service.getJob(jobPost.getPostId());
    }

    @DeleteMapping("jobPost/{postId}")
    public String deleteJob(@PathVariable("postId") int postId){
        service.deleteJob(postId);
        return "deleted";
    }



}
