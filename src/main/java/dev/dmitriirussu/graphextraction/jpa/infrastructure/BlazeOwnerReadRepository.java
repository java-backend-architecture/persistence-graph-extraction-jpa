package dev.dmitriirussu.graphextraction.jpa.infrastructure;

import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.view.EntityViewManager;
import com.blazebit.persistence.view.EntityViewSetting;
import dev.dmitriirussu.graphextraction.jpa.application.OwnerReadRepository;
import dev.dmitriirussu.graphextraction.jpa.application.view.OwnerListView;
import dev.dmitriirussu.graphextraction.jpa.application.view.OwnerView;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
class BlazeOwnerReadRepository implements OwnerReadRepository {

    private final EntityManager em;
    private final EntityViewManager evm;
    private final CriteriaBuilderFactory cbf;

    BlazeOwnerReadRepository(EntityManager em,
                             EntityViewManager evm,
                             CriteriaBuilderFactory cbf) {
        this.em = em;
        this.evm = evm;
        this.cbf = cbf;
    }

    @Override
    public Optional<OwnerView> findByIdWithGraph(String id) {
        var setting = EntityViewSetting.create(OwnerEntityView.class);
        var cb = cbf.create(em, OwnerEntity.class)
                .where("id").eq(id);
        return evm.applySetting(setting, cb)
                .getResultList()
                .stream()
                .findFirst()
                .map(ViewMapper::toView);
    }

    @Override
    public List<OwnerView> findAllWithGraph() {
        var setting = EntityViewSetting.create(OwnerEntityView.class);
        var cb = cbf.create(em, OwnerEntity.class)
                .orderByAsc("id");
        return evm.applySetting(setting, cb)
                .getResultList()
                .stream()
                .map(ViewMapper::toView)
                .toList();
    }

    @Override
    public List<OwnerListView> findAllFlat() {
        var setting = EntityViewSetting.create(OwnerListEntityView.class);
        var cb = cbf.create(em, OwnerEntity.class)
                .orderByAsc("id");
        return evm.applySetting(setting, cb)
                .getResultList()
                .stream()
                .map(ViewMapper::toListView)
                .toList();
    }
}
