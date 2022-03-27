package com.github.ceakerBot.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;

import net.dean.jraw.RedditClient;
import net.dean.jraw.http.NetworkAdapter;
import net.dean.jraw.http.OkHttpNetworkAdapter;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.oauth.Credentials;
import net.dean.jraw.oauth.OAuthHelper;

@Configuration()
public class RedditClientConf {
    
    private final String username;
    private final String password;
    private final String clientId;
    private final String clientSecret;
    private final String userAgent;
    
    
    public RedditClientConf(@Value("${reddit.username}") String username,
                        @Value("${reddit.password}") String password,
                        @Value("${reddit.clientId}") String clientId,
                        @Value("${reddit.clientSecret}") String clientSecret,
                        @Value("${reddit.userAgent}") String userAgent){
        
        this.username = username;
        this.password = password;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.userAgent = userAgent;
        
        
    }
    
    @Bean
    public DefaultBotOptions options() {
        return new DefaultBotOptions();
    }
    
    @Bean
    public NetworkAdapter getNetworkAdapter() {
        
        return new OkHttpNetworkAdapter(new UserAgent(userAgent));
    }
    
    @Bean
    @Autowired
    public RedditClient getRedditClient(NetworkAdapter adapter) {
        
        Credentials creds = Credentials.script(username, password, clientId, clientSecret);
        
        return OAuthHelper.automatic(adapter, creds);
    }
    
    
    
    
    

}
