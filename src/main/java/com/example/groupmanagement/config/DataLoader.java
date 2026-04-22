package com.example.groupmanagement.config;

import com.example.groupmanagement.entity.*;
import com.example.groupmanagement.repository.*;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initData(
            GroupRepository groupRepo,
            ChainRepository chainRepo,
            BrandRepository brandRepo
    ) {
        return args -> {

            // ✅ Prevent duplicate insert
            if (groupRepo.count() > 0) {
                return;
            }

            // ================= GROUP =================
            Group group = new Group();
            group.setGroupName("Retail Group");
            groupRepo.save(group);

            // ================= COMPANY (CHAIN) =================
            Chain chain = new Chain();
            chain.setCompanyName("ABC Pvt Ltd");
            chain.setGstNumber("GST12345");
            chain.setGroup(group);
            chainRepo.save(chain);

            // ================= BRAND =================
            Brand brand = new Brand();
            brand.setBrandName("Nike");
            brand.setChain(chain);
            brandRepo.save(brand);

            System.out.println("✅ Sample data loaded!");
        };
    }
}