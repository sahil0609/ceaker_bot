package com.github.ceakerBot.services;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dean.jraw.RedditClient;
import net.dean.jraw.models.Listing;
import net.dean.jraw.models.Submission;
import net.dean.jraw.models.SubredditSort;

@Service
@Slf4j
@RequiredArgsConstructor
public class RedditService {

    private final RedditClient client;
    
    
    
    
    public List<Listing<Submission>> getPosts(String subreddit, int limit, SubredditSort sortBy) {

        log.info("getting posts from subreddit");
       
       return client.subreddit(subreddit)
        .posts()
        .limit(limit)
        .sorting(sortBy)
        .build()
        .accumulate(1);
         
        
    }
    
    
}
