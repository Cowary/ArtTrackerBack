package org.cowary.arttrackerback.dbCase;

import org.cowary.arttrackerback.entity.Production;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.cowary.arttrackerback.repo.ProductionRepo;

@Component
public class ProductionCrud {

    @Autowired
    ProductionRepo productionRepo;

    public Production createOrGetByName(String name) {
        return productionRepo.findByName(name)
                .orElseGet(() -> {
                    Production production = new Production(name);
                    productionRepo.save(production);
                    return production;
        });
    }
}
