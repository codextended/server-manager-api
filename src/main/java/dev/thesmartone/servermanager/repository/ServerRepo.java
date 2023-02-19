package dev.thesmartone.servermanager.repository;

import dev.thesmartone.servermanager.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepo extends JpaRepository<Server, Long> {

    Server findByIpAddress(String ipAddress);
}
