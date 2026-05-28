package dev.dmitriirussu.graphextraction.jpa.infrastructure;

import com.blazebit.persistence.Criteria;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.view.EntityViewManager;
import com.blazebit.persistence.view.EntityViews;
import com.blazebit.persistence.view.spi.EntityViewConfiguration;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class BlazeConfig {

    @Bean
    CriteriaBuilderFactory criteriaBuilderFactory(EntityManagerFactory emf) {
        return Criteria.getDefault().createCriteriaBuilderFactory(emf);
    }

    @Bean
    EntityViewManager entityViewManager(CriteriaBuilderFactory cbf) {
        EntityViewConfiguration config = EntityViews.createDefaultConfiguration();
        config.addEntityView(OwnerEntityView.class);
        config.addEntityView(PetEntityView.class);
        config.addEntityView(VisitEntityView.class);
        config.addEntityView(OwnerListEntityView.class);
        return config.createEntityViewManager(cbf);
    }
}
