package com.hub.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SupabaseConfig {

    @Value("${supabase.url}")
    private String supabaseUrl;

    @Value("${supabase.apikey}")
    private String supabaseApiKey;

    public String getSupabaseUrl() {
        return supabaseUrl;
    }

    public String getSupabaseApiKey() {
        return supabaseApiKey;
    }
}
