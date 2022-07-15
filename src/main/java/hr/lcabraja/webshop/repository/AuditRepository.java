package hr.lcabraja.webshop.repository;

import hr.lcabraja.webshop.model.Audit;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AuditRepository {
    private static AuditRepository singleton = null;
    private List<Audit> audits = new ArrayList<>();

    private AuditRepository() {
        audits.add(new Audit(UserRepository.getAllUsers().get(0), LocalDateTime.now(), "172.0.0.1"));
    }

    public static List<Audit> getAllAudits() {
        if (singleton == null) {
            singleton = new AuditRepository();
            return getAllAudits();
        }
        return singleton.audits;
    }
}
